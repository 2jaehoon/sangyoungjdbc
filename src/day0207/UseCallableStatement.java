package day0207;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.dao.conn.DbConnection;

public class UseCallableStatement {
	
	public void useCallable() throws SQLException{
		
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		CallableStatement cstmt = null;
		
		
		int i  = 2;
		int j = 7;
		try {
		//1.
		//2.
			con = db.getConn();
			
		//3. 쿼리문 생성 객체 얻기
			cstmt=con.prepareCall("{ call test_proc(?, ?, ?) }");
		//4. 바인드 변수에 값 설정
			//in parameter
			cstmt.setInt(1, i);
			cstmt.setInt(2, j);
			//out parameter : oracle bind 변수가 필요
			cstmt.registerOutParameter(3, Types.NUMERIC);
		//5. 쿼리문 수행
			cstmt.execute();
		//6. out parameter에 설정된 값을 얻기
			int result = cstmt.getInt(3);
			System.out.println(i + " + " +  j + " = " + result);
		}finally {
		//7. 연결 끊기
			db.dbClose(null, cstmt, con);
		}//end finally
		
	}//useCallable

	public static void main(String[] args) {
		UseCallableStatement ucs = new UseCallableStatement();
		
		try {
			ucs.useCallable();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
