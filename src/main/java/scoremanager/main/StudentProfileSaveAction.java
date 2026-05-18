package scoremanager.main;

import bean.Student;
import bean.StudentProfile;
import dao.StudentDao;
import dao.StudentProfileDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

/*
 * 学生プロフィール保存アクションクラス
 * フォームから受け取ったプロフィール情報をDBに保存し、
 * プロフィール表示画面へリダイレクトする
 */
public class StudentProfileSaveAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 学生番号取得
		String no = request.getParameter("no");

		// 入力値取得
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		// エラーフラグ
		boolean hasError = false;

		/*
		 * 電話番号バリデーション
		 * 数字・ハイフンのみ許可、10〜13文字
		 * 例: 090-0000-0000 / 0312345678
		 */
		if (phone != null && !phone.isEmpty()) {

			if (!phone.matches("[0-9\\-]{10,13}")) {

				request.setAttribute(
						"phoneError",
						"電話番号の形式が正しくありません（例: 090-0000-0000）");

				hasError = true;
			}
		}

		/*
		 * メールアドレスバリデーション
		 * 簡易チェック: @が1つ存在し、前後に文字がある
		 */
		if (email != null && !email.isEmpty()) {

			if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {

				request.setAttribute(
						"emailError",
						"メールアドレスの形式が正しくありません（例: student@example.com）");

				hasError = true;
			}
		}

		/*
		 * エラー時はプロフィール画面へ戻る（入力値を保持）
		 */
		if (hasError) {

			// 学生基本情報を再取得
			StudentDao studentDao = new StudentDao();
			Student student = studentDao.get(no);

			// 既存プロフィールを再取得
			StudentProfileDao profileDao = new StudentProfileDao();
			StudentProfile profile = profileDao.get(no);

			// 入力値を上書きしてJSPへ戻す
			if (profile == null) {
				profile = new StudentProfile();
			}

			profile.setStudentNo(no);
			profile.setName(name);
			profile.setAddress(address);
			profile.setPhone(phone);
			profile.setEmail(email);

			request.setAttribute("student", student);
			request.setAttribute("profile", profile);

			request.getRequestDispatcher(
					"/scoremanager/main/student_profile.jsp")
					.forward(request, response);

			return;
		}

		// プロフィールBeanに値をセット
		StudentProfile profile = new StudentProfile();
		profile.setStudentNo(no);
		profile.setName(name);
		profile.setAddress(address);
		profile.setPhone(phone);
		profile.setEmail(email);

		// 写真ファイルの処理
		byte[] photoBytes = null;
		String photoMime = null;

		Part photoPart = request.getPart("photo");
		if (photoPart != null && photoPart.getSize() > 0) {
			photoMime = photoPart.getContentType();
			photoBytes = photoPart.getInputStream().readAllBytes();
		}

		// DB保存
		StudentProfileDao profileDao = new StudentProfileDao();
		profileDao.save(profile, photoBytes, photoMime);

		// プロフィール画面へリダイレクト
		response.sendRedirect("StudentProfile.action?no=" + no);
	}
}