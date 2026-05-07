package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/*
 * 学生登録実行用アクションクラス
 * 入力チェック後、学生情報を登録する
 */
public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッション取得
		HttpSession session = request.getSession();

		// ログイン中教員取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 入力値取得
		String entYearStr = request.getParameter("ent_year");
		String classNum = request.getParameter("class_num");
		String no = request.getParameter("no");
		String name = request.getParameter("name");

		// 現在年取得
		LocalDate today = LocalDate.now();
		int year = today.getYear();

		// 入学年度一覧生成
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// クラス一覧取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> classList = cDao.filter(teacher.getSchool());

		// エラーフラグ
		boolean hasError = false;

		// 入学年度
		int entYear = 0;

		/*
		 * 入学年度チェック
		 */
		if (entYearStr == null || entYearStr.equals("0")) {

			request.setAttribute(
					"entYearError",
					"入学年度を選択してください");

			hasError = true;

		} else {

			entYear = Integer.parseInt(entYearStr);
		}

		/*
		 * クラスチェック
		 */
		if (classNum == null || classNum.isEmpty()) {

			request.setAttribute(
					"classNumError",
					"クラスを選択してください");

			hasError = true;
		}

		/*
		 * 氏名チェック
		 */
		if (name == null || name.isEmpty()) {

			request.setAttribute(
					"nameError",
					"氏名を入力してください");

			hasError = true;
		}

		/*
		 * 学生番号チェック
		 */
		StudentDao sDao = new StudentDao();

		if (no == null || no.isEmpty()) {

			request.setAttribute(
					"noError",
					"学生番号を入力してください");

			hasError = true;

		} else {

			// 重複チェック
			Student studentCheck = sDao.get(no);

			if (studentCheck != null) {

				request.setAttribute(
						"noError",
						"学生番号が重複しています");

				hasError = true;
			}
		}

		/*
		 * エラー時は入力画面へ戻る
		 */
		if (hasError) {

			// 再表示用データ設定
			request.setAttribute(
					"ent_year_set",
					entYearSet);

			request.setAttribute(
					"class_num_set",
					classList);

			// 入力値保持
			request.setAttribute("f1", entYearStr);
			request.setAttribute("class_num", classNum);
			request.setAttribute("no", no);
			request.setAttribute("name", name);

			// 入力画面へ戻る
			request.getRequestDispatcher(
					"student_create.jsp")
					.forward(request, response);

			return;
		}

		/*
		 * 学生情報作成
		 */
		Student student = new Student();

		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(true);
		student.setSchool(teacher.getSchool());

		/*
		 * DB保存
		 */
		StudentDao dao = new StudentDao();
		dao.save(student);

		/*
		 * 完了画面へ
		 */
		request.getRequestDispatcher(
				"student_create_done.jsp")
				.forward(request, response);
	}
}