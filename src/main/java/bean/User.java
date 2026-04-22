package bean;

/*
 * ユーザー共通情報を管理するクラス
 * 認証状態を保持する親クラス
 */
public class User {

	/*
	 * 認証済みフラグ
	 * true  : 認証済み
	 * false : 未認証
	 */
	private boolean isAuthenticated;

	/*
	 * 認証状態を取得するメソッド
	 */
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	/*
	 * 認証状態を設定するメソッド
	 */
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}