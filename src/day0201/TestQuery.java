package day0201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Driver�� ����Ͽ� DB ������ Connection ���
 * @author user
 *
 */
public class TestQuery {
	public TestQuery() {
		//1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̹� �ε� ����!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		//2. Connection ���
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		try {
			Connection con = DriverManager.getConnection(url, id, pass);
			System.out.println("DB ���� ���� " + con);
			
			//3. Connection���� ������ ���� ��ü ���
			Statement stmt = con.createStatement();
			
			//������ ����
			String sql = "insert into cp_emp2(empno, ename, mgr, hiredate,job) values(1111,'������',1234,sysdate,'������')";
			
// cnt�� insert, update, date executeUpdate Ƚ��
			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "�� �߰� �Ǿ����ϴ�.");

//			�Ǳ� ������ �������� �ʴ�.  execute�� �� ����, create, drop, alter, truncate�� �÷���
//			boolean flag = stmt.execute(sql);
//			System.out.println(flag + "�߰�");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
	
		
	}//TestConnection

	public static void main(String[] args) {
		new TestQuery();

	}//main

}//class
