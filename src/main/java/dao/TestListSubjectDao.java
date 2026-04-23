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

    private static final String baseSql =
        "select s.ent_year, s.class_num, s.no as student_no, " +
        "       t.no as test_no, t.point " +
        "from student s " +
        "join test t on s.no = t.student_no and s.school_cd = t.school_cd " +
        "where s.school_cd = ? ";

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        List<TestListSubject> list = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder(baseSql);

            if (entYear != 0) sql.append("and s.ent_year = ? ");
            if (classNum != null && !classNum.isEmpty()) sql.append("and s.class_num = ? ");
            if (subject != null) sql.append("and t.subject_cd = ? ");

            sql.append("order by s.ent_year, s.class_num, s.no, t.no");

            st = con.prepareStatement(sql.toString());

            int idx = 1;
            st.setString(idx++, school.getCd());
            if (entYear != 0) st.setInt(idx++, entYear);
            if (classNum != null && !classNum.isEmpty()) st.setString(idx++, classNum);
            if (subject != null) st.setString(idx++, subject.getCd());

            rs = st.executeQuery();

            list = postFilter(rs);

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return list;
    }

    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {

        List<TestListSubject> list = new ArrayList<>();
        TestListSubject bean = null;
        Map<Integer, Integer> points = null;

        String currentStudent = null;

        while (rs.next()) {

            String studentNo = rs.getString("student_no");

            if (!studentNo.equals(currentStudent)) {

                if (bean != null) {
                    bean.putPoints(points);
                    list.add(bean);
                }

                bean = new TestListSubject();
                bean.setEntYear(rs.getInt("ent_year"));
                bean.setClassNum(rs.getString("class_num"));
                bean.setStudentNo(studentNo);

                points = new LinkedHashMap<>();
                currentStudent = studentNo;
            }

            points.put(rs.getInt("test_no"), rs.getInt("point"));
        }

        if (bean != null) {
            bean.putPoints(points);
            list.add(bean);
        }

        return list;
    }
}
