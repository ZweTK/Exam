package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/*
 * 学生登録画面を表示するアクションクラス
 * 入学年度一覧・クラス一覧を取得してJSPへ渡す
 */
public class StudentCreateAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログイン中の教員情報取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 画面入力値取得（再表示用）
		String entYearStr = request.getParameter("ent_year");

		String classNum = request.getParameter("class_num");

		String no = request.getParameter("no");

		String name = request.getParameter("name");

		// 現在年取得
		LocalDate today = LocalDate.now();
		int year = today.getYear();

		// 入学年度一覧格納用
		List<Integer> entYearSet = new ArrayList<>();

		// 現在年から10年前まで生成
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// クラス一覧取得
		ClassNumDao cDao = new ClassNumDao();

		List<String> classList = cDao.filter(teacher.getSchool());

		// JSPへデータ送信
		request.setAttribute(
				"ent_year_set",
				entYearSet);

		request.setAttribute(
				"class_num_set",
				classList);

		request.setAttribute("f1", entYearStr);
		request.setAttribute("class_num", classNum);
		request.setAttribute("no", no);
		request.setAttribute("name", name);

		// 学生登録画面へ遷移
		request.getRequestDispatcher(
				"student_create.jsp")
				.forward(request, response);
	}
}