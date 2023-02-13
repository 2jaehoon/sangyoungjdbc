package day0208;

import java.sql.Date;

public class CpEmpVO {
	private int empno, sal, deptno;
	private String ename, job;
	private Date date;

	public CpEmpVO() {
	}
	public CpEmpVO(int empno, int sal, int deptno, String ename, String job, Date date) {
		this.empno = empno;
		this.sal = sal;
		this.deptno = deptno;
		this.ename = ename;
		this.job = job;
		this.date = date;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CpEmpVO [empno=" + empno + ", sal=" + sal + ", deptno=" + deptno + ", ename=" + ename + ", job=" + job
				+ ", date=" + date + "]";
	}
	
	

}
