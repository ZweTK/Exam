// TestRegistAction.java
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		ClassNumDao cDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();

		int year = LocalDate.now().getYear();

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		List<Integer> noSet = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			noSet.add(i);
		}

		req.setAttribute("entYearSet", entYearSet);
		req.setAttribute("noSet", noSet);
		req.setAttribute("classList", cDao.filter(teacher.getSchool()));
		req.setAttribute("subjectList", sDao.filter(teacher.getSchool()));

		String entYearStr = req.getParameter("entYear");
		String classNum = req.getParameter("classNum");
		String subjectCd = req.getParameter("subjectCd");
		String noStr = req.getParameter("no");

		if (entYearStr != null &&
			classNum != null &&
			subjectCd != null &&
			noStr != null &&
			!entYearStr.equals("0") &&
			!classNum.equals("0") &&
			!subjectCd.equals("0") &&
			!noStr.equals("0")) {

			int entYear = Integer.parseInt(entYearStr);
			int no = Integer.parseInt(noStr);

			Subject subject = sDao.get(subjectCd, teacher.getSchool());

			List<Test> tests = tDao.filter(
				entYear,
				classNum,
				subject,
				no,
				teacher.getSchool());

			req.setAttribute("tests", tests);
			req.setAttribute("entYear", entYear);
			req.setAttribute("classNum", classNum);
			req.setAttribute("subjectCd", subjectCd);
			req.setAttribute("subjectName", subject.getName());
			req.setAttribute("no", no);

		} else if (entYearStr != null) {
			req.setAttribute("error",
				"入学年度・クラス・科目・回数を選択してください");
		}

		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}