package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/*
 * クラス新規登録画面表示Action
 * 入力画面(class_create.jsp)へ遷移する
 */
public class ClassCreateAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 登録画面へ移動
		request.getRequestDispatcher("classcreate.jsp")
				.forward(request, response);
	}
}