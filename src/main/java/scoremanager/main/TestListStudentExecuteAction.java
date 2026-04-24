package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 学生番号取得
		String no = request.getParameter("no");

		// 入力チェック
		if (no == null || no.isEmpty()) {
			request.setAttribute("error", "学生番号を入力してください。");
			request.getRequestDispatcher("test_list_student.jsp")
					.forward(request, response);
			return;
		}

		// 学生情報取得
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.get(no);

		// 学生存在チェック
		if (student == null) {
			request.setAttribute("error", "指定された学生が見つかりません。");
			request.getRequestDispatcher("test_list_student.jsp")
					.forward(request, response);
			return;
		}

		// 成績一覧取得
		TestListStudentDao dao = new TestListStudentDao();
		List<TestListStudent> testList = dao.filter(no);
		System.out.println(testList.size());

		// JSPへ渡す
		request.setAttribute("student", student);
		request.setAttribute("testList", testList);

		// 画面表示
		request.getRequestDispatcher("test_list_student.jsp")
				.forward(request, response);
	}
}