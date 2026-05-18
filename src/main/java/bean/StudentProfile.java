package bean;

import java.io.Serializable;

/*
 * 学生プロフィール情報を管理するBeanクラス
 */
public class StudentProfile implements Serializable {

	// 学生番号（studentテーブルの no と対応）
	private String studentNo;

	// 名前
	private String name;

	// 住所
	private String address;

	// 電話番号
	private String phone;

	// メールアドレス
	private String email;

	// プロフィール写真（Base64エンコード文字列）
	private String photoBase64;

	// 写真のMIMEタイプ（例: image/jpeg）
	private String photoMimeType;

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}

	public String getPhotoMimeType() {
		return photoMimeType;
	}

	public void setPhotoMimeType(String photoMimeType) {
		this.photoMimeType = photoMimeType;
	}
}