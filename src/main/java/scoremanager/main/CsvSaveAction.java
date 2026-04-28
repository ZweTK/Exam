// CsvSaveAction.java
package scoremanager.main;

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

			// セッションからプレビュー用の学生リストを取得
			@SuppressWarnings("unchecked")
			List<Student> list = (List<Student>) req
					.getSession()
					.getAttribute(
							"previewList");

			// データが存在しない場合
			if (list == null ||
					list.isEmpty()) {

				// エラーメッセージ設定
				req.setAttribute(
						"msg",
						"登録データがありません");

				// 結果画面へ移動
				req.getRequestDispatcher(
						"Upload-out.jsp")
						.forward(
								req,
								res);
				return;
			}

			// Studentテーブル操作用DAO作成
			StudentDao dao = new StudentDao();

			// リスト内の学生データを1件ずつ保存
			for (Student s : list) {

				// 学生番号が既に存在するか確認
				if (dao.get(
						s.getNo()) != null) {

					// 重複している場合はエラーメッセージ表示
					req.setAttribute(
							"msg",
							"学生番号 "
									+ s.getNo()
									+ " は既に登録されています");

					req.getRequestDispatcher(
							"Upload-out.jsp")
							.forward(
									req,
									res);
					return;
				}

				// DBへ学生情報を保存
				dao.save(s);
			}

			// 保存完了後、セッションのプレビューリスト削除
			req.getSession()
					.removeAttribute(
							"previewList");

			// 成功メッセージ設定
			req.setAttribute(
					"msg",
					"登録完了しました。");

			// 完了画面へ移動
			req.getRequestDispatcher(
					"Upload-out.jsp")
					.forward(
							req,
							res);

		} catch (Exception e) {

			// エラー発生時のメッセージ
			req.setAttribute(
					"msg",
					"CSV登録失敗");

			// エラー画面へ移動
			req.getRequestDispatcher(
					"Upload-out.jsp")
					.forward(
							req,
							res);
		}
	}
}