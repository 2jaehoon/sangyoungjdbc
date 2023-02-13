package day0207;

import java.sql.Date;

public class WorkVO {
	
	private int num;
	private String name;
	private String phone;
	private String email;
	private Date inputDate;
	
	public WorkVO() {
	}

	public WorkVO(int num, String name, String phone, String email, Date inputDate) {
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.inputDate = inputDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Override
	public String toString() {
		return "WorkVO [num=" + num + ", name=" + name + ", phone=" + phone + ", email=" + email + ", inputDate="
				+ inputDate + "]";
	}
	

}//class
