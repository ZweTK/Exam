package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/*
 * 教員更新画面表示用アクションクラス
 * 指定された教員情報を取得し、更新画面へ渡す
 */
public class TeacherUpdateAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// SchoolDao生成
		SchoolDao scDao = new SchoolDao();

		// 学校コード一覧取得
		List<String> school_num_set = scDao.filter();

		// リクエストから教員ID取得
		String id = request.getParameter("id");

		// TeacherDao生成
		TeacherDao tDao = new TeacherDao();

		// 教員情報取得
		Teacher teacher = tDao.get(id);

		// JSPへ教員情報送信
		request.setAttribute(
				"id",
				teacher.getId());

		request.setAttribute(
				"password",
				teacher.getPassword());

		request.setAttribute(
				"name",
				teacher.getName());

		request.setAttribute(
				"school",
				teacher.getSchool());

		request.setAttribute(
				"school_num_set",
				school_num_set);

		request.setAttribute(
				"manage",
				teacher.isManage());

		// 更新画面へ遷移
		request.getRequestDispatcher(
				"teacher_update.jsp")
				.forward(request, response);
	}
}