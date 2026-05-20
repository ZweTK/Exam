package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendSetAction extends Action {

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

        List<String> classList =
            cDao.filter(
                teacher.getSchool());

        req.setAttribute(
            "classList",
            classList);

        // パラメータ
        String classNum =
            req.getParameter("class_num");

        String date =
            req.getParameter("date");

        // クラス選択時だけ学生取得
        if (classNum != null &&
            !classNum.isEmpty()) {

            StudentDao sDao =
                new StudentDao();

            List<Student> students =
                sDao.getClass(
                    classNum,
                    teacher.getSchool());

            req.setAttribute(
                "students",
                students);
        }

        req.setAttribute(
            "classNum",
            classNum);

        req.setAttribute(
            "date",
            date);

        req.getRequestDispatcher(
            "attend_set.jsp")
            .forward(req, res);
    }
}