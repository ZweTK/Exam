package scoremanager.main;

import java.util.Collections;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SeatChangeAction extends Action {

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

		ClassNumDao cDao = new ClassNumDao();
		List<String> classList = cDao.filter(teacher.getSchool());

		// 学生一覧取得
		List<Student> list = dao.getClass(class_num, teacher.getSchool());

		list.removeIf(student -> !student.isAttend());
		// ランダム並び替え（席替え）
		Collections.shuffle(list);
		// JSPへ渡す
		request.setAttribute("list", list);
		request.setAttribute("class_num", class_num);
		request.setAttribute(
				"class_num_set",
				classList);

		request.getRequestDispatcher("seatchange.jsp")
				.forward(request, response);
	}
}