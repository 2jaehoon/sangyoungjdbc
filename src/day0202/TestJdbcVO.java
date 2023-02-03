package day0202;

import java.sql.Date;

/**
 * 값을 저장하고., 전달할 목적으로 생성하는 클래스
 * @author user
 *
 */
public class TestJdbcVO {
	private int num;
	private String name, gender;
	private double height;
	private Date inputDate;	//소문자 컨트롤 슈프트 y
	
	public TestJdbcVO() {
		
	}
	
	
	public TestJdbcVO(int num, String name, String gender, double height, Date inputDate) {
		this.num = num;
		this.name = name;
		this.gender = gender;
		this.height = height;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Date getInput_date() {
		return inputDate;
	}
	public void setInput_date(Date inputDate) {
		this.inputDate = inputDate;
	}
	
	@Override
	public String toString() {
		return "TestJdbcVO [num=" + num + ", name=" + name + ", gender=" + gender + ", height=" + height
				+ ", input_date=" + inputDate + "]";
	}
	

}
