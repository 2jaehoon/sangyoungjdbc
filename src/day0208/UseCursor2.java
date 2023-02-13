package day0208;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;
import oracle.jdbc.OracleTypes;

public class UseCursor2 {

	public List<CpEmpVO> useCpEmpDeptno(int deptno) throws SQLException{
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
			cstmt = con.prepareCall(" { call select_cp_emp_deptno(?, ?) } ");
		//4.
			//in parameter
			cstmt.setInt(1,deptno);
			//out parameter
//			cstmt.registerOutParameter(1, Types.REF_CURSOR);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
		//5.
			cstmt.execute();
		//6.
			rs=(ResultSet) cstmt.getObject(2); //ResultSet은 cursor의 제어권을 갖는다.
			
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
		UseCursor2 uc = new  UseCursor2();
		try {
			int deptno = 50;
			List<CpEmpVO> list =  uc.useCpEmpDeptno(deptno);
			
			if(list.isEmpty()) {
				System.out.println(deptno + " 번 부서에는 조회된 사원 정보가 존재하지 않습니다.");
			}
			
			for(CpEmpVO ceVO : list) {
				System.out.println(ceVO.getEmpno() + " / " + ceVO.getEname() + " / " + ceVO.getJob() + " / " + ceVO.getSal() + " / " + ceVO.getDate() + " / " + ceVO.getDeptno());
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch

	}//main
}
