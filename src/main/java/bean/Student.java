package bean;

import java.io.Serializable;

/*
 * 学生情報を管理するBeanクラス
 * Serializableを実装し、データ保存や通信に対応する
 */
public class Student implements Serializable {

	// 学生番号
	private String no;

	// 学生氏名
	private String name;

	// 入学年度
	private int entYear;

	// クラス番号
	private String classNum;

	// 在学中かどうか（true: 在学 / false: 退学など）
	private boolean isAttend;

	// 所属学校情報
	private School school;

	/*
	 * 学生番号を取得するメソッド
	 */
	public String getNo() {
		return no;
	}

	/*
	 * 学生番号を設定するメソッド
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/*
	 * 学生氏名を取得するメソッド
	 */
	public String getName() {
		return name;
	}

	/*
	 * 学生氏名を設定するメソッド
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 入学年度を取得するメソッド
	 */
	public int getEntYear() {
		return entYear;
	}

	/*
	 * 入学年度を設定するメソッド
	 */
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	/*
	 * 在学状態を取得するメソッド
	 */
	public boolean isAttend() {
		return isAttend;
	}

	/*
	 * 在学状態を設定するメソッド
	 */
	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
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

	/*
	 * クラス番号を取得するメソッド
	 */
	public String getClassNum() {
		return classNum;
	}

	/*
	 * クラス番号を設定するメソッド
	 */
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

}