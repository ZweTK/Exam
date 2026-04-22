package dao;

//コミットテスト

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

/*
 * 学生情報を操作するDAOクラス
 * studentテーブルに対する取得・検索・登録・更新を行う
 */
public class StudentDao extends DAO {

	// 学校コード指定の基本SQL
	private String baseSql = "select * from student where school_cd=?";

	/*
	 * 学生番号から学生情報を1件取得する
	 */
	public Student get(String no) throws Exception {

		Student student = new Student();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"select * from student where no=?");

			statement.setString(1, no);

			ResultSet rSet = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setSchool(
						schoolDao.get(rSet.getString("school_cd")));
			} else {
				student = null;
			}

		} catch (Exception e) {
			throw e;

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return student;
	}

	/*
	 * ResultSetから学生一覧へ変換する共通処理
	 */
	private List<Student> postFilter(ResultSet rSet, School school)
			throws Exception {

		List<Student> list = new ArrayList<>();

		try {
			while (rSet.next()) {

				Student student = new Student();

				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setSchool(school);

				list.add(student);
			}

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * 入学年度・クラス番号・在学条件で検索
	 */
	public List<Student> filter(
			School school,
			int entYear,
			String classnum,
			boolean isAttend) throws Exception {

		List<Student> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		String condition = " and ent_year=? and class_num=?";
		String order = " order by no asc";
		String conditionIsAttend = "";

		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}

		try {
			statement = connection.prepareStatement(
					baseSql + condition + conditionIsAttend + order);

			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classnum);

			ResultSet rSet = statement.executeQuery();

			list = postFilter(rSet, school);

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return list;
	}

	/*
	 * 入学年度・在学条件で検索
	 */
	public List<Student> filter(
			School school,
			int entYear,
			boolean isAttend) throws Exception {

		List<Student> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		String condition = " and ent_year=?";
		String order = " order by no asc";
		String conditionIsAttend = "";

		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}

		try {
			statement = connection.prepareStatement(
					baseSql + condition + conditionIsAttend + order);

			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);

			ResultSet rSet = statement.executeQuery();

			list = postFilter(rSet, school);

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return list;
	}

	/*
	 * 学校・在学条件で検索
	 */
	public List<Student> filter(
			School school,
			boolean isAttend) throws Exception {

		List<Student> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		String order = " order by no asc";
		String conditionIsAttend = "";

		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}

		try {
			statement = connection.prepareStatement(
					baseSql + conditionIsAttend + order);

			statement.setString(1, school.getCd());

			ResultSet rSet = statement.executeQuery();

			list = postFilter(rSet, school);

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return list;
	}

	/*
	 * 学生情報を保存する
	 * 新規ならINSERT、存在すればUPDATE
	 */
	public boolean save(Student student) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		int count = 0;

		try {

			Student old = get(student.getNo());

			// 新規登録
			if (old == null) {

				statement = connection.prepareStatement(
						"insert into student(no,name,ent_year,class_num,is_attend,school_cd) values(?,?,?,?,?,?)");

				statement.setString(1, student.getNo());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.isAttend());
				statement.setString(6, student.getSchool().getCd());

			} else {

				// 更新
				statement = connection.prepareStatement(
						"update student set name=?,ent_year=?,class_num=?,is_attend=? where no=?");

				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.isAttend());
				statement.setString(5, student.getNo());
			}

			count = statement.executeUpdate();

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return count > 0;
	}
}