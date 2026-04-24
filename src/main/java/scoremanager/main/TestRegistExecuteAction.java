package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 成績登録実行Action
 * 登録専用
 */
public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		School school = teacher.getSchool();

		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();

		String subjectCd = req.getParameter("subjectCd");
		String classNum = req.getParameter("classNum");
		int no = Integer.parseInt(req.getParameter("no"));
		int count = Integer.parseInt(req.getParameter("count"));

		Subject subject = sDao.get(subjectCd, school);
		String errorMessage = "";

		List<Test> saveList = new ArrayList<>();

		for (int i = 0; i < count; i++) {

			String studentNo = req.getParameter("studentNo_" + i);
			String pointStr = req.getParameter("point_" + i);

			int point = 0;
			if (pointStr != null && !pointStr.isEmpty()) {
				point = Integer.parseInt(pointStr);
			} else if (point < 0 || point > 100) {
				errorMessage = "0~100範囲で入力してください。";
			}

			Student student = new Student();
			student.setNo(studentNo);

			Test test = new Test();
			test.setStudent(student);
			test.setClassNum(classNum);
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(no);
			test.setPoint(point);

			saveList.add(test);
		}

		tDao.save(saveList);
		req.setAttribute("errorMessage", errorMessage);

		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}