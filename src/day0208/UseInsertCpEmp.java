package day0208;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.dao.conn.DbConnection;

public class UseInsertCpEmp {
	
	public ResultVO insertCpEmp(CpEmpVO ceVO) throws SQLException {
		ResultVO rVO = new ResultVO();
		DbConnection db = DbConnection.getInstance();
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
		//1.
		//2.
			con = db.getConn();
		//3.
			cstmt = con.prepareCall(" { call insert_cp_emp(?, ?, ?, ?, ?, ?, ? ) } ");
		//4.
			//in parameter
			cstmt.setInt(1, ceVO.getEmpno());
			cstmt.setString(2, ceVO.getEname());
			cstmt.setString(3, ceVO.getJob());
			cstmt.setInt(4, ceVO.getSal());
			cstmt.setInt(5, ceVO.getDeptno());
			//out parameter
			cstmt.registerOutParameter(6, Types.NUMERIC);
				cstmt.registerOutParameter(7, Types.VARCHAR);
		//5.
				cstmt.execute();
		//6.  
				int cnt = cstmt.getInt(6);
				String msg = cstmt.getString(7);
				
				rVO.setCnt(cnt);
				rVO.setMsg(msg);
				
		}finally {
		//7.
			db.dbClose(null, cstmt, con);
		}//end finally
		return rVO;
	}//useInsertCpEmp

	public static void main(String[] args) {
	CpEmpVO ceVO = new CpEmpVO(1112, 2800, 10, "박진호", "개발자", null);
	
	UseInsertCpEmp uice = new UseInsertCpEmp();
	try {
		ResultVO rVO =  uice.insertCpEmp(ceVO);
		System.out.println(rVO.getCnt() + "건 추가");
		System.out.println(rVO.getMsg() );
	} catch (SQLException e) {
		e.printStackTrace();
	}//end catch

	}//main

}//UseInsertCpEmp
