package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/*
 * クラス新規登録実行Action
 * 入力されたクラス番号をDBへ登録する
 */
public class ClassCreateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログイン教師取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 未ログイン時
		if (teacher == null) {
			response.sendRedirect("../login.jsp");
			return;
		}

		// 入力値取得
		String classNum = request.getParameter("class_num");

		// Bean作成
		ClassNum c = new ClassNum();
		c.setClass_num(classNum);
		c.setSchool(teacher.getSchool());

		// 登録処理
		ClassNumDao dao = new ClassNumDao();
		dao.save(c);

		// 一覧へ戻る
		response.sendRedirect("ClassList.action");
	}
}