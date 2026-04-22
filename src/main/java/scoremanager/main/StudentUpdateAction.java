package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		ClassNumDao cDao = new ClassNumDao();
		List<String> classList = cDao.filter(teacher.getSchool());

		// 学生番号取得
		String no = request.getParameter("no");

		// DAO
		StudentDao dao = new StudentDao();

		// 学生情報取得
		Student student = dao.get(no);

		// JSPへ送る
		request.setAttribute("no", student.getNo());
		request.setAttribute("name", student.getName());
		request.setAttribute("ent_year", student.getEntYear());
		request.setAttribute("class_num", student.getClassNum());
		request.setAttribute("class_num_set", classList);
		request.setAttribute("student", student);

		// 画面表示
		request.getRequestDispatcher("student_update.jsp")
				.forward(request, response);

	}
}