package day0207;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.dao.conn.DbConnection;

/**
 * 
 * @author user
 *
 */
public class TestTransaction {
	
	public TestTransaction() {
		
	}//TestTransaction

	/**
	 * test_transaction���̺�� test_transaction2�� ���̺� ��� insert�� �����ϸ� 
	 * transaction �Ϸ�, �׷��� ������ transaction ���
	 * @return
	 */
	public int transaction(Connection con, String name, String addr) throws SQLException {
		int cnt = 0;
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		//3.
		String insert = "insert into test_transaction(name, addr) values(?, ?)";
		pstmt = con.prepareStatement(insert);
		//4.
		pstmt.setString(1, name);
		pstmt.setString(2, addr);
		//5.
		int insertCnt  =pstmt.executeUpdate();
		
		//3.
		String insert2 = "insert into test_transaction2(name, addr) values(?, ?)";
		pstmt2 = con.prepareStatement(insert2);
		//4.
		pstmt2.setString(1, name);
		pstmt2.setString(2, addr);
		//5.
		int insertCnt2  =pstmt2.executeUpdate();
		
		//�� ���� ������ ������ Ŀ�ԵǾ ������� ���� �κе� Ŀ���� �Ǿ� ������. ���� ���� Ʈ����ǿ���
		
		cnt=insertCnt+insertCnt2;
		
		return cnt;
	}//transaction

	public void useTransaction() throws SQLException {
		//1. //2.   //6.
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		try {
			con=db.getConn();// Ŀ�ؼ� ���
			con.setAutoCommit(false);// autocommit ����
			
			String name = "�׽�Ʈ";
			String addr = "����� ������ ���ﵿ";
			
			//��ǥ�� �ϴ� ����� 2��
			int cnt  = transaction(con, name, addr);
			if(cnt == 2) {
				System.out.println("Ʈ����� �Ϸ�");
				con.commit();
			}//end if
//			else {
//				System.out.println("Ʈ����� ���");
//				con.rollback();                                        else�� �� �߰��Ǵ� ������ ����?
//			}
			
		}catch(SQLException se) {	
			System.out.println("Ʈ����� ���");
			con.rollback();
			
		}finally {
			db.dbClose(null, null, con);
		}//end finally
		
	}//useTransaction

	public static void main(String[] args) {
		
		TestTransaction tt = new TestTransaction();
		try {
			tt.useTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}// end catch

	}// main

}// class
