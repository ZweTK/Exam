package bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 学生別成績一覧Bean
 */
public class TestListSubject implements Serializable {

	private int entYear; // 入学年度
	private String studentNo; // 学生番号
	private String classNum; // クラス番号
	private Map<Integer, Integer> points; // 回数ごとの点数

	/**
	 * 入学年度取得
	 */
	public int getEntYear() {
		return entYear;
	}

	/**
	 * 入学年度設定
	 */
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	/**
	 * 学生番号取得
	 */
	public String getStudentNo() {
		return studentNo;
	}

	/**
	 * 学生番号設定
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	/**
	 * クラス番号取得
	 */
	public String getClassNum() {
		return classNum;
	}

	/**
	 * クラス番号設定
	 */
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	/**
	 * 点数一覧取得
	 */
	public Map<Integer, Integer> getPoints() {
		return points;
	}

	/**
	 * 点数一覧設定
	 */
	public void putPoints(Map<Integer, Integer> points) {
		this.points = points;
	}
}