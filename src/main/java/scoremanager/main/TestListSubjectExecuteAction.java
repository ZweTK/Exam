package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		// セッション取得
		HttpSession session = req.getSession();

		// ログイン先生情報
		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ取得
		String entYearStr = req.getParameter("f1");

		String classNum = req.getParameter("f2");

		String subjectCd = req.getParameter("f3");

		int entYear = 0;

		Subject subject = new Subject();

		List<TestListSubject> testList = null;

		List<Subject> subjectList = null;

		List<String> classNumList = null;

		Map<String, String> errors = new HashMap<>();

		// 入学年度一覧
		int year = LocalDate.now().getYear();

		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {

			entYearSet.add(i);
		}

		// DAO
		SubjectDao sDao = new SubjectDao();

		ClassNumDao cDao = new ClassNumDao();

		TestListSubjectDao tDao = new TestListSubjectDao();

		// 初期データ
		subjectList = sDao.filter(
				teacher.getSchool());

		classNumList = cDao.filter(
				teacher.getSchool());

		// 入力チェック
		if (entYearStr != null &&
				classNum != null &&
				subjectCd != null &&
				!entYearStr.isEmpty() &&
				!classNum.isEmpty() &&
				!subjectCd.isEmpty()) {

			entYear = Integer.parseInt(
					entYearStr);

			subject = sDao.get(subjectCd);

			testList = tDao.filter(
					entYear,
					classNum,
					subject,
					teacher.getSchool());

			if (testList == null ||
					testList.isEmpty()) {

				errors.put(
						"f",
						"成績情報がありません。");
			}

		} else {

			errors.put(
					"f",
					"入学年度・クラス・科目を選択してください。");
		}

		// 最大回数取得
		int maxNo = 0;

		if (testList != null) {

			for (TestListSubject row : testList) {

				for (Integer key : row.getPoints()
						.keySet()) {

					if (key > maxNo) {
						maxNo = key;
					}
				}
			}
		}

		// 画面保持値
		req.setAttribute(
				"f1",
				entYear);

		req.setAttribute(
				"f2",
				classNum);

		req.setAttribute(
				"f3",
				subject);

		// JSPへ渡す
		req.setAttribute(
				"ent_year_set",
				entYearSet);

		req.setAttribute(
				"class_num_set",
				classNumList);

		req.setAttribute(
				"subject_set",
				subjectList);

		req.setAttribute(
				"testList",
				testList);

		req.setAttribute(
				"errors",
				errors);

		req.setAttribute(
				"maxNo",
				maxNo);

		// JSP表示
		req.getRequestDispatcher(
				"test_list_subject.jsp")
				.forward(req, res);
	}
}