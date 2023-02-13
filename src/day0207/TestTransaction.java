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
	 * test_transaction테이블과 test_transaction2의 테이블에 모두 insert가 성공하면 
	 * transaction 완료, 그렇지 않으면 transaction 취소
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
		
		//안 끊는 이유는 끊으면 커밋되어서 실행되지 않은 부분도 커밋이 되어 버린다. 여러 개의 트랜잭션에서
		
		cnt=insertCnt+insertCnt2;
		
		return cnt;
	}//transaction

	public void useTransaction() throws SQLException {
		//1. //2.   //6.
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		try {
			con=db.getConn();// 커넥션 얻기
			con.setAutoCommit(false);// autocommit 해제
			
			String name = "테스트";
			String addr = "서울시 강남구 역삼동";
			
			//목표로 하는 행수가 2행
			int cnt  = transaction(con, name, addr);
			if(cnt == 2) {
				System.out.println("트랜잭션 완료");
				con.commit();
			}//end if
//			else {
//				System.out.println("트랜잭션 취소");
//				con.rollback();                                        else일 때 추가되는 이유가 뭘까?
//			}
			
		}catch(SQLException se) {	
			System.out.println("트랜잭션 취소");
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
