package Work0202;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	public void insert(VO vo, Design d) throws SQLException {
		// vo��� ��ü�� design�̶�� ��ü �ּҰ� ���;� �Ʒ� �ڵ尡 ���� ����
		// vo������ �������� design��ü�� �ּҰ� ���� �Ѵ�.
		// ����� ���� ������ null���� ���´�.

		// 1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";

		Connection con = null;
		Statement stmt = null;

		try {
			// 2. Ŀ�ؼ� ���
			con = DriverManager.getConnection(url, id, pass);

			// 3. ������ ���� ��ü ���
			stmt = con.createStatement();

			// 4. ���� ���� �� ��� ���

			StringBuilder insert = new StringBuilder();
			
			insert.append(" insert into work_jdbc(name) values('").append(vo.getName(d)).append("')");
			stmt.executeUpdate(insert.toString());
			
		} finally {
			// 5. ���� ����
			if (con != null) {
				con.close();
			}//end if
			if (stmt != null) {
				stmt.close();
			}//end if
		}//end finally

	}//end insert

}//class DAO
