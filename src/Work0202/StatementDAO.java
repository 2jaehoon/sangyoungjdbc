package Work0202;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StatementDAO {

	public void insert(JdbcVO tjVO) throws SQLException {

		// 1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch

		Connection con = null;
		Statement stmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";

		// 2.로딩된 드라이버를 사용하여 "Connection 얻기"
		try {
			con = DriverManager.getConnection(url, id, pass);
		// 3.쿼리문을 생성하고 실행할 수 있는 "쿼리문 생성객체얻기"
			stmt = con.createStatement();
		// 4.쿼리문 실행 후 결과를 받기
			StringBuilder insertJdbc = new StringBuilder();
			insertJdbc.append(" insert into work_jdbc(name) values('").append(tjVO.getName()).append("')");
			
			stmt.execute(insertJdbc.toString());
			System.out.println(insertJdbc);
		} finally {
		// 5.연결끊기
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insert
	
	

}// class
