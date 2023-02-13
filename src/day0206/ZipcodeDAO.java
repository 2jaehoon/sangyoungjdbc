package day0206;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;

/**
 * ���� �Է¹޾� �����ȣ, �õ�, ����, �� ������ �˻��ϴ� ��
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
//			.append(" where  dong like ?% 					");//bind������ like�� Ư�����ڰ� �Բ� ���Ǹ� ���ε庯���� �νĵ��� �ʴ´�.
			
			pstmt = con.prepareStatement(selectZipcode.toString());
			System.out.println(selectZipcode.toString());
		//4.
			pstmt.setString(1, dong);
		//5.
			
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO = null;
			
			while(rs.next()) {
				//��ȸ ����� �ִٸ� VO�� �����Ͽ� ��ȸ����� ����
				zVO = new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"), rs.getString("gugun"), rs.getString("dong"), rs.getString("bunji"));
				//���� �̸��� ��ü�� ������ �����ϱ� ���ؼ�
				
				list.add(zVO);
			}
		}finally{
		//6.
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
	}//selectZipcode

}//class
