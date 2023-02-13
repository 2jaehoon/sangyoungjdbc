package day0208;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

/**
 * SYS_REFCURSOR의 사용 => Types.CURSOR, OracleTypes.CURSOR
 * @author user
 *
 */
public class UseCursor {
	
	public List<CpEmpVO> useCpEmpAll() throws SQLException{
		List<CpEmpVO> list = new ArrayList<CpEmpVO>();
		
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
		//1.
		//2.
			con=db.getConn();
		//3.
			cstmt = con.prepareCall(" { call select_all_cp_emp(?) } ");
		//4.
			//in parameter
			
			//out parameter
//			cstmt.registerOutParameter(1, Types.REF_CURSOR);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
		//5.
			cstmt.execute();
		//6.
			rs=(ResultSet) cstmt.getObject(1); //ResultSet은 cursor의 제어권을 갖는다.
			
			CpEmpVO ceVO = null;
			
			while( rs.next() ) {
				ceVO = new CpEmpVO(rs.getInt("empno"), rs.getInt("sal"), rs.getInt("deptno"), rs.getString("ename"), rs.getString("job"), rs.getDate("hiredate"));
				
				list.add(ceVO);
			}//end while
			
			
			}finally {
		//7.
			db.dbClose(rs, cstmt, con);
		}//end finally
		
		return list;
	}//useCpempall

	public static void main(String[] args) {
		UseCursor uc = new  UseCursor();
		try {
			List<CpEmpVO> list =  uc.useCpEmpAll();
			
			if(list.isEmpty()) {
				System.out.println("조회된 사원 정보가 존재하지 않습니다.");
			}
			
			for(CpEmpVO ceVO : list) {
				System.out.println(ceVO.getEmpno() + " / " + ceVO.getEname() + " / " + ceVO.getJob() + " / " + ceVO.getSal() + " / " + ceVO.getDate() + " / " + ceVO.getDeptno());
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch

	}//main

}//class
