package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import bean.StudentProfile;

/*
 * 学生プロフィール情報を操作するDAOクラス
 * student_profile テーブルに対する取得・登録・更新を行う
 */
public class StudentProfileDao extends DAO {

	/*
	 * テーブルが存在しない場合に作成する（初回起動時に自動実行）
	 */
	public void createTableIfNotExists() throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "CREATE TABLE IF NOT EXISTS student_profile (" +
					"  student_no   VARCHAR(20)   PRIMARY KEY, " +
					"  name         VARCHAR(100), " +
					"  address      VARCHAR(255), " +
					"  phone        VARCHAR(20),  " +
					"  email        VARCHAR(100), " +
					"  photo        BLOB,         " +
					"  photo_mime   VARCHAR(50)   " +
					")";

			statement = connection.prepareStatement(sql);
			statement.executeUpdate();

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * 学生番号でプロフィールを1件取得する
	 * 存在しない場合は null を返す
	 */
	public StudentProfile get(String studentNo) throws Exception {

		createTableIfNotExists();

		Connection connection = getConnection();
		PreparedStatement statement = null;
		StudentProfile profile = null;

		try {
			statement = connection.prepareStatement(
					"SELECT * FROM student_profile WHERE student_no = ?");

			statement.setString(1, studentNo);

			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {

				profile = new StudentProfile();
				profile.setStudentNo(rSet.getString("student_no"));
				profile.setName(rSet.getString("name"));
				profile.setAddress(rSet.getString("address"));
				profile.setPhone(rSet.getString("phone"));
				profile.setEmail(rSet.getString("email"));
				profile.setPhotoMimeType(rSet.getString("photo_mime"));

				// 写真データをBase64に変換してセット
				byte[] photoBytes = rSet.getBytes("photo");
				if (photoBytes != null && photoBytes.length > 0) {
					profile.setPhotoBase64(
							Base64.getEncoder().encodeToString(photoBytes));
				}
			}

		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return profile;
	}

	/*
	 * プロフィールを保存する
	 * 新規ならINSERT、存在すればUPDATE
	 * photoBytes が null の場合は写真を更新しない
	 */
	public boolean save(
			StudentProfile profile,
			byte[] photoBytes,
			String photoMime) throws Exception {

		createTableIfNotExists();

		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			StudentProfile existing = get(profile.getStudentNo());

			if (existing == null) {

				// 新規登録
				statement = connection.prepareStatement(
						"INSERT INTO student_profile" +
								" (student_no, name, address, phone, email, photo, photo_mime)" +
								" VALUES (?, ?, ?, ?, ?, ?, ?)");

				statement.setString(1, profile.getStudentNo());
				statement.setString(2, profile.getName());
				statement.setString(3, profile.getAddress());
				statement.setString(4, profile.getPhone());
				statement.setString(5, profile.getEmail());

				if (photoBytes != null && photoBytes.length > 0) {
					statement.setBytes(6, photoBytes);
					statement.setString(7, photoMime);
				} else {
					statement.setNull(6, java.sql.Types.BLOB);
					statement.setNull(7, java.sql.Types.VARCHAR);
				}

			} else {

				if (photoBytes != null && photoBytes.length > 0) {

					// 写真も含めて更新
					statement = connection.prepareStatement(
							"UPDATE student_profile" +
									" SET name=?, address=?, phone=?, email=?, photo=?, photo_mime=?" +
									" WHERE student_no=?");

					statement.setString(1, profile.getName());
					statement.setString(2, profile.getAddress());
					statement.setString(3, profile.getPhone());
					statement.setString(4, profile.getEmail());
					statement.setBytes(5, photoBytes);
					statement.setString(6, photoMime);
					statement.setString(7, profile.getStudentNo());

				} else {

					// 写真以外を更新
					statement = connection.prepareStatement(
							"UPDATE student_profile" +
									" SET name=?, address=?, phone=?, email=?" +
									" WHERE student_no=?");

					statement.setString(1, profile.getName());
					statement.setString(2, profile.getAddress());
					statement.setString(3, profile.getPhone());
					statement.setString(4, profile.getEmail());
					statement.setString(5, profile.getStudentNo());
				}
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