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

		// DAO
		ClassNumDao dao = new ClassNumDao();

		// 同じ名前ならそのまま一覧へ
		if (oldClassNum.equals(newClassNum)) {
			response.sendRedirect("ClassList.action");
			return;
		}

		// 既存チェック
		ClassNum exist = dao.get(newClassNum, teacher.getSchool());

		// 既に存在
		if (exist != null) {

			request.setAttribute("error", "クラスが存在しています");

			request.setAttribute("class_num", oldClassNum);
			request.setAttribute("new_class_num", newClassNum);

			request.getRequestDispatcher("classupdate.jsp")
					.forward(request, response);

			return;
		}

		// Bean作成
		ClassNum c = new ClassNum();
		c.setClass_num(oldClassNum);
		c.setSchool(teacher.getSchool());

		// 更新処理
		dao.save(c, newClassNum);

		// 一覧へ戻る
		response.sendRedirect("ClassList.action");
	}
}