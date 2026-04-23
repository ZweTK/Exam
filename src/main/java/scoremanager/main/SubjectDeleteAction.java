package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 未ログイン時はログイン画面へ
		if (teacher == null) {
			response.sendRedirect("../login.jsp");
			return;
		}

		// 科目コード取得
		String cd = request.getParameter("cd");

		// DAO
		SubjectDao dao = new SubjectDao();

		// 科目の詳細データを取得
		Subject subject = dao.get(cd);
		// JSPへ送る
		request.setAttribute("subject", subject);

		// 画面表示
		request.getRequestDispatcher("subject_delete.jsp")
				.forward(request, response);

	}
}