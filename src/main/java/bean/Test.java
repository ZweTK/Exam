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

	/** 回数 */
	private int no;

	/** 点数 */
	private Integer point;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
}