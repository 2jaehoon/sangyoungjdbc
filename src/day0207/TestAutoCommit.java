package day0207;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.dao.conn.DbConnection;

/**
 * Connection�� ������ autocommit ����
 * @author user
 *
 */
public class TestAutoCommit {
	
	public void insert() throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConnection db = DbConnection.getInstance();
		try {
		//1.
		//2.
			con = db.getConn();
			//Connection�� autocommit ���� ����
			con.setAutoCommit(false);
			System.out.println("autocommit? : " + con.getAutoCommit());
		//3.
			String insertQuery = "insert into cp_dept(deptno, dname, loc) values(?,?,?)";
			pstmt = con.prepareStatement(insertQuery);
		//4.
			pstmt.setInt(1, 81);
			pstmt.setString(2, "����");
			pstmt.setString(3, "����");
		//5.
			int cnt = pstmt.executeUpdate(); //insert�� 1�� ���ų� ���� //PK���࿡ �ɸ��� ���� ������ ȣ���� ������ ���� �Ʒ� �ڵ� ���� �� �Ѵ�.
			
//			if(cnt == 1) {
//				//transaction�� �Ϸ�
//				con.commit();
//			} /*
//				 * else { con.rollback(); }
//				 */
			
		}finally {
		//6.
			db.dbClose(null, pstmt, con);//�������� ���� ���� => commit
		}//end finally
	}//insert
	
	
	public int update() throws SQLException {
		int cnt = 0;
		
		Connection con  = null;
		PreparedStatement pstmt = null;
		
		DbConnection db = DbConnection.getInstance();
		
		try {
		//1.
		//2.
		con = db.getConn();
		con.setAutoCommit(false);
		//3.
		String updateQuery = "update cp_emp2 set ename = ?, mgr = ? where empno = ?";
		pstmt = con.prepareStatement(updateQuery);
		//4.
		pstmt.setString(1, " c�ڸ� ");
		pstmt.setInt(2, 9999);
		pstmt.setInt(3, 1110);
		//5.
		cnt = pstmt.executeUpdate();
		
		if(cnt == 1) { //����� ���� 1���̶�� commit
			con.commit();
		}else { // �׷��� �ʴٸ� rollback
			con.rollback();
		}//end else
		
		}finally {
		//6.
			db.dbClose(null, pstmt, con);
		}//end finally
		
		return cnt;
	}//update

	public static void main(String[] args) {
		try {
//			new TestAutoCommit().insert();
//			System.out.println("�Է� ����");
			int cnt = new TestAutoCommit().update();
					if(cnt == 0) {
						System.out.println(cnt + " : ����� ���� ����");
					}else {
						System.out.println(cnt + " : ����� ���� ����");
					}//end else
		} catch (SQLException e) {
//			System.out.println("�Է� ����");
			System.out.println("���� �۾� ��ü ����"); //0. DBMS�� ���� ���� ����, 1. ������ ����, 2. DBMS ��ü�� ����
			e.printStackTrace();
		}
	}//main

}//class
