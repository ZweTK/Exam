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

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // クラス一覧
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(teacher.getSchool());
        req.setAttribute("classList", classList);

        // パラメータ
        String classNum = req.getParameter("class_num");
        String date = req.getParameter("date");

        // ★ 検索ボタンが押された（class_num が送られてきた）場合のみチェック
        if (req.getParameter("class_num") != null) {

            boolean classEmpty = (classNum == null || classNum.isEmpty());
            boolean dateEmpty = (date == null || date.isEmpty());

            // ★ 両方未入力
            if (classEmpty && dateEmpty) {
                req.setAttribute("error", "クラスと日付を入力してください");
                req.setAttribute("classNum", classNum);
                req.setAttribute("date", date);
                req.getRequestDispatcher("attend_set.jsp").forward(req, res);
                return;
            }

            // ★ クラス未入力
            if (classEmpty) {
                req.setAttribute("error", "クラスを入力してください");
                req.setAttribute("classNum", classNum);
                req.setAttribute("date", date);
                req.getRequestDispatcher("attend_set.jsp").forward(req, res);
                return;
            }

            // ★ 日付未入力
            if (dateEmpty) {
                req.setAttribute("error", "日付を入力してください");
                req.setAttribute("classNum", classNum);
                req.setAttribute("date", date);
                req.getRequestDispatcher("attend_set.jsp").forward(req, res);
                return;
            }

            // ★ ここまで来たら両方入力済み → 学生取得
            StudentDao sDao = new StudentDao();
            List<Student> students =
                sDao.getClass(classNum, teacher.getSchool());

            req.setAttribute("students", students);
        }

        req.setAttribute("classNum", classNum);
        req.setAttribute("date", date);

        req.getRequestDispatcher("attend_set.jsp").forward(req, res);
    }
}
