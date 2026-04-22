package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdatExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ取得
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		int entYear = Integer.parseInt(request.getParameter("ent_year"));
		String classNum = request.getParameter("class_num");
		String isAttendStr = request.getParameter("isAttend");
		boolean isAttend = false;
		if (isAttendStr != null) {
			isAttend = true;
		}
		// Student作成
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);

		School school = teacher.getSchool();
		student.setSchool(school);

		// 保存
		StudentDao dao = new StudentDao();
		dao.save(student);

		// 一覧へ戻る
		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
	}
}