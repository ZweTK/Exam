package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/*
 * クラス変更画面表示Action
 * class_edit.jsp へ遷移する
 */
public class ClassUpdateAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 変更前クラス番号取得
		String classNum = request.getParameter("class_num");

		// JSPへ渡す
		request.setAttribute("class_num", classNum);

		// 編集画面表示
		request.getRequestDispatcher("classupdate.jsp")
				.forward(request, response);
	}
}