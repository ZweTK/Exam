package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
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
 * 入力された点数をチェックし、問題なければ保存する
 */
public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		/* ログイン中の先生情報取得 */
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		/* 学校情報取得 */
		School school = teacher.getSchool();

		/* DAO生成 */
		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();

		/* 画面から送られた検索条件取得 */
		String subjectCd = req.getParameter("subjectCd");
		String classNum = req.getParameter("classNum");
		int no = Integer.parseInt(req.getParameter("no"));
		int count = Integer.parseInt(req.getParameter("count"));

		/* 科目情報取得 */
		Subject subject = sDao.get(subjectCd, school);

		/* 保存用リスト */
		List<Test> saveList = new ArrayList<>();

		/* 学生ごとのエラー管理 */
		Map<String, String> errorMap = new HashMap<>();

		/* 学生人数分ループ */
		for (int i = 0; i < count; i++) {

			/* 学籍番号取得 */
			String studentNo = req.getParameter("studentNo_" + i);

			/* 点数取得 */
			String pointStr = req.getParameter("point_" + i);

			int point = 0;

			/* 点数が入力されている場合 */
			if (pointStr != null && !pointStr.isEmpty()) {

				point = Integer.parseInt(pointStr);

				/* 0～100以外ならエラー */
				if (point < 0 || point > 100) {

					errorMap.put(
							studentNo,
							"0～100の範囲で入力してください。");
				}
			}

			/* Studentオブジェクト作成 */
			Student student = new Student();
			student.setNo(studentNo);

			/* Testオブジェクト作成 */
			Test test = new Test();
			test.setStudent(student);
			test.setClassNum(classNum);
			test.setSubject(subject);
			test.setSchool(school);
			test.setNo(no);
			test.setPoint(point);

			/* 保存リストへ追加 */
			saveList.add(test);
		}

		/* エラーがある場合 */
		if (!errorMap.isEmpty()) {

			/* エラーメッセージ保存 */
			req.setAttribute("errorMap", errorMap);

			/* 検索条件保持 */
			req.setAttribute("entYear",
					req.getParameter("entYear"));
			req.setAttribute("classNum", classNum);
			req.setAttribute("subjectCd", subjectCd);
			req.setAttribute("no", no);

			/* 入力画面へ戻る */
			req.getRequestDispatcher("TestRegist.action")
					.forward(req, res);
			return;
		}

		/* DB保存 */
		tDao.save(saveList);

		/* 完了画面へ */
		req.getRequestDispatcher("test_regist_done.jsp")
				.forward(req, res);
	}
}