// CsvSaveAction.java
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

// CSVプレビュー画面で確認したデータをDBへ保存するActionクラス
public class CsvSaveAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		try {

			// セッションからCSVプレビューリスト取得
			@SuppressWarnings("unchecked")
			List<Student> list = (List<Student>) req
					.getSession()
					.getAttribute("previewList");

			// データなし
			if (list == null || list.isEmpty()) {

				req.setAttribute(
						"msg",
						"登録データがありません");

				req.getRequestDispatcher(
						"Upload-out.jsp")
						.forward(req, res);
				return;
			}

			// DAO生成
			StudentDao dao = new StudentDao();

			// 重複学生番号保存用
			List<String> duplicateNos = new ArrayList<String>();

			// 先に全部チェックする
			for (Student s : list) {

				// DBに既に存在するなら追加
				if (dao.get(s.getNo()) != null) {
					duplicateNos.add(
							s.getNo());
				}
			}

			// 重複が1件でもあれば保存しない
			if (!duplicateNos.isEmpty()) {

				String msg = "登録している番号 : "
						+ String.join(
								", ",
								duplicateNos);

				req.setAttribute(
						"msg",
						msg);

				req.getRequestDispatcher(
						"Upload-out.jsp")
						.forward(req, res);
				return;
			}

			// 重複なし → 全件保存
			for (Student s : list) {
				dao.save(s);
			}

			// セッション削除
			req.getSession()
					.removeAttribute(
							"previewList");

			req.setAttribute(
					"msg",
					"登録完了しました。");

			req.getRequestDispatcher(
					"Upload-out.jsp")
					.forward(req, res);

		} catch (Exception e) {

			req.setAttribute(
					"msg",
					"CSV登録失敗");

			req.getRequestDispatcher(
					"Upload-out.jsp")
					.forward(req, res);
		}
	}
}