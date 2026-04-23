package bean;

import java.io.Serializable;

/**
 * 科目別成績一覧Bean
 */
public class TestListStudent implements Serializable {

	private String subjectName; // 科目名
	private String subjectCD; // 科目コード
	private int num; // 回数
	private int point; // 点数

	/**
	 * 科目名取得
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * 科目名設定
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * 科目コード取得
	 */
	public String getSubjectCD() {
		return subjectCD;
	}

	/**
	 * 科目コード設定
	 */
	public void setSubjectCD(String subjectCD) {
		this.subjectCD = subjectCD;
	}

	/**
	 * 回数取得
	 */
	public int getNum() {
		return num;
	}

	/**
	 * 回数設定
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 点数取得
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * 点数設定
	 */
	public void setPoint(int point) {
		this.point = point;
	}
}