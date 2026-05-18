package scoremanager.main;

import bean.Student;
import bean.StudentProfile;
import dao.StudentDao;
import dao.StudentProfileDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/*
 * 学生プロフィール表示アクションクラス
 * 学生番号を受け取りプロフィール情報を取得して表示画面へ遷移する
 */
public class StudentProfileAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 学生番号取得
		String no = request.getParameter("no");

		// 学生基本情報取得
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.get(no);

		// プロフィール情報取得（未登録の場合は null）
		StudentProfileDao profileDao = new StudentProfileDao();
		StudentProfile profile = profileDao.get(no);

		// JSPへ渡す
		request.setAttribute("student", student);
		request.setAttribute("profile", profile);

		// プロフィール画面へ遷移
		request.getRequestDispatcher(
				"/scoremanager/main/student_profile.jsp")
				.forward(request, response);
	}
}