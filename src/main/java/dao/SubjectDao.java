package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

/**
 * 科目情報DAOクラス
 */
public class SubjectDao extends DAO {

	/**
	 * ResultSetからSubjectオブジェクト生成
	 */
	private Subject postFilter(ResultSet rs) throws Exception {

		Subject subject = new Subject();

		subject.setSchool_cd(rs.getString("school_cd"));
		subject.setCd(rs.getString("cd"));
		subject.setName(rs.getString("name"));

		return subject;
	}

	/**
	 * 学校別 科目一覧取得
	 * @param school 学校情報
	 * @return 科目一覧
	 */
	public List<Subject> filter(School school) throws Exception {

		List<Subject> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"select * from subject " +
						"where school_cd=? " +
						"order by cd");

		st.setString(1, school.getCd());

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			list.add(postFilter(rs));
		}

		st.close();
		con.close();

		return list;
	}

	/**
	 * 科目コードで1件取得
	 * @param cd 科目コード
	 * @param school 学校情報
	 * @return Subject
	 */
	public Subject get(String cd, School school) throws Exception {

		Subject subject = null;

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"select * from subject " +
						"where cd=? and school_cd=?");

		st.setString(1, cd);
		st.setString(2, school.getCd());

		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			subject = postFilter(rs);
		}

		st.close();
		con.close();

		return subject;
	}

	/**
	 * 科目登録
	 */
	public boolean save(Subject subject) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"insert into subject(school_cd, cd, name) " +
						"values(?, ?, ?)");

		st.setString(1, subject.getSchool_cd());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getName());

		int count = st.executeUpdate();

		st.close();
		con.close();

		return count > 0;
	}

	/**
	 * 科目更新
	 */
	public boolean update(Subject subject) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"update subject set name=? " +
						"where school_cd=? and cd=?");

		st.setString(1, subject.getName());
		st.setString(2, subject.getSchool_cd());
		st.setString(3, subject.getCd());

		int count = st.executeUpdate();

		st.close();
		con.close();

		return count > 0;
	}

	/**
	 * 科目削除
	 */
	public boolean delete(Subject subject) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"delete from subject " +
						"where school_cd=? and cd=?");

		st.setString(1, subject.getSchool_cd());
		st.setString(2, subject.getCd());

		int count = st.executeUpdate();

		st.close();
		con.close();

		return count > 0;
	}

	/**
	 * 科目チック
	 */
	public Subject get(String cd) throws Exception {
		// データベース接続取得
		Connection con = getConnection();

		// subjectテーブルから指定された科目コードを検索
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM subject WHERE cd = ?");
		st.setString(1, cd);

		// SELECT文なので executeQuery() を使用
		ResultSet rs = st.executeQuery();

		Subject subject = null;

		// データが存在する場合、Subjectオブジェクトへ格納
		if (rs.next()) {
			subject = new Subject();
			subject.setCd(rs.getString("cd"));
			subject.setName(rs.getString("name"));
		}

		// リソース解放
		rs.close();
		st.close();
		con.close();

		return subject;
	}
}