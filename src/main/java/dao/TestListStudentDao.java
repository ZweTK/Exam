package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends DAO {

	// ベースSQL
	private String baseSql = "SELECT " +
			"    s.name AS subject_name, " +
			"    s.cd AS subject_cd, " +
			"    t.no AS num, " +
			"    t.point AS point, " +
			"    st.name AS student_name " +
			"FROM student st " +
			"LEFT JOIN test t " +
			"    ON st.no = t.student_no " +
			"LEFT JOIN subject s " +
			"    ON t.subject_cd = s.cd ";

	/**
	 * ResultSet → List変換
	 */
	private List<TestListStudent> postFilter(
			ResultSet rs) throws Exception {

		List<TestListStudent> list = new ArrayList<>();

		while (rs.next()) {

			// 成績情報が存在しない場合
			if (rs.getString("subject_cd") == null) {
				continue;
			}

			TestListStudent bean = new TestListStudent();

			bean.setSubjectName(
					rs.getString("subject_name"));

			bean.setSubjectCD(
					rs.getString("subject_cd"));

			bean.setNum(
					rs.getInt("num"));

			bean.setPoint(
					rs.getInt("point"));

			list.add(bean);
		}

		return list;
	}

	/**
	 * 学生番号検索
	 */
	public List<TestListStudent> filter(
			String studentNo) {

		List<TestListStudent> list = new ArrayList<>();

		String sql = baseSql +
				"WHERE st.no = ? " +
				"ORDER BY s.cd, t.no";

		try (

				Connection connection = getConnection();

				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, studentNo);

			ResultSet rs = ps.executeQuery();

			list = postFilter(rs);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
}