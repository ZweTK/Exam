package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログインユーザー取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// クラス一覧取得
		ClassNumDao cDao = new ClassNumDao();

		List<String> clist = cDao.filter(
				teacher.getSchool());

		// 科目一覧取得
		SubjectDao sDao = new SubjectDao();

		List<Subject> subject = sDao.filter(
				teacher.getSchool());

		// 現在年取得
		int year = LocalDate.now()
				.getYear();

		// 入学年度一覧
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {

			entYearSet.add(i);
		}

		// JSPへ渡す
		request.setAttribute(
				"class_num_set",
				clist);

		request.setAttribute(
				"ent_year_set",
				entYearSet);

		request.setAttribute(
				"subject_set",
				subject);

		// 学生番号取得
		String no = request.getParameter("no");

		// 入力チェック
		if (no == null ||
				no.isEmpty()) {

			request.setAttribute(
					"error",
					"学生番号を入力してください。");

			request.getRequestDispatcher(
					"test_list_student.jsp")
					.forward(
							request,
							response);

			return;
		}

		// 学生情報取得
		StudentDao studentDao = new StudentDao();

		Student student = studentDao.get(no);

		// 学生存在チェック
		if (student == null) {

			request.setAttribute(
					"error",
					"指定された学生が見つかりません。");

			request.getRequestDispatcher(
					"test_list_student.jsp")
					.forward(
							request,
							response);

			return;
		}

		// 成績一覧取得
		TestListStudentDao dao = new TestListStudentDao();

		List<TestListStudent> testList = dao.filter(no);

		request.setAttribute(
				"student",
				student);

		request.setAttribute(
				"testList",
				testList);

		// 表示
		request.getRequestDispatcher(
				"test_list_student.jsp")
				.forward(
						request,
						response);
	}
}