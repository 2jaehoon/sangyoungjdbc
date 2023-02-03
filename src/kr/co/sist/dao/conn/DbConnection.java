package kr.co.sist.dao.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static DbConnection dbConn;
	
	private DbConnection() {
		
	}//DbConnection
	
	public static DbConnection getInstance() {
		if(dbConn==null) {
			dbConn = new DbConnection();
		}//end if
		return dbConn;
	}//getInstance
	
	public Connection getConn() throws SQLException{
		Connection con  = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		String url ="jdbc:oracle:thin:@localhost:1521:orcl"; // 
		String id ="scott";
		String pass ="tiger";
		
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}

	
	public void dbClose(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		try {
		if(rs!=null) {
			rs.close();
		}// end if
		if(stmt!=null) {
			stmt.close();
		}// end if
		}finally {
		if(con!=null) {
			con.close();
		}// end if
		}//end finally
	}//dbClose
	
}//class
