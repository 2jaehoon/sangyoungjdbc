package work0206;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;

public class TableDAO {
	private static TableDAO tDAO;
	
	private TableDAO() {
		
	}//TableDAO
	
	public static TableDAO getInstance() {
		if(tDAO == null) {
			tDAO = new TableDAO();
	}//end if
		return tDAO;
}//getInstance

	public List<String> selectAllTabName() throws SQLException{
		List<String> list  = new ArrayList<String>();
		
		DbConnection db = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		//1.
		//2.
			con=db.getConn();
		//3.
			pstmt = con.prepareStatement("select tname from tab");
		//4.
		//5.
			rs=pstmt.executeQuery();
			
			
			
			//5.
	         rs=pstmt.executeQuery();
	         while(rs.next()) {
	            list.add(rs.getString("tname"));
	         }//end while
		}finally {
		//6.
			db.dbClose(rs, pstmt, con);
		}
		return list;
	}//selectAllTabName
	
	
	public String selectOne( String tName ) throws SQLException{
		TableVO tVO = null;
		//1. 드라이버 로딩
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
		ResultSet rs = null;
		
		
		try {
		//2. 커넥트 연결
			con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 객체 사용 실행으로 결과 값 얻기
			StringBuilder selectTestJdbc = new StringBuilder();
			
			selectTestJdbc
			.append("	select		*")
			.append("	from		user_tab_cols")
			.append("	where		table_name='").append(tName).append("'");
			
			rs=stmt.executeQuery(selectTestJdbc.toString());
			
			StringBuilder sb = new StringBuilder();
			sb
			.append("컬럼명\t데이터형\tnull허용\tdefault\n")
			.append("------------------------------------------------------------------------------\n");
			
			while(rs.next()) {//검색된 레코드가 존재하니?
				
				sb
				.append(rs.getString("column_name")).append("\t")
				.append(rs.getString("data_type")).append("(");
				
				if(rs.getString("data_precision")==null) {
				sb.append(rs.getInt("data_length")).append(")\t");
				}else {
					sb.append(rs.getString("data_precision")).append(")\t");
				}
				
				sb.append(rs.getString("nullable")).append("\t")
				.append(rs.getString("data_default")).append("\n");
				
			}//end if
			
			return sb.toString();
			
		}finally {
		//5. 커넥트, 생성객체, 결과 끊기
			if( rs != null ) { rs.close(); }//end if
			if( stmt != null ) { stmt.close(); }//end if
			if( con != null ) { con.close(); }//end if
		}
		
		
	}//selectOne
	
	
	

}//class
