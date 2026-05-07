package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 成績登録実行Action
 */
public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res)
			throws Exception {

		/* ログイン中先生取得 */
		Teacher teacher = (Teacher) req.getSession()
				.getAttribute("user");

		/* 学校 */
		School school = teacher.getSchool();

		/* DAO */
		SubjectDao sDao = new SubjectDao();

		TestDao tDao = new TestDao();

		/* パラメータ */
		String subjectCd = req.getParameter("subjectCd");

		String classNum = req.getParameter("classNum");

		int entYear = Integer.parseInt(
				req.getParameter("entYear"));

		int no = Integer.parseInt(
				req.getParameter("no"));

		int count = Integer.parseInt(
				req.getParameter("count"));

		/* 科目取得 */
		Subject subject = sDao.getSchool(
				subjectCd,
				school);

		/* DBから一覧取得 */
		List<Test> tests = tDao.filter(
				entYear,
				classNum,
				subject,
				no,
				school);

		/* エラー管理 */
		Map<String, String> errorMap = new HashMap<>();

		/* 入力値反映 */
		for (int i = 0; i < count; i++) {

			/* 学生番号 */
			String studentNo = req.getParameter(
					"studentNo_" + i);

			/* 点数 */
			String pointStr = req.getParameter(
					"point_" + i);

			/* 対象学生検索 */
			for (Test test : tests) {

				if (test.getStudent()
						.getNo()
						.equals(studentNo)) {

					/* 未入力 */
					if (pointStr == null ||
							pointStr.isEmpty()) {

						test.setPoint(null);
						break;
					}

					try {

						int point = Integer.parseInt(pointStr);

						/* 範囲チェック */
						if (point < 0 ||
								point > 100) {

							errorMap.put(
									studentNo,
									"0～100の範囲で入力してください");

						} else {

							/* 正常値のみ反映 */
							test.setPoint(point);
						}

					} catch (NumberFormatException e) {

						errorMap.put(
								studentNo,
								"数字で入力してください");
					}

					break;
				}
			}
		}

		/* エラー */
		if (!errorMap.isEmpty()) {

			req.setAttribute(
					"errorMap",
					errorMap);

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

			req.getRequestDispatcher(
					"test_regist.jsp")
					.forward(req, res);

			return;
		}

		/* 保存 */
		tDao.save(tests);

		/* 完了画面 */
		req.getRequestDispatcher(
				"test_regist_done.jsp")
				.forward(req, res);
	}
}