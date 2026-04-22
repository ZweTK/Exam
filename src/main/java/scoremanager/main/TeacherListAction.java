package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TeacherDao dao = new TeacherDao();

		// teacherテーブル全件取得用
		List<Teacher> teachers = dao.filter();

		// JSPへ渡す
		request.setAttribute("teachers", teachers);

		// JSP表示
		request.getRequestDispatcher("teacher_list.jsp")
				.forward(request, response);
	}
}