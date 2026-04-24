package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

/**
 * テスト（点数）情報DAOクラス
 */
public class TestDao extends DAO {

	/**
	 * ResultSet → List<Test>
	 */
	private List<Test> postFilter(ResultSet rs, School school) throws Exception {

		List<Test> list = new ArrayList<>();

		while (rs.next()) {

			Student student = new Student();
			student.setNo(rs.getString("student_no"));
			student.setName(rs.getString("student_name"));
			student.setEntYear(rs.getInt("ent_year"));
			student.setClassNum(rs.getString("class_num"));
			student.setSchool(school);

			Subject subject = new Subject();
			subject.setCd(rs.getString("subject_cd"));
			subject.setName(rs.getString("subject_name"));
			subject.setSchool_cd(school.getCd());

			Test test = new Test();
			test.setStudent(student);
			test.setClassNum(rs.getString("class_num"));
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(rs.getInt("test_no"));
			test.setPoint(rs.getInt("point"));

			list.add(test);
		}

		return list;
	}

	/**
	 * 検索
	 */
	public List<Test> filter(
			int entYear,
			String classNum,
			Subject subject,
			int num,
			School school) throws Exception {

		List<Test> list = new ArrayList<>();

		Connection con = getConnection();

		String sql = "SELECT " +
				" s.ent_year, " +
				" s.class_num, " +
				" s.no AS student_no, " +
				" s.name AS student_name, " +
				" sub.cd AS subject_cd, " +
				" sub.name AS subject_name, " +
				" t.no AS test_no, " +
				" t.point " +

				"FROM student s " +

				"CROSS JOIN ( " +
				" SELECT cd, name " +
				" FROM subject " +
				" WHERE school_cd = ? " +
				") sub " +

				"LEFT JOIN test t " +
				" ON t.student_no = s.no " +
				" AND t.class_num = s.class_num " +
				" AND t.subject_cd = sub.cd " +
				" AND t.school_cd = s.school_cd " +
				" AND t.no = ? " +

				"WHERE s.school_cd = ? " +
				"AND s.ent_year = ? " +
				"AND s.class_num = ? " +
				"AND sub.cd = ? " +

				"ORDER BY s.no";

		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, school.getCd());
		st.setInt(2, num);
		st.setString(3, school.getCd());
		st.setInt(4, entYear);
		st.setString(5, classNum);
		st.setString(6, subject.getCd());

		ResultSet rs = st.executeQuery();

		list = postFilter(rs, school);

		rs.close();
		st.close();
		con.close();

		return list;
	}

	/**
	 * 登録
	 */
	public boolean save(Test test, Connection con) throws Exception {

		String sql = "MERGE INTO test " +
				"(student_no, subject_cd, school_cd, no, class_num, point) " +
				"KEY(student_no, subject_cd, school_cd, no) " +
				"VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, test.getStudent().getNo());
		st.setString(2, test.getSubject().getCd());
		st.setString(3, test.getSchool().getCd());
		st.setInt(4, test.getNo());
		st.setString(5, test.getClassNum());
		st.setInt(6, test.getPoint());

		int count = st.executeUpdate();

		st.close();

		return count > 0;
	}

	/**
	 * 複数登録
	 */
	public boolean save(List<Test> list) throws Exception {

		Connection con = getConnection();

		boolean result = true;

		try {

			con.setAutoCommit(false);

			for (Test test : list) {
				if (!save(test, con)) {
					result = false;
					break;
				}
			}

			if (result) {
				con.commit();
			} else {
				con.rollback();
			}

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		con.close();

		return result;
	}
}