package scoremanager.main;

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/*
 * 教員更新実行アクションクラス
 * 入力された教員情報を更新する
 */
public class TeacherUpdateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// パラメータ取得
		String id = request.getParameter("id");

		String password = request.getParameter("password");

		String name = request.getParameter("name");

		String schoolCd = request.getParameter("school_num");

		/*
		 * 管理者チェックボックス取得
		 * チェックありならtrue
		 */
		boolean isManage = false;

		if (request.getParameter("manage") != null) {
			isManage = true;
		}

		/*
		 * 学校情報取得
		 */
		SchoolDao schoolDao = new SchoolDao();

		School school = schoolDao.get(schoolCd);

		/*
		 * Teacherオブジェクト作成
		 */
		Teacher teacher = new Teacher();

		teacher.setId(id);
		teacher.setPassword(password);
		teacher.setName(name);
		teacher.setSchool(school);
		teacher.setManage(isManage);

		/*
		 * 更新実行
		 */
		TeacherDao teacherDao = new TeacherDao();

		teacherDao.update(teacher);

		// 完了画面へ遷移
		request.getRequestDispatcher(
				"teacher_update_done.jsp")
				.forward(request, response);
	}
}