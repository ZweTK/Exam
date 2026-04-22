package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/*
 * 学生一覧表示アクションクラス
 * 条件検索を行い学生一覧画面へ遷移する
 */
public class StudentListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログイン中教員情報取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 検索条件初期化
		String entYearStr = "";
		String classNum = "";
		String isAttendStr = "";

		int entYear = 0;
		boolean isAttend = false;

		// 学生一覧
		List<Student> students = null;

		// 現在年取得
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		// DAO生成
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();

		// エラーメッセージ格納
		Map<String, String> errors = new HashMap<>();

		// リクエストパラメータ取得
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");

		// 入学年度変換
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		/*
		 * 入学年度一覧作成
		 */
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// クラス一覧取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		/*
		 * 検索条件によって処理分岐
		 */
		if (entYear != 0 &&
				!classNum.equals("0")) {

			// 年度＋クラス指定
			students = sDao.filter(
					teacher.getSchool(),
					entYear,
					classNum,
					isAttend);

		} else if (entYear != 0 &&
				classNum.equals("0")) {

			// 年度のみ指定
			students = sDao.filter(
					teacher.getSchool(),
					entYear,
					isAttend);

		} else if (entYear == 0 &&
				classNum == null
				|| entYear == 0 &&
						classNum.equals("0")) {

			// 条件なし
			students = sDao.filter(
					teacher.getSchool(),
					isAttend);

		} else {

			// クラスのみ指定はエラー
			errors.put(
					"f",
					"クラスを指定する場合は入学年度も指定してください");

			request.setAttribute(
					"errors",
					errors);

			students = sDao.filter(
					teacher.getSchool(),
					isAttend);
		}

		// 画面保持値
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);

		// 在学中チェック
		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute(
					"f3",
					isAttendStr);
		}

		// JSPへ送信
		request.setAttribute(
				"students",
				students);

		request.setAttribute(
				"class_num_set",
				list);

		request.setAttribute(
				"ent_year_set",
				entYearSet);

		// 一覧画面へ遷移
		request.getRequestDispatcher(
				"student_list.jsp")
				.forward(request, response);
	}
}