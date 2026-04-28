// CsvUploadAction.java
package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

// CSVファイルを読み込み、確認画面へ送るActionクラス
public class CsvUploadAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		try {

			// アップロードされたCSVファイルを取得
			Part filePart = request.getPart("csvfile");

			// CSVファイルをUTF-8で読み込む
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							filePart.getInputStream(),
							"UTF-8"));

			// Studentオブジェクトを格納するリスト
			List<Student> list = new ArrayList<Student>();

			String line;

			// 1行ずつCSVデータを読み込む
			while ((line = br.readLine()) != null) {

				// カンマ区切りで分割
				String[] data = line.split(",");

				// データ数不足なら読み飛ばす
				if (data.length < 6) {
					continue;
				}

				// Studentオブジェクト作成
				Student student = new Student();

				// 学生番号
				student.setNo(data[0].trim());

				// 学生名
				student.setName(data[1].trim());

				// 入学年度
				student.setEntYear(
						Integer.parseInt(
								data[2].trim()));

				// クラス番号
				student.setClassNum(
						data[3].trim());

				// 在学フラグ（true / false）
				student.setAttend(
						Boolean.parseBoolean(
								data[4].trim()));

				// 学校情報作成
				School school = new School();

				// 学校コード設定
				school.setCd(
						data[5].trim());

				// StudentにSchool情報セット
				student.setSchool(
						school);

				// リストへ追加
				list.add(student);
			}

			// BufferedReaderを閉じる
			br.close();

			// セッションに確認用データ保存
			request.getSession()
					.setAttribute(
							"previewList",
							list);

			// 確認画面へ遷移
			request.getRequestDispatcher(
					"CsvUploadConfirm.jsp")
					.forward(
							request,
							response);

		} catch (Exception e) {

			// エラー時メッセージ設定
			request.setAttribute(
					"msg",
					"CSV読込失敗");

			// エラー画面へ遷移
			request.getRequestDispatcher(
					"Upload-out.jsp")
					.forward(
							request,
							response);
		}
	}
}