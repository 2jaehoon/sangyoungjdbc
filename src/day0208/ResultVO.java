package day0208;

public class ResultVO {
	private int cnt;
	private String msg;
	
	public ResultVO() {
	}
	public ResultVO(int cnt, String msg) {
		this.cnt = cnt;
		this.msg = msg;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ResultVO [cnt=" + cnt + ", msg=" + msg + "]";
	}
	
	

}
