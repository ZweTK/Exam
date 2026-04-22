package bean;

import java.io.Serializable;

/**
 * 科目情報を管理するBeanクラス
 * Serializableを実装し、オブジェクトの保存や転送を可能にする
 */
public class Subject implements Serializable {

	// 学校コード
	private String school_cd;

	// 科目コード
	private String cd;

	// 科目名
	private String name;

	/**
	 * 学校コードを取得
	 * @return school_cd 学校コード
	 */
	public String getSchool_cd() {
		return school_cd;
	}

	/**
	 * 学校コードを設定
	 * @param school_cd 学校コード
	 */
	public void setSchool_cd(String school_cd) {
		this.school_cd = school_cd;
	}

	/**
	 * 科目コードを取得
	 * @return cd 科目コード
	 */
	public String getCd() {
		return cd;
	}

	/**
	 * 科目コードを設定
	 * @param cd 科目コード
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}

	/**
	 * 科目名を取得
	 * @return name 科目名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 科目名を設定
	 * @param name 科目名
	 */
	public void setName(String name) {
		this.name = name;
	}
}