package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 成績登録画面表示Action
 * 検索条件を受け取り、学生一覧を表示する
 */
public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		/* ログイン中の先生情報取得 */
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		/* DAO生成 */
		ClassNumDao cDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();

		/* 現在年取得 */
		int year = LocalDate.now().getYear();

		/* 入学年度リスト作成（今年から10年前まで） */
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		/* 回数リスト作成（1～10回） */
		List<Integer> noSet = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			noSet.add(i);
		}

		/* JSPへ渡す共通データ */
		req.setAttribute("entYearSet", entYearSet);
		req.setAttribute("noSet", noSet);
		req.setAttribute("classList",
				cDao.filter(teacher.getSchool()));
		req.setAttribute("subjectList",
				sDao.filter(teacher.getSchool()));

		/* 画面から受け取る検索条件 */
		String entYearStr = req.getParameter("entYear");
		String classNum = req.getParameter("classNum");
		String subjectCd = req.getParameter("subjectCd");
		String noStr = req.getParameter("no");

		/* forward時に渡された値も取得 */
		Object entYearObj = req.getAttribute("entYear");
		Object classNumObj = req.getAttribute("classNum");
		Object subjectCdObj = req.getAttribute("subjectCd");
		Object noObj = req.getAttribute("no");

		/* forwardされた値があれば優先 */
		if (entYearObj != null) {
			entYearStr = entYearObj.toString();
		}
		if (classNumObj != null) {
			classNum = classNumObj.toString();
		}
		if (subjectCdObj != null) {
			subjectCd = subjectCdObj.toString();
		}
		if (noObj != null) {
			noStr = noObj.toString();
		}

		/* すべて選択されている場合 */
		if (entYearStr != null &&
				classNum != null &&
				subjectCd != null &&
				noStr != null &&
				!entYearStr.equals("0") &&
				!classNum.equals("0") &&
				!subjectCd.equals("0") &&
				!noStr.equals("0")) {

			/* 数値変換 */
			int entYear = Integer.parseInt(entYearStr);
			int no = Integer.parseInt(noStr);

			/* 科目取得 */
			Subject subject = sDao.getSchool(subjectCd, teacher.getSchool());

			/* 学生一覧取得 */
			List<Test> tests = tDao.filter(
					entYear,
					classNum,
					subject,
					no,
					teacher.getSchool());

			/* JSPへ渡す */
			req.setAttribute("tests", tests);
			req.setAttribute("entYear", entYear);
			req.setAttribute("classNum", classNum);
			req.setAttribute("subjectCd", subjectCd);
			req.setAttribute("subjectName",
					subject.getName());
			req.setAttribute("no", no);

		}
		/* 未選択の場合 */
		else if (entYearStr != null) {

			req.setAttribute(
					"error",
					"入学年度・クラス・科目・回数を選択してください");
		}

		/* JSP表示 */
		req.getRequestDispatcher("test_regist.jsp")
				.forward(req, res);
	}
}