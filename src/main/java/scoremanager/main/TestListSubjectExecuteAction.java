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
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		//セッション取得
		HttpSession session = req.getSession();
		
		//ログイン中の先生情報取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		//検索情報初期化
		String entYearStr = "";
		String classNum = "";
		String subjectStr = "";
		
		Subject	subject = new Subject();
		int entYear = 0;
		
		//科目別成績一覧一覧
		List<TestListSubject> test = null;
		
		//科目一覧
		List<Subject> subjectList = null;
		
		//クラス一覧
		List<String> classNumList = null;
		
		//現在時刻取得
		LocalDate todayDate = LocalDate.now();
		int year = todayDate.getYear();
		
		/* 入学年度リスト作成（今年から10年前まで） */
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}
		
		//Dao生成
		TestListSubjectDao tDao = new TestListSubjectDao();
		SubjectDao sDao = new SubjectDao();
		ClassNumDao cDao = new ClassNumDao();
		
		
		//エラーーメッセージ
		Map<String, String> errors = new HashMap<>();

		//科目変換
		subjectList = sDao.filter(teacher.getSchool());
		//クラス変換
		classNumList = cDao.filter(teacher.getSchool());

		
		//リクエストパラメーター
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectStr = req.getParameter("f3");

		//入力チェック
		if (entYearStr != null &&
				classNum != null &&
				subjectStr != null &&
				entYearStr.equals("0") &&
				classNum.equals("0") &&
				subjectStr.equals("0")) {
			

			//入学年度変換
			entYear = Integer.parseInt(entYearStr);
			//科目変換
			subject.setCd(subjectStr);
			
			test = tDao.filter(
					entYear,
					classNum,
					subject,
					teacher.getSchool()
					) ;
			
		}else {
			errors.put("f", "入学年度とクラスと科目を選択してください");
			req.setAttribute("errors", errors);
		}
		if (test != null) {
			errors.put("f", "学生情報が存在しませんでした");
			req.setAttribute("errors", errors);
		}
		
		//画面保持値
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", subject);
		
		//jspへ返す
		req.setAttribute("entYearSet", entYearSet);
		req.setAttribute("classNumList", classNumList);
		req.setAttribute("subjectList", subjectList);
		req.setAttribute("testList", test);


		

		/* JSP表示 */
		req.getRequestDispatcher("test_list_subject.jsp")
				.forward(req, res);
	}
}
//エラーメッセージの時の処理　private subject型nullかどうか