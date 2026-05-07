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
 */
public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		/* ログイン中先生取得 */
		Teacher teacher = (Teacher) req.getSession()
				.getAttribute("user");

		/* DAO */
		ClassNumDao cDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();

		/* 年取得 */
		int year = LocalDate.now().getYear();

		/* 入学年度リスト */
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {

			entYearSet.add(i);
		}

		/* 回数リスト */
		List<Integer> noSet = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {

			noSet.add(i);
		}

		/* 共通データ */
		req.setAttribute(
				"entYearSet",
				entYearSet);

		req.setAttribute(
				"noSet",
				noSet);

		req.setAttribute(
				"classList",
				cDao.filter(
						teacher.getSchool()));

		req.setAttribute(
				"subjectList",
				sDao.filter(
						teacher.getSchool()));

		/* 検索条件取得 */
		String entYearStr = req.getParameter("entYear");

		String classNum = req.getParameter("classNum");

		String subjectCd = req.getParameter("subjectCd");

		String noStr = req.getParameter("no");

		/* forward値優先 */
		if (req.getAttribute("entYear") != null) {
			entYearStr = req.getAttribute("entYear")
					.toString();
		}

		if (req.getAttribute("classNum") != null) {
			classNum = req.getAttribute("classNum")
					.toString();
		}

		if (req.getAttribute("subjectCd") != null) {
			subjectCd = req.getAttribute("subjectCd")
					.toString();
		}

		if (req.getAttribute("no") != null) {
			noStr = req.getAttribute("no")
					.toString();
		}

		/* testsが既にある場合 */
		if (req.getAttribute("tests") != null) {

			req.getRequestDispatcher(
					"test_regist.jsp")
					.forward(req, res);

			return;
		}

		/* 全選択時 */
		if (entYearStr != null &&
				classNum != null &&
				subjectCd != null &&
				noStr != null &&
				!entYearStr.equals("0") &&
				!classNum.equals("0") &&
				!subjectCd.equals("0") &&
				!noStr.equals("0")) {

			int entYear = Integer.parseInt(entYearStr);

			int no = Integer.parseInt(noStr);

			/* 科目 */
			Subject subject = sDao.getSchool(
					subjectCd,
					teacher.getSchool());

			/* 学生一覧 */
			List<Test> tests = tDao.filter(
					entYear,
					classNum,
					subject,
					no,
					teacher.getSchool());

			/* JSPへ */
			req.setAttribute(
					"tests",
					tests);

			req.setAttribute(
					"entYear",
					entYear);

			req.setAttribute(
					"classNum",
					classNum);

			req.setAttribute(
					"subjectCd",
					subjectCd);

			req.setAttribute(
					"subjectName",
					subject.getName());

			req.setAttribute(
					"no",
					no);

		}
		/* 未選択 */
		else if (entYearStr != null) {

			req.setAttribute(
					"error",
					"入学年度・クラス・科目・回数を選択してください");
		}

		/* JSP */
		req.getRequestDispatcher(
				"test_regist.jsp")
				.forward(req, res);
	}
}