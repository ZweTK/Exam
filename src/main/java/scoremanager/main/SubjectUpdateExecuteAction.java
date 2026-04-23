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

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// エラー格納用
		Map<String, String> errors = new HashMap<>();

		// セッション取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ取得
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

		// DAO生成
		SubjectDao dao = new SubjectDao();

		// 学校情報付きで科目取得
		Subject subjectCheck = dao.get(cd, teacher.getSchool());

		// 科目存在チェック
		if (subjectCheck == null) {
			errors.put("cd", "科目が存在しません。");
		}

		// 20文字以内チェック
		if (name != null && name.length() > 20) {
			errors.put("name", "科目名は20文字以内で入力してください。");
		}

		// エラー時
		if (!errors.isEmpty()) {

			Subject subject = new Subject();
			subject.setCd(cd);
			subject.setName(name);

			request.setAttribute("errors", errors);
			request.setAttribute("subject", subject);

			request.getRequestDispatcher("subject_update.jsp")
					.forward(request, response);
			return;
		}

		// 更新用Subject生成
		Subject subject = new Subject();
		subject.setSchool_cd(teacher.getSchool().getCd());
		subject.setCd(cd);
		subject.setName(name);

		// 更新実行
		dao.update(subject);

		// 完了画面へ
		request.setAttribute("subject", subject);

		request.getRequestDispatcher("subject_update_done.jsp")
				.forward(request, response);
	}
}