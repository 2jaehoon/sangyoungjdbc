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
		//3. ������ ���� ��ü ���
//			String sql = "select * from ?"; // table���̳� �÷����� bind������ ó���� �� ����.
			
			//Preparedstate���� ���̺���̳� �÷����� �������� ó���ϰ� �ʹٸ�
			// �������� ���� �־��־�� �Ѵ�. 
			StringBuilder selectTableInfo = new StringBuilder();
			selectTableInfo
			.append("select * from ").append(tName);
			pstmt = con.prepareStatement(selectTableInfo.toString());
			//�ε������� ������ IN �Ǵ� OUT �Ű�����:: 1 <<<<���ε� ������ ���� ���� ���� �� ���� �ߴ� ����
		//4. ���ε� ������ �� ����
//			pstmt.setString(0, tName);
		//5. ������ ���� �� ��� ���
			rs=pstmt.executeQuery();
		//6. rs���� ResultSetMataData ���
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
		//7. ���� ����
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
