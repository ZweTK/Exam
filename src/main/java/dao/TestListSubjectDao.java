package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends DAO {

	private static final String baseSql = "select s.ent_year, s.class_num, s.no as student_no, s.name as student_name, "
			+ "t.no as test_no, t.point "
			+ "from student s "
			+ "left join test t "
			+ "on s.no = t.student_no and s.school_cd = t.school_cd "
			+ "where s.school_cd = ? ";

	/**
	 * 条件検索メソッド
	 */
	public List<TestListSubject> filter(
			int entYear,
			String classNum,
			Subject subject,
			School school) throws Exception {

		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		List<TestListSubject> list = new ArrayList<>();

		try {
			StringBuilder sql = new StringBuilder(baseSql);

			// 入学年度で絞り込み
			if (entYear != 0) {
				sql.append("and s.ent_year = ? ");
			}

			// クラスで絞り込み
			if (classNum != null && !classNum.isEmpty()) {
				sql.append("and s.class_num = ? ");
			}

			// 科目で絞り込み
			if (subject != null) {
				sql.append("and t.subject_cd = ? ");
			}

			// 並び順（学生単位・テスト回数順）
			sql.append("order by s.ent_year, s.class_num, s.no, t.no");

			st = con.prepareStatement(sql.toString());

			int idx = 1;

			// 学校コード（必須条件）
			st.setString(idx++, school.getCd());

			if (entYear != 0) {
				st.setInt(idx++, entYear);
			}

			if (classNum != null && !classNum.isEmpty()) {
				st.setString(idx++, classNum);
			}

			if (subject != null) {
				st.setString(idx++, subject.getCd());
			}

			rs = st.executeQuery();

			// ResultSetを整形してリスト化
			list = postFilter(rs);

		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}

		return list;
	}

	/**
	 * ResultSetを学生ごとにまとめる処理
	 */
	private List<TestListSubject> postFilter(ResultSet rs) throws Exception {

		List<TestListSubject> list = new ArrayList<>();

		TestListSubject bean = null;

		// テスト回数ごとの点数を保持するMap
		Map<Integer, Integer> points = null;

		String currentStudent = null;

		while (rs.next()) {

			String studentNo = rs.getString("student_no");

			/**
			 * 学生が切り替わった場合、新しいオブジェクトを作成する
			 */
			if (currentStudent == null || !studentNo.equals(currentStudent)) {

				// 前の学生データをリストへ追加
				if (bean != null) {
					bean.putPoints(points);
					list.add(bean);
				}

				// 新しい学生オブジェクトを作成
				bean = new TestListSubject();

				bean.setEntYear(rs.getInt("ent_year"));
				bean.setClassNum(rs.getString("class_num"));
				bean.setStudentNo(studentNo);
				bean.setStudentName(rs.getString("student_name"));

				// 点数格納用Mapを初期化
				points = new LinkedHashMap<>();

				currentStudent = studentNo;
			}

			/**
			 * テスト回数ごとの点数を格納
			 * key = テスト回数（NO）
			 * value = 点数
			 */
			int testNo = rs.getInt("test_no");
			int point = rs.getInt("point");

			points.put(testNo, point);
		}

		// 最後の学生データを追加
		if (bean != null) {
			bean.putPoints(points);
			list.add(bean);
		}

		return list;
	}
}