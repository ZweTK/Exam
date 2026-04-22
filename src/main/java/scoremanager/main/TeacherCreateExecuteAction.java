package scoremanager.main;

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherCreateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// リクエストパラメータ取得
		String id = request.getParameter("ID");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String schoolCd = request.getParameter("school_cd");
		String manageStr = request.getParameter("isManage");

		// 管理者チェックボックス判定
		boolean manage = false;

		if (manageStr != null) {
			manage = true;
		}

		// 学校情報取得
		SchoolDao sDao = new SchoolDao();
		School school = sDao.get(schoolCd);

		// Teacherインスタンス生成
		Teacher teacher = new Teacher();

		teacher.setId(id);
		teacher.setPassword(password);
		teacher.setName(name);
		teacher.setSchool(school);
		teacher.setManage(manage);

		// DB保存
		TeacherDao tDao = new TeacherDao();
		tDao.save(teacher);

		// 一覧画面へ戻る
		request.getRequestDispatcher("teacher_create_done.jsp").forward(request, response);
	}
}