package scoremanager.main;

import java.util.List;

import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherCreateAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// SchoolDaoを生成
		SchoolDao scDao = new SchoolDao();

		// 学校コード一覧取得
		List<String> school_num_set = scDao.filter();

		// JSPへ渡す
		request.setAttribute("school_num_set", school_num_set);

		// 登録画面へ遷移
		request.getRequestDispatcher("teacher_create.jsp")
				.forward(request, response);
	}
}