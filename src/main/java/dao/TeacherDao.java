package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;

/*
 * 教員情報を操作するDAOクラス
 * teacherテーブルに対する検索・認証・登録・更新を行う
 */
public class TeacherDao extends DAO {

	/*
	 * 教員IDを指定して教員情報を1件取得する
	 */
	public Teacher get(String id) throws Exception {

		Teacher teacher = new Teacher();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			// SQL準備
			statement = connection.prepareStatement(
					"select * from teacher where id=?");

			// ID設定
			statement.setString(1, id);

			// 実行
			ResultSet resultSet = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao();

			if (resultSet.next()) {

				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				teacher.setManage(resultSet.getBoolean("manage"));

				// 学校情報取得
				teacher.setSchool(
						schoolDao.get(resultSet.getString("school_cd")));

			} else {
				teacher = null;
			}

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return teacher;
	}

	/*
	 * ログイン認証
	 * IDとパスワードが一致すればTeacherを返す
	 */
	public Teacher login(String id, String password) throws Exception {

		Teacher teacher = get(id);

		if (teacher == null ||
				!teacher.getPassword().equals(password)) {
			return null;
		}

		return teacher;
	}

	/*
	 * 教員新規登録
	 */
	public boolean save(Teacher teacher) throws Exception {

		boolean result = false;

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			statement = connection.prepareStatement(
					"insert into teacher(id,password,name,school_cd,manage) values(?,?,?,?,?)");

			statement.setString(1, teacher.getId());
			statement.setString(2, teacher.getPassword());
			statement.setString(3, teacher.getName());
			statement.setString(4, teacher.getSchool().getCd());
			statement.setBoolean(5, teacher.isManage());

			int count = statement.executeUpdate();

			if (count > 0) {
				result = true;
			}

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return result;
	}

	/*
	 * 教員一覧取得
	 */
	public List<Teacher> filter() throws Exception {

		List<Teacher> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			statement = connection.prepareStatement(
					"select * from teacher order by id");

			ResultSet rs = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao();

			while (rs.next()) {

				Teacher teacher = new Teacher();

				teacher.setId(rs.getString("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				teacher.setManage(rs.getBoolean("manage"));
				teacher.setSchool(
						schoolDao.get(rs.getString("school_cd")));

				list.add(teacher);
			}

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return list;
	}

	/*
	 * 教員情報更新
	 */
	public boolean update(Teacher teacher) throws Exception {

		boolean result = false;

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			statement = connection.prepareStatement(
					"update teacher "
							+ "set password=?, name=?, school_cd=?, manage=? "
							+ "where id=?");

			statement.setString(1, teacher.getPassword());
			statement.setString(2, teacher.getName());
			statement.setString(3, teacher.getSchool().getCd());
			statement.setBoolean(4, teacher.isManage());
			statement.setString(5, teacher.getId());

			int count = statement.executeUpdate();

			if (count > 0) {
				result = true;
			}

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return result;
	}
}