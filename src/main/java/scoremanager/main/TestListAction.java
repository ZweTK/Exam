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
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
		//セッションからユーザーデーターを取得
		HttpSession session = req.getSession();
		
		//ログインユーザーデーター取得
		Teacher teacher = (Teacher)session.getAttribute("user");
			
		//ClassNumDao作成
		ClassNumDao cDao = new ClassNumDao();
		
		//指定するクラス番号と学校コードからクラス情報を取得するメソッド
		// クラス一覧取得
		List<String> clist = cDao.filter(teacher.getSchool());

		//SubjectDao作成
		SubjectDao sDao = new SubjectDao();
		
		//科目コードで科目を一見取得		
		//ユーザーが所属する学校の科目一覧取得
		List<Subject> subject = sDao.filter(teacher.getSchool());
		
		// 現在年取得
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		
		/*
		 * 入学年度一覧作成
		 */
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		
		//入学年度リスト作成
		req.setAttribute(
				"class_num_set",
				clist);

		req.setAttribute(
				"ent_year_set",
				entYearSet);
		req.setAttribute(
				"subject_set",
				subject);
		
		//収集したデータをリクエストに設定
		req.getRequestDispatcher(
				"test_list.jsp")
				.forward(req, res);
		

	}
}