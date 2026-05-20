package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attend;
import bean.Teacher;
import dao.AttendDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class AttendExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        Teacher teacher =
            (Teacher) req.getSession()
                         .getAttribute("user");

        // 学生番号取得
        String[] studentNos =
            req.getParameterValues("student_no");

        // 日付取得
        String dateStr =
            req.getParameter("date");

        // 日付未入力チェック
        if (dateStr == null ||
            dateStr.isEmpty()) {

            req.setAttribute(
                "error",
                "日付を入力してください"
            );

            req.getRequestDispatcher(
                "attend_set.jsp"
            ).forward(req, res);

            return;
        }

        // 学生取得チェック
        if (studentNos == null ||
            studentNos.length == 0) {

            req.setAttribute(
                "error",
                "学生データがありません"
            );

            req.getRequestDispatcher(
                "attend_set.jsp"
            ).forward(req, res);

            return;
        }

        // 日付変換
        LocalDate date =
            LocalDate.parse(dateStr);

        AttendDao aDao =
            new AttendDao();

        List<Attend> list =
            new ArrayList<>();

        for (String studentNo : studentNos) {

            // 出欠取得
            String status =
                req.getParameter(
                    "status_" + studentNo
                );

            Attend a =
                new Attend();

            a.setStudentNo(studentNo);

            a.setAttendDate(date);

            a.setStatus(status);

            a.setSchoolCd(
                teacher.getSchool().getCd()
            );

            list.add(a);
        }

        // 保存
        aDao.save(list);

        // 完了メッセージ
        req.getSession().setAttribute(
            "message",
            "登録が完了しました"
        );

        // 一覧へ
        res.sendRedirect(
            "AttendList.action"
        );
    }
}