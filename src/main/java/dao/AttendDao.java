package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attend;
import util.DBUtil;

public class AttendDao {

    // 出欠登録
    public boolean save(List<Attend> list)
        throws Exception {

        Connection con =
            DBUtil.getConnection();

        // 重複確認
        String checkSql =
            "SELECT COUNT(*) " +
            "FROM ATTEND " +
            "WHERE STUDENT_NO=? " +
            "AND ATTEND_DATE=?";

        // 新規登録
        String insertSql =
            "INSERT INTO ATTEND " +
            "(STUDENT_NO, ATTEND_DATE, STATUS, SCHOOL_CD) " +
            "VALUES (?, ?, ?, ?)";

        // 上書き
        String updateSql =
            "UPDATE ATTEND " +
            "SET STATUS=?, SCHOOL_CD=? " +
            "WHERE STUDENT_NO=? " +
            "AND ATTEND_DATE=?";

        PreparedStatement checkSt =
            con.prepareStatement(checkSql);

        PreparedStatement insertSt =
            con.prepareStatement(insertSql);

        PreparedStatement updateSt =
            con.prepareStatement(updateSql);

        for (Attend a : list) {

            // 重複確認
            checkSt.setString(
                1,
                a.getStudentNo());

            checkSt.setDate(
                2,
                java.sql.Date.valueOf(
                    a.getAttendDate()));

            ResultSet rs =
                checkSt.executeQuery();

            rs.next();

            // 既に存在 → UPDATE
            if (rs.getInt(1) > 0) {

                updateSt.setString(
                    1,
                    a.getStatus());

                updateSt.setString(
                    2,
                    a.getSchoolCd());

                updateSt.setString(
                    3,
                    a.getStudentNo());

                updateSt.setDate(
                    4,
                    java.sql.Date.valueOf(
                        a.getAttendDate()));

                updateSt.addBatch();
            }

            // 無い → INSERT
            else {

                insertSt.setString(
                    1,
                    a.getStudentNo());

                insertSt.setDate(
                    2,
                    java.sql.Date.valueOf(
                        a.getAttendDate()));

                insertSt.setString(
                    3,
                    a.getStatus());

                insertSt.setString(
                    4,
                    a.getSchoolCd());

                insertSt.addBatch();
            }

            rs.close();
        }

        insertSt.executeBatch();

        updateSt.executeBatch();

        checkSt.close();
        insertSt.close();
        updateSt.close();
        con.close();

        return true;
    }

    // クラス＋日付検索
    public List<Attend> filter(
            String classNum,
            LocalDate date)
            throws Exception {

        List<Attend> list =
            new ArrayList<>();

        Connection con =
            DBUtil.getConnection();

        String sql =
            "SELECT a.* " +
            "FROM ATTEND a " +
            "INNER JOIN STUDENT s " +
            "ON a.STUDENT_NO=s.NO " +
            "WHERE s.CLASS_NUM=? " +
            "AND a.ATTEND_DATE=? " +
            "ORDER BY a.STUDENT_NO";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(
            1,
            classNum);

        st.setDate(
            2,
            java.sql.Date.valueOf(date));

        ResultSet rs =
            st.executeQuery();

        while (rs.next()) {

            Attend a =
                new Attend();

            a.setId(
                rs.getInt("ID"));

            a.setStudentNo(
                rs.getString(
                    "STUDENT_NO"));

            a.setAttendDate(
                rs.getDate(
                    "ATTEND_DATE")
                    .toLocalDate());

            a.setStatus(
                rs.getString(
                    "STATUS"));

            a.setSchoolCd(
                rs.getString(
                    "SCHOOL_CD"));

            list.add(a);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 学生番号＋日付検索
    public List<Attend> filterByStudentAndDate(
            String studentNo,
            LocalDate date)
            throws Exception {

        List<Attend> list =
            new ArrayList<>();

        Connection con =
            DBUtil.getConnection();

        String sql =
            "SELECT * FROM ATTEND " +
            "WHERE STUDENT_NO=? " +
            "AND ATTEND_DATE=?";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(
            1,
            studentNo);

        st.setDate(
            2,
            java.sql.Date.valueOf(date));

        ResultSet rs =
            st.executeQuery();

        while (rs.next()) {

            Attend a =
                new Attend();

            a.setId(
                rs.getInt("ID"));

            a.setStudentNo(
                rs.getString(
                    "STUDENT_NO"));

            a.setAttendDate(
                rs.getDate(
                    "ATTEND_DATE")
                    .toLocalDate());

            a.setStatus(
                rs.getString(
                    "STATUS"));

            a.setSchoolCd(
                rs.getString(
                    "SCHOOL_CD"));

            list.add(a);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 学生番号検索
    public List<Attend> filterByStudent(
            String studentNo)
            throws Exception {

        List<Attend> list =
            new ArrayList<>();

        Connection con =
            DBUtil.getConnection();

        String sql =
            "SELECT * FROM ATTEND " +
            "WHERE STUDENT_NO=? " +
            "ORDER BY ATTEND_DATE";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(
            1,
            studentNo);

        ResultSet rs =
            st.executeQuery();

        while (rs.next()) {

            Attend a =
                new Attend();

            a.setId(
                rs.getInt("ID"));

            a.setStudentNo(
                rs.getString(
                    "STUDENT_NO"));

            a.setAttendDate(
                rs.getDate(
                    "ATTEND_DATE")
                    .toLocalDate());

            a.setStatus(
                rs.getString(
                    "STATUS"));

            a.setSchoolCd(
                rs.getString(
                    "SCHOOL_CD"));

            list.add(a);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}