package day0201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Driver�� ����Ͽ� DB ������ Connection ���
 * @author user
 *
 */
public class TestConnection {
	public TestConnection() {
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
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		//3. 
		
	}//TestConnection

	public static void main(String[] args) {
		new TestConnection();

	}//main

}//class
