package bean;

import java.io.Serializable;

/**
 * テスト（点数）情報を管理するBeanクラス
 */
public class Test implements Serializable {

	/** 学生情報 */
	private Student student;

	/** クラス番号 */
	private String classNum;

	/** 科目情報 */
	private Subject subject;

	/** 学校情報 */
	private School school;

	/** 回数（第1回、第2回など） */
	private int no;

	/** 点数 */
	private int point;

	/**
	 * 学生情報を取得
	 * @return student 学生情報
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 学生情報を設定
	 * @param student 学生情報
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * クラス番号を取得
	 * @return classNum クラス番号
	 */
	public String getClassNum() {
		return classNum;
	}

	/**
	 * クラス番号を設定
	 * @param classNum クラス番号
	 */
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	/**
	 * 科目情報を取得
	 * @return subject 科目情報
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * 科目情報を設定
	 * @param subject 科目情報
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * 学校情報を取得
	 * @return school 学校情報
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * 学校情報を設定
	 * @param school 学校情報
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * テスト回数を取得
	 * @return no 回数
	 */
	public int getNo() {
		return no;
	}

	/**
	 * テスト回数を設定
	 * @param no 回数
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * 点数を取得
	 * @return point 点数
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * 点数を設定
	 * @param point 点数
	 */
	public void setPoint(int point) {
		this.point = point;
	}
}