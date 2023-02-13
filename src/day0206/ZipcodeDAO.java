package day0206;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;

/**
 * 동을 입력받아 우편번호, 시도, 구군, 동 번지를 검색하는 일
 * @author user
 *
 */
public class ZipcodeDAO {
	
	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		//1.
		//2.
			con=db.getConn();
		//3.
			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode
			.append("  select  zipcode, sido, gugun, dong, nvl(bunji,' ') bunji ")
			.append("from zipcode_cp ")
			.append("where dong like ?||'%' ");
//			.append(" where  dong like ?% 					");//bind변수와 like의 특수문자가 함께 사용되면 바인드변수가 인식되지 않는다.
			
			pstmt = con.prepareStatement(selectZipcode.toString());
			System.out.println(selectZipcode.toString());
		//4.
			pstmt.setString(1, dong);
		//5.
			
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO = null;
			
			while(rs.next()) {
				//조회 결과가 있다면 VO를 생성하여 조회결과를 저장
				zVO = new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"), rs.getString("gugun"), rs.getString("dong"), rs.getString("bunji"));
				//같은 이름의 객체를 여러개 저장하기 위해서
				
				list.add(zVO);
			}
		}finally{
		//6.
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
	}//selectZipcode

}//class
