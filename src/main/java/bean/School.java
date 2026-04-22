package bean;

import java.io.Serializable;

/*
 * 学校情報を管理するBeanクラス
 * Serializableを実装し、データ保存や送信に対応する
 */
public class School implements Serializable {

	/*
	 * 学校コード
	 * 例: OOM など
	 */
	private String cd;

	/*
	 * 学校名
	 * 例: ○○高等学校
	 */
	private String name;

	/*
	 * 学校コードを取得するメソッド
	 */
	public String getCd() {
		return cd;
	}

	/*
	 * 学校コードを設定するメソッド
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}

	/*
	 * 学校名を取得するメソッド
	 */
	public String getName() {
		return name;
	}

	/*
	 * 学校名を設定するメソッド
	 */
	public void setName(String name) {
		this.name = name;
	}
}