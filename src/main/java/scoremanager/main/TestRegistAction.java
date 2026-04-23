package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
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

		// セッションからログインユーザー取得
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		// DAO生成
		ClassNumDao cDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();

		// クラス一覧取得
		List<String> classList = cDao.filter(teacher.getSchool());

		// 科目一覧取得
		List<Subject> subjectList = sDao.filter(teacher.getSchool());

		// 現在年取得
		int year = LocalDate.now().getYear();

		/*
		 * 入学年度一覧作成
		 * 現在年から10年前～今年まで
		 */
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// JSPへセット
		req.setAttribute("classList", classList);
		req.setAttribute("subjectList", subjectList);
		req.setAttribute("entYearSet", entYearSet);

		// 画面表示
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}