package day0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;

/**
 * Clob 데이터 사용
 * @author user
 *
 */
public class TestClob {
	
	public List<ClobVO> selectClob() throws SQLException {
		List<ClobVO> list = new ArrayList<ClobVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BufferedReader br =null;
		
		DbConnection db  = DbConnection.getInstance();
		
		try {
		//1.
		//2.
			con=db.getConn();
		//3.
			String selecttestClob
			="select title, content, writer, to_char(input_date,'yyyy-mm-dd day') input_date from test_clob";
			pstmt = con.prepareStatement(selecttestClob);
		//4.
		//5.
			rs=pstmt.executeQuery();
			
			ClobVO cVO = null;
			
			
			//local 상태에서는 clob데이터도 rs.getString()으로 가져올 수 있다.
			String data  = "";
			StringBuilder content = null;
			while( rs.next()) {
//				cVO = new ClobVO(rs.getString("title"),rs.getString("content") ,rs.getString("writer") ,rs.getString("input_date")  );
				
				cVO = new ClobVO();
				
				cVO.setTitle(rs.getString("title"));
				cVO.setInput_date(rs.getString("input_date"));
				cVO.setWriter(rs.getString("writer"));
				
				
				
				//컬럼 하나를 가져오기 위해서 스트림을 연결
				br = new BufferedReader(rs.getClob("content").getCharacterStream() );
				try {
					content = new StringBuilder();
					while((data = br.readLine()) != null) {
						content.append(data).append("\n");
					}//end while
					
					cVO.setContent(content.toString());
					
					if(br!=null) {
						br.close();
					}//end if
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				list.add(cVO);
			}//end while
		}finally {
		//6.
		db.dbClose(rs, pstmt, con);
		}//end finally
		return list;
	}//selectClob
	
	public void searchClob() {
		try {
			List<ClobVO> list = selectClob();
			
			for(ClobVO cVO : list) {
				System.out.println("작성자 : " + cVO.getWriter());
				System.out.println("작성일 : " + cVO.getInputdate());
			System.out.println("제목 : " + cVO.getTitle());
			System.out.println("내용 : " + cVO.getContent());
			}//end for
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
	}//searchClob

	public static void main(String[] args) {
		new TestClob().searchClob();
	}//main

}//class
