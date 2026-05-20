package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attend;
import bean.Student;
import bean.Teacher;
import dao.AttendDao;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendListAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        HttpSession session =
            req.getSession();

        Teacher teacher =
            (Teacher) session.getAttribute("user");

        // クラス一覧
        ClassNumDao cDao =
            new ClassNumDao();

        req.setAttribute(
            "classList",
            cDao.filter(
                teacher.getSchool()));

        // パラメータ
        String classNum =
            req.getParameter("class_num");

        String dateStr =
            req.getParameter("date");

        String studentNo =
            req.getParameter("student_no");

        AttendDao aDao =
            new AttendDao();

        List<Attend> attendList =
            new ArrayList<>();

        // 学生番号検索
        if (studentNo != null &&
            !studentNo.isEmpty()) {

            attendList =
                aDao.filterByStudent(
                    studentNo
                );

            req.setAttribute(
                "studentNo",
                studentNo);
        }

        // クラス＋日付検索
        else if (classNum != null &&
                 !classNum.isEmpty() &&
                 dateStr != null &&
                 !dateStr.isEmpty()) {

            // 日付変換
            LocalDate date =
                LocalDate.parse(dateStr);

            StudentDao sDao =
                new StudentDao();

            List<Student> students =
                sDao.getClass(
                    classNum,
                    teacher.getSchool());

            attendList =
                aDao.filter(
                    classNum,
                    date
                );

            req.setAttribute(
                "students",
                students);

            req.setAttribute(
                "classNum",
                classNum);

            req.setAttribute(
                "date",
                dateStr);
        }

        req.setAttribute(
            "attendList",
            attendList);

        req.getRequestDispatcher(
            "attend_list.jsp")
            .forward(req, res);
    }
}