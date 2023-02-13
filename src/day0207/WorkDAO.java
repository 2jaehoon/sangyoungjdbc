package day0207;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;

public class WorkDAO {
	
	private static WorkDAO wDAO;
	
	private WorkDAO() {
		
	}//WorkDAO
	
	public static WorkDAO getInstance() {
		if( wDAO == null ){
			wDAO=new WorkDAO();
		}//end if
		return wDAO;
	}//getInstance
	
	public void insert(String name, String phone, String email)throws SQLException{
		
		DbConnection db=DbConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			//1.
			//2.
			con=db.getConn();
			//3.
			String insertData="insert into namecard(num,name,phone,email,input_date) values( namecard_seq.nextval, ?, ?, ?, sysdate)";
			pstmt=con.prepareStatement(insertData);
			//4.
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			//5.
			pstmt.executeUpdate();
			
		}finally {
			//6.
			db.dbClose(null, pstmt, con);
		}//end finally
		
	}//insert
	
	public List<WorkVO> selectAllTabNum()throws SQLException{
		
		List<WorkVO> list=new ArrayList<WorkVO>();
		
		DbConnection db=DbConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//1.드라이버로딩
			//2.Connection 얻기
			con=db.getConn();
			
			//3.쿼리생성객체얻기
			pstmt=con.prepareStatement("select * from namecard");
			
			//4.바인드변수 값 설정.
			//5.쿼리수행후 결과얻기
			rs=pstmt.executeQuery();
			
			WorkVO wVO=null;
			while( rs.next() ) {
				wVO=new WorkVO( rs.getInt("num"), rs.getString("name"), 
						rs.getString("phone"), rs.getString("email"), rs.getDate("input_date"));
				list.add(wVO);
			}//end while
			
		}finally {
			
			//6.연결끊기
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectAllTabNum
	
	public void modify(String name, String phone, String email, int num) throws SQLException {
		
		DbConnection db=DbConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			//1.
			//2.
			con=db.getConn();
			//3.
			StringBuilder updateData = new StringBuilder();
			updateData
			.append("update namecard ")
			.append(" set name=?, phone=?, email=?")
			.append(" where num = ?");
			pstmt=con.prepareStatement(updateData.toString());
			//4.
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setInt(4, num);
			//5.
			pstmt.executeUpdate();
			
		}finally {
			//6.
			db.dbClose(null, pstmt, con);
		}//end finally
		
	}//modify
	
	public void delete(int num) throws SQLException {
		
		DbConnection db=DbConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			//1.
			//2.
			con=db.getConn();
			//3.
			String deleteData="delete from namecard where num = ?";
			pstmt=con.prepareStatement(deleteData);
			//4.
			pstmt.setInt(1, num);
//			pstmt.setString(2, phone);
//			pstmt.setString(3, email);
			//5.
			pstmt.executeUpdate();
			
		}finally {
			//6.
			db.dbClose(null, pstmt, con);
		}//end finally
		
	}//delete
	
}//class
