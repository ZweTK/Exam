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

	/** ベースSQL */
	private String baseSql = "select t.student_no, t.class_num, t.subject_cd, t.school_cd, t.no, t.point,"
			+ " s.name as student_name, s.ent_year, s.is_attend,"
			+ " sub.name as subject_name "
			+ "from test t "
			+ "join student s on t.student_no = s.no "
			+ "join subject sub on t.subject_cd = sub.cd "
			+ "where t.school_cd = ? ";

	/**
	 * ResultSetからTestリストを生成
	 * @param rs ResultSet
	 * @param school 学校情報
	 * @return Testリスト
	 * @throws Exception
	 */
	private List<Test> postFilter(ResultSet rs, School school) throws Exception {

		List<Test> list = new ArrayList<>();

		while (rs.next()) {

			Test test = new Test();

			Student student = new Student();
			student.setNo(rs.getString("student_no"));
			student.setName(rs.getString("student_name"));
			student.setEntYear(rs.getInt("ent_year"));
			student.setAttend(rs.getBoolean("is_attend"));
			student.setSchool(school);

			Subject subject = new Subject();
			subject.setCd(rs.getString("subject_cd"));
			subject.setName(rs.getString("subject_name"));
			subject.setSchool_cd(school.getCd());

			test.setStudent(student);
			test.setClassNum(rs.getString("class_num"));
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(rs.getInt("no"));
			test.setPoint(rs.getInt("point"));

			list.add(test);
		}

		return list;
	}

	/**
	 * 指定条件で検索
	 * @param entYear 入学年度
	 * @param classNum クラス番号
	 * @param subject 科目
	 * @param num 回数
	 * @param school 学校
	 * @return テスト一覧
	 * @throws Exception
	 */
	public List<Test> filter(
			int entYear,
			String classNum,
			Subject subject,
			int num,
			School school) throws Exception {

		List<Test> list = new ArrayList<>();

		Connection con = getConnection();

		String sql = baseSql
				+ " and s.ent_year = ?"
				+ " and t.class_num = ?"
				+ " and t.subject_cd = ?"
				+ " and t.no = ?"
				+ " order by t.student_no";

		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, school.getCd());
		st.setInt(2, entYear);
		st.setString(3, classNum);
		st.setString(4, subject.getCd());
		st.setInt(5, num);

		ResultSet rs = st.executeQuery();

		list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}

	/**
	 * 1件取得
	 * @param student 学生
	 * @param subject 科目
	 * @param school 学校
	 * @param no 回数
	 * @return Test
	 * @throws Exception
	 */
	public Test get(
			Student student,
			Subject subject,
			School school,
			int no) throws Exception {

		Connection con = getConnection();

		String sql = baseSql
				+ " and t.student_no = ?"
				+ " and t.subject_cd = ?"
				+ " and t.no = ?";

		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, school.getCd());
		st.setString(2, student.getNo());
		st.setString(3, subject.getCd());
		st.setInt(4, no);

		ResultSet rs = st.executeQuery();

		List<Test> list = postFilter(rs, school);

		st.close();
		con.close();

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 登録・更新
	 * @param test Test
	 * @param con Connection
	 * @return 成功:true
	 * @throws Exception
	 */
	public boolean save(Test test, Connection con) throws Exception {

		String sql = "merge into test key(student_no, subject_cd, school_cd, no) "
				+ "values(?, ?, ?, ?, ?, ?)";

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
	 * 複数件登録・更新
	 * @param list Testリスト
	 * @return 成功:true
	 * @throws Exception
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