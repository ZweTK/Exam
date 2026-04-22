package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

/*
 * CSVファイルをアップロードし、
 * 学生情報をデータベースへ登録するアクションクラス
 */
public class CsvUploadAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			// アップロードされたCSVファイル取得
			Part filePart = request.getPart("csvfile");

			// UTF-8で読み込み
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							filePart.getInputStream(), "UTF-8"));

			String line;

			// StudentDAO生成
			StudentDao dao = new StudentDao();

			// 1行ずつ読み込む
			while ((line = br.readLine()) != null) {

				// カンマ区切りで分割
				String[] data = line.split(",");

				// 項目数不足ならスキップ
				if (data.length < 6) {
					continue;
				}

				// Studentオブジェクト作成
				Student p = new Student();

				// CSVデータをセット
				p.setNo(data[0]); // 学生番号
				p.setName(data[1]); // 氏名
				p.setEntYear(
						Integer.parseInt(data[2])); // 入学年度
				p.setClassNum(data[3]); // クラス番号

				// 在学フラグ文字列 → boolean変換
				p.setAttend(
						Boolean.parseBoolean(data[4]));

				// 学校情報セット
				School school = new School();
				school.setCd(data[5]);

				p.setSchool(school);

				// DB保存
				dao.save(p);
			}

			// 読み込み終了
			br.close();

			// 成功メッセージ
			request.setAttribute(
					"msg",
					"CSV Upload Success");

		} catch (Exception e) {

			// エラーメッセージ
			request.setAttribute(
					"msg",
					"Error : " + e.getMessage());
		}

		// 結果画面へ遷移
		request.getRequestDispatcher(
				"Upload-out.jsp")
				.forward(request, response);
	}
}