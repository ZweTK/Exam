package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

/*
 * 学校情報を操作するDAOクラス
 * schoolテーブルに対する取得処理を行う
 */
public class SchoolDao extends DAO {

	/*
	 * 学校コードを指定して学校情報を1件取得するメソッド
	 *
	 * 引数:
	 * cd = 学校コード
	 *
	 * 戻り値:
	 * Schoolオブジェクト
	 * 該当データがない場合はnull
	 */
	public School get(String cd) throws Exception {

		// 学校オブジェクト生成
		School school = new School();

		// データベース接続取得
		Connection connection = getConnection();

		// SQL実行用
		PreparedStatement statement = null;

		try {

			// SQL文を準備
			statement = connection.prepareStatement(
					"select * from school where cd = ?");

			// 学校コード設定
			statement.setString(1, cd);

			// SQL実行
			ResultSet rSet = statement.executeQuery();

			// データが存在する場合
			if (rSet.next()) {

				// 値をSchoolオブジェクトへ格納
				school.setCd(rSet.getString("cd"));
				school.setName(rSet.getString("name"));

			} else {

				// 該当データなし
				school = null;
			}

		} catch (Exception e) {
			throw e;

		} finally {

			// PreparedStatementを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// Connectionを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return school;
	}

	/*
	 * 学校コード一覧を取得するメソッド
	 *
	 * 戻り値:
	 * 学校コードのリスト
	 */
	public List<String> filter() throws Exception {

		// 学校コード格納用リスト
		List<String> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			// SQL準備
			statement = connection.prepareStatement(
					"select cd from school order by cd");

			// SQL実行
			ResultSet rSet = statement.executeQuery();

			// 取得結果をリストへ追加
			while (rSet.next()) {
				list.add(rSet.getString("cd"));
			}

		} catch (Exception e) {
			throw e;

		} finally {

			// Statementを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// Connectionを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}
}