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

		// 1.����̹��ε�
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

		// 2.�ε��� ����̹��� ����Ͽ� "Connection ���"
		try {
			con = DriverManager.getConnection(url, id, pass);
		// 3.�������� �����ϰ� ������ �� �ִ� "������ ������ü���"
			stmt = con.createStatement();
		// 4.������ ���� �� ����� �ޱ�
			StringBuilder insertJdbc = new StringBuilder();
			insertJdbc.append(" insert into work_jdbc(name) values('").append(tjVO.getName()).append("')");
			
			stmt.execute(insertJdbc.toString());
			System.out.println(insertJdbc);
		} finally {
		// 5.�������
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// insert
	
	

}// class
