package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 科目一覧表示処理
 */
public class SubjectListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログインユーザー情報取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 未ログイン時はログイン画面へ
		if (teacher == null) {
			response.sendRedirect("../login.jsp");
			return;
		}

		// SubjectDao生成
		SubjectDao dao = new SubjectDao();

		// 学校コードに一致する科目一覧取得
		List<Subject> subjects = dao.filter(teacher.getSchool());

		// JSPへ渡す
		request.setAttribute("subjects", subjects);

		// 画面表示
		request.getRequestDispatcher("subject_list.jsp")
				.forward(request, response);
	}
}