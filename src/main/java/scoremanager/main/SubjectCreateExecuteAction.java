package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 科目登録実行処理
 */
public class SubjectCreateExecuteAction extends Action {

	private Subject String;

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログインユーザー取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 入力値取得
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

		// エラー格納用
		Map<String, String> errors = new HashMap<>();

		// 入力チェック
		SubjectDao sbDao = new SubjectDao();

		Subject subjectCheck = sbDao.getSchool(cd, teacher.getSchool());

		// 科目コード未入力チェック
		if (cd == null || cd.isEmpty()) {
			errors.put("cd", "科目コードを入力してください。");

			// 重複チェック
		} else if (subjectCheck != null) {
			errors.put("cd", "科目コードが重複してます。");

			// 3文字チェック
		} else if (cd.length() != 3) {
			errors.put("cd", "科目コードは3文字で入力してください。");
		}

		// エラー時は入力画面へ戻る
		if (errors.size() > 0) {

			request.setAttribute("errors", errors);
			request.setAttribute("cd", cd);
			request.setAttribute("name", name);

			request.getRequestDispatcher(
					"subject_create.jsp")
					.forward(request, response);

			return;
		}

		// Beanへセット
		Subject subject = new Subject();

		subject.setSchool_cd(
				teacher.getSchool().getCd());

		subject.setCd(cd);
		subject.setName(name);

		// 登録処理
		SubjectDao dao = new SubjectDao();
		dao.save(subject);

		// 一覧画面へ戻る
		response.sendRedirect("subject_create_done.jsp");
	}
}