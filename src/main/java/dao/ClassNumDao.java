package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

/*
 * クラス番号情報を操作するDAOクラス
 * class_numテーブルに対する処理を行う
 */
public class ClassNumDao extends DAO {

	/*
	 * 指定したクラス番号と学校コードから
	 * クラス情報を取得するメソッド
	 */
	public ClassNum get(String class_num, School school) throws Exception {

		// 戻り値用オブジェクト
		ClassNum classNum = new ClassNum();

		// DB接続取得
		Connection connection = getConnection();

		PreparedStatement statement = null;

		try {
			// SQL準備
			statement = connection.prepareStatement(
					"select * from class_num where class_num=? and school_cd=?");

			// プレースホルダに値を設定
			statement.setString(1, class_num);
			statement.setString(2, school.getCd());

			// SQL実行
			ResultSet rSet = statement.executeQuery();

			SchoolDao sDao = new SchoolDao();

			// データが存在する場合
			if (rSet.next()) {
				classNum.setClass_num(rSet.getString("class_num"));
				classNum.setSchool(sDao.get(rSet.getString("school_cd")));
			} else {
				// 該当データなし
				classNum = null;
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

		return classNum;
	}

	/*
	 * 指定学校に所属するクラス番号一覧を取得するメソッド
	 */
	public List<String> filter(School school) throws Exception {

		// クラス番号一覧格納用リスト
		List<String> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			// SQL準備
			statement = connection.prepareStatement(
					"select class_num from class_num where school_cd=? order by class_num");

			// 学校コード設定
			statement.setString(1, school.getCd());

			// SQL実行
			ResultSet rSet = statement.executeQuery();

			// 結果をリストへ追加
			while (rSet.next()) {
				list.add(rSet.getString("class_num"));
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

	/*
	 * クラス新規登録
	 * 引数：ClassNumオブジェクト
	 * 戻り値：登録成功 true / 失敗 false
	 */
	public boolean save(ClassNum classNum) throws Exception {

		// DB接続取得
		Connection connection = getConnection();

		PreparedStatement statement = null;

		try {

			// SQL作成
			statement = connection.prepareStatement(
					"insert into class_num (school_cd, class_num) values (?, ?)");

			// 学校コード設定
			statement.setString(1, classNum.getSchool().getCd());

			// クラス番号設定
			statement.setString(2, classNum.getClass_num());

			// SQL実行
			int count = statement.executeUpdate();

			// 1件以上なら成功
			return count > 0;

		} catch (Exception e) {
			throw e;

		} finally {

			// Statementを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw e;
				}
			}

			// Connectionを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw e;
				}
			}
		}
	}

	/*
	 * クラス番号変更
	 * class_num テーブル変更
	 * student テーブルの所属クラスも変更
	 *
	 * 引数：
	 * classNum     変更前クラス情報
	 * newClassNum  変更後クラス番号
	 *
	 * 戻り値：
	 * 成功 true / 失敗 false
	 */
	public boolean save(ClassNum classNum, String newClassNum) throws Exception {

		Connection connection = getConnection();

		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;

		try {

			/*
			 * ① class_num テーブル更新
			 */
			statement1 = connection.prepareStatement(
					"update class_num set class_num=? where class_num=? and school_cd=?");

			// 新しいクラス番号
			statement1.setString(1, newClassNum);

			// 変更前クラス番号
			statement1.setString(2, classNum.getClass_num());

			// 学校コード
			statement1.setString(3, classNum.getSchool().getCd());

			statement1.executeUpdate();

			/*
			 * ② student テーブル更新
			 * そのクラスの学生全員変更
			 */
			statement2 = connection.prepareStatement(
					"update student set class_num=? where class_num=?");

			statement2.setString(1, newClassNum);
			statement2.setString(2, classNum.getClass_num());

			statement2.executeUpdate();

			return true;

		} catch (Exception e) {
			throw e;

		} finally {

			// statement1 close
			if (statement1 != null) {
				try {
					statement1.close();
				} catch (SQLException e) {
					throw e;
				}
			}

			// statement2 close
			if (statement2 != null) {
				try {
					statement2.close();
				} catch (SQLException e) {
					throw e;
				}
			}

			// connection close
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw e;
				}
			}
		}
	}
}