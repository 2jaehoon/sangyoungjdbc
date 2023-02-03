package day0201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Driver를 사용하여 DB 서버와 Connection 얻기
 * @author user
 *
 */
public class TestQuery {
	public TestQuery() {
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 성공!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		//2. Connection 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		try {
			Connection con = DriverManager.getConnection(url, id, pass);
			System.out.println("DB 연결 성공 " + con);
			
			//3. Connection에서 쿼리문 생성 객체 얻기
			Statement stmt = con.createStatement();
			
			//쿼리문 생성
			String sql = "insert into cp_emp2(empno, ename, mgr, hiredate,job) values(1111,'윤상준',1234,sysdate,'개발자')";
			
// cnt는 insert, update, date executeUpdate 횟수
			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "건 추가 되었습니다.");

//			되긴 하지만 적합하지 않다.  execute는 참 거짓, create, drop, alter, truncate는 플레그
//			boolean flag = stmt.execute(sql);
//			System.out.println(flag + "추가");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
	
		
	}//TestConnection

	public static void main(String[] args) {
		new TestQuery();

	}//main

}//class
