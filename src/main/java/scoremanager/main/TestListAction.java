package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action{
	public void executte(HttpServletRequest req, HttpServletResponse res)throws Exception {
		//セッションからユーザーデーターを取得
		Httpsession session = req.getSession();
		
		//ログインユーザーデーター取得
		
		Teacher teacher = (Teacher)session.getAttribute("user");
		//未ログイン時ログイン画面へ
		if (teacher == null) {
			res.sendRedirect("..login/.jsp");
			return;
			
		//ClassNumDao作成
		ClassNumDao cDao = new ClassNumDao();
		
		//指定学校に所属するクラス番号一覧を取得するメソッド
		List<String> school_class = cDao.get(teacher.getClass(), teacher.getSchool());
		
		//SubjectDao作成
		SubjectDao sDao = new SubjectDao();
		
		
		}
	}
}