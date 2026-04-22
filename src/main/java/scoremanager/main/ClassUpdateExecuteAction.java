package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/*
 * クラス変更実行Action
 * クラス番号変更＋学生所属クラス更新
 */
public class ClassUpdateExecuteAction extends Action {

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

		// パラメータ取得
		String oldClassNum = request.getParameter("old_class_num");
		String newClassNum = request.getParameter("new_class_num");

		// Bean作成
		ClassNum c = new ClassNum();
		c.setClass_num(oldClassNum);
		c.setSchool(teacher.getSchool());

		// 更新処理
		ClassNumDao dao = new ClassNumDao();
		dao.save(c, newClassNum);

		// 一覧へ戻る
		response.sendRedirect("ClassList.action");
	}
}