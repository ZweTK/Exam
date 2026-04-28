package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassViewAction extends Action {

	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		// クラス番号取得
		String class_num = request.getParameter("class_num");

		// DAO生成
		StudentDao dao = new StudentDao();

		// 学生一覧取得
		List<Student> list = dao.getClass(class_num, teacher.getSchool());

		// JSPへ送る
		request.setAttribute("list", list);
		request.setAttribute("class_num", class_num);

		// 画面表示
		request.getRequestDispatcher("class_view.jsp")
				.forward(request, response);
	}
}