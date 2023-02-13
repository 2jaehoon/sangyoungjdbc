package day0206;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
	
	public List<TableVO> selectTableColumnData(String tName) throws SQLException{
		List<TableVO> list = new ArrayList<TableVO>();
		
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
		//1.
		//2.
			con=db.getConn();
		//3. 쿼리문 생성 객체 얻기
//			String sql = "select * from ?"; // table명이나 컬럼명은 bind변수로 처리할 수 없다.
			
			//Preparedstate에서 테이블명이나 컬럼명을 동적으로 처리하고 싶다면
			// 쿼리문에 직접 넣어주어야 한다. 
			StringBuilder selectTableInfo = new StringBuilder();
			selectTableInfo
			.append("select * from ").append(tName);
			pstmt = con.prepareStatement(selectTableInfo.toString());
			//인덱스에서 누락된 IN 또는 OUT 매개변수:: 1 <<<<바인드 변수에 값이 들어가지 않을 때 에러 뜨는 형태
		//4. 바인드 변수에 값 설정
//			pstmt.setString(0, tName);
		//5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
		//6. rs에서 ResultSetMataData 얻기
			rsmd=rs.getMetaData();
//			System.out.println(rsmd.getColumnCount());
//			System.out.println(rsmd.getColumnLabel(3));
//			System.out.println(rsmd.getColumnTypeName(3));
//			System.out.println(rsmd.getPrecision(3));
			
//			for(int i=1; i<rsmd.getColumnCount(); i++) {
//				System.out.println(rsmd.getColumnLabel(i) + "  " +
//			rsmd.getColumnTypeName(i) + "("
//			+ rsmd.getPrecision(i) + ") " + (rsmd.isNullable(i)==0?"not null":""));
//			}
			
			TableVO tVO = null;
			for(int i=1; i<rsmd.getColumnCount(); i++) {
				tVO = new TableVO(rsmd.getColumnName(i), rsmd.getColumnTypeName(i), rsmd.isNullable(i)==0?"not null":"", rsmd.getPrecision(i));
				
				list.add(tVO);
				
			}//end for
			
		}finally {
		//7. 연결 끊기
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}

	public static void main(String[] args) {
		
		try {
			System.out.println(TableDAO.getInstance().selectTableColumnData("dept"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//main
}//class
