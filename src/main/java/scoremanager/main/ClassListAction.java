package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/*
 * クラス一覧表示Action
 * ログイン中の教師の学校に所属するクラス一覧を表示する
 */
public class ClassListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログインユーザー取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 未ログインならログイン画面へ
		if (teacher == null) {
			response.sendRedirect("../login.jsp");
			return;
		}

		// DAO生成
		ClassNumDao dao = new ClassNumDao();

		// クラス一覧取得
		List<String> list = dao.filter(teacher.getSchool());

		// JSPへ渡す
		request.setAttribute("list", list);

		// 画面表示
		request.getRequestDispatcher("classlist.jsp")
				.forward(request, response);
	}
}