package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
    	
		// 未ログイン時はログイン画面へ
		if (teacher == null) {
			response.sendRedirect("../login.jsp");
			return;
		}
        
        // 科目コード取得
        String cd = request.getParameter("cd");
        
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool_cd(teacher.getSchool().getCd());
        
        // DAO
        SubjectDao dao = new SubjectDao();

        // 削除実行
        dao.delete(subject);
        

        // 完了画面へ
        request.getRequestDispatcher("subject_delete_done.jsp")
               .forward(request, response);
    }
}