package Work0202;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	public void insert(VO vo, Design d) throws SQLException {
		// vo라는 객체와 design이라는 객체 주소가 들어와야 아래 코드가 값이 들어간다
		// vo에서도 마찬가지 design객체의 주소가 들어가야 한다.
		// 제대로 들어가지 않으면 null값이 나온다.

		// 1. 드라이버 로딩
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
			// 2. 커넥션 얻기
			con = DriverManager.getConnection(url, id, pass);

			// 3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();

			// 4. 쿼리 수행 후 결과 얻기

			StringBuilder insert = new StringBuilder();
			
			insert.append(" insert into work_jdbc(name) values('").append(vo.getName(d)).append("')");
			stmt.executeUpdate(insert.toString());
			
		} finally {
			// 5. 연결 끊기
			if (con != null) {
				con.close();
			}//end if
			if (stmt != null) {
				stmt.close();
			}//end if
		}//end finally

	}//end insert

}//class DAO
