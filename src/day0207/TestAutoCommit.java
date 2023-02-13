package day0207;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.sist.dao.conn.DbConnection;

/**
 * Connection에 설정된 autocommit 변경
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
			//Connection의 autocommit 상태 변경
			con.setAutoCommit(false);
			System.out.println("autocommit? : " + con.getAutoCommit());
		//3.
			String insertQuery = "insert into cp_dept(deptno, dname, loc) values(?,?,?)";
			pstmt = con.prepareStatement(insertQuery);
		//4.
			pstmt.setInt(1, 81);
			pstmt.setString(2, "개발");
			pstmt.setString(3, "서울");
		//5.
			int cnt = pstmt.executeUpdate(); //insert는 1건 들어가거나 예외 //PK제약에 걸리면 예외 터져서 호출한 곳으로 간다 아래 코드 수행 안 한다.
			
//			if(cnt == 1) {
//				//transaction이 완료
//				con.commit();
//			} /*
//				 * else { con.rollback(); }
//				 */
			
		}finally {
		//6.
			db.dbClose(null, pstmt, con);//끊어지면 정상 종료 => commit
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
		pstmt.setString(1, " c자목 ");
		pstmt.setInt(2, 9999);
		pstmt.setInt(3, 1110);
		//5.
		cnt = pstmt.executeUpdate();
		
		if(cnt == 1) { //변경된 행이 1행이라면 commit
			con.commit();
		}else { // 그렇지 않다면 rollback
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
//			System.out.println("입력 성공");
			int cnt = new TestAutoCommit().update();
					if(cnt == 0) {
						System.out.println(cnt + " : 변경된 행이 없음");
					}else {
						System.out.println(cnt + " : 변경된 행이 있음");
					}//end else
		} catch (SQLException e) {
//			System.out.println("입력 실패");
			System.out.println("변경 작업 자체 실패"); //0. DBMS에 대한 연결 오류, 1. 쿼리문 오류, 2. DBMS 자체의 문제
			e.printStackTrace();
		}
	}//main

}//class
