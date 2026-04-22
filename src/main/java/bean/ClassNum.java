package bean;

import java.io.Serializable;

/*
 * クラス番号情報を管理するBeanクラス
 * Serializableを実装し、オブジェクトの保存や送信を可能にする
 */
public class ClassNum implements Serializable {

	// クラス番号（例: A組、101など）
	private String class_num;

	// 所属学校情報
	private School school;

	/*
	 * クラス番号を取得するメソッド
	 */
	public String getClass_num() {
		return class_num;
	}

	/*
	 * クラス番号を設定するメソッド
	 */
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}

	/*
	 * 学校情報を取得するメソッド
	 */
	public School getSchool() {
		return school;
	}

	/*
	 * 学校情報を設定するメソッド
	 */
	public void setSchool(School school) {
		this.school = school;
	}
}