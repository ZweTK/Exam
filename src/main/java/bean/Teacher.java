package bean;

import java.io.Serializable;

/*
 * 教員情報を管理するBeanクラス
 * Userクラスを継承し、Serializableを実装している
 */
public class Teacher extends User implements Serializable {

	/*
	 * 教員ID
	 */
	private String id;

	/*
	 * パスワード
	 */
	private String password;

	/*
	 * 教員名
	 */
	private String name;

	/*
	 * 所属学校情報
	 */
	private School school;

	/*
	 * 管理者権限の有無
	 * true : 管理者
	 * false: 一般教員
	 */
	private boolean manage;

	/*
	 * 教員IDを取得するメソッド
	 */
	public String getId() {
		return id;
	}

	/*
	 * 教員IDを設定するメソッド
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * パスワードを取得するメソッド
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * パスワードを設定するメソッド
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * 教員名を取得するメソッド
	 */
	public String getName() {
		return name;
	}

	/*
	 * 教員名を設定するメソッド
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 所属学校情報を取得するメソッド
	 */
	public School getSchool() {
		return school;
	}

	/*
	 * 所属学校情報を設定するメソッド
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/*
	 * 管理者権限を取得するメソッド
	 */
	public boolean isManage() {
		return manage;
	}

	/*
	 * 管理者権限を設定するメソッド
	 */
	public void setManage(boolean manage) {
		this.manage = manage;
	}
}