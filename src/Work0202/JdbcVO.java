package Work0202;

/**
 * JdbcVO : ���� �����ϰ� ������ �������� ����� ��ü
 * @author user
 */
public class JdbcVO {

	private String name;
	
	public JdbcVO() {
		
	}//JdbcVO

	public JdbcVO(String name) {
		this.name = name;
	}//JdbcVO

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JdbcVO [name=" + name + "]";
	}
	
	
	
}//JdbcVO
