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
			
		//3. ������ ���� ��ü ���
			cstmt=con.prepareCall("{ call test_proc(?, ?, ?) }");
		//4. ���ε� ������ �� ����
			//in parameter
			cstmt.setInt(1, i);
			cstmt.setInt(2, j);
			//out parameter : oracle bind ������ �ʿ�
			cstmt.registerOutParameter(3, Types.NUMERIC);
		//5. ������ ����
			cstmt.execute();
		//6. out parameter�� ������ ���� ���
			int result = cstmt.getInt(3);
			System.out.println(i + " + " +  j + " = " + result);
		}finally {
		//7. ���� ����
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
