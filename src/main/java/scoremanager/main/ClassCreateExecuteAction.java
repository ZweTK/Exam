package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		if (teacher == null) {
			response.sendRedirect("../login.jsp");
			return;
		}

		// 入力値
		String classNum = request.getParameter("class_num");

		ClassNumDao dao = new ClassNumDao();

		// 既存チェック
		ClassNum exist = dao.get(classNum, teacher.getSchool());

		// 既に存在
		if (exist != null) {

			request.setAttribute("error", "クラスが存在しています");

			request.setAttribute("class_num", classNum);

			request.getRequestDispatcher("classcreate.jsp")
					.forward(request, response);

			return;
		}

		// 登録Bean
		ClassNum c = new ClassNum();
		c.setClass_num(classNum);
		c.setSchool(teacher.getSchool());

		// 登録
		dao.save(c);

		response.sendRedirect("ClassList.action");
	}
}