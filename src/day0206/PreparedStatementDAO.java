package day0206;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day0202.TestJdbcVO;
import kr.co.sist.dao.conn.DbConnection;

/**
 * DAO : ( Data Access Object )
 * DB작업에 관련된 코드만 정의하는 클래스
 * @author user
 *
 */
public class PreparedStatementDAO {
	
	private static PreparedStatementDAO psDAO;
	
	private PreparedStatementDAO() {
		
	}//PreparedStatementDAO
	
	public static PreparedStatementDAO getInstance() {
		if(psDAO==null) {
			psDAO=new PreparedStatementDAO();
		}//end if
	return psDAO;	
	}//getInstance
	
	
	
	
	
	public void insert(TestJdbcVO tjVO) throws SQLException {
		
		
		
		//1. 드라이버 로딩
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		//2. 로딩된드라이버를 사용하여 "Connection 얻기"
		try {
			con=dbCon.getConn();
		
		//3. 쿼리문을 생성하고 실행할 수 있는 "쿼리문 생성 객체 얻기"
			// 쿼리문에서 값이 들어가는 ㅂ분은 ?(bind 변수)로 처리한다.
			String insertTestJdbc 
			= " insert into test_jdbc(num, name, gender, height, input_date) values(?,?,?,?,sysdate)";
			
			pstmt=con.prepareStatement(insertTestJdbc);
			
			//4. 쿼리문에 값이 존재하지 않기 때문에 bind변수(?)에 값 설정
			pstmt.setInt(1, tjVO.getNum() );
			pstmt.setString(2, tjVO.getName());
			pstmt.setString(3, tjVO.getGender());
			pstmt.setDouble(4, tjVO.getHeight());
			
		//5. 쿼리문 실행 후 결과 받기
			pstmt.executeUpdate();
			
		}finally {
			//5. 연결 끊기 
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
	}//insert
	
	public int update(TestJdbcVO tjVo)throws SQLException{
		
		int cnt = 0;
DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		//1. 드라이버 로딩
		//2. 커넥션 얻기
			con=dbCon.getConn();
		//3. 쿼리문 생성 객체 얻기
			StringBuilder updateTestJdbc = new StringBuilder();
			updateTestJdbc
			.append("update test_jdbc ")
			.append(" set name=?, height=? ")
			.append("where num=?");
			
			pstmt=con.prepareStatement(updateTestJdbc.toString());
			
		//4. 바인드 변수에 값 설정
			pstmt.setString(1, tjVo.getName());
			pstmt.setDouble(2, tjVo.getHeight());
			pstmt.setInt(3, tjVo.getNum());
		//5. 쿼리문 수행 후 결과 얻기
			cnt = pstmt.executeUpdate();
		}finally {
		//6. 연결 끊기
		dbCon.dbClose(null, pstmt, con);
		}//end finally
		return cnt;
	}//update
	
	public int delete(int num)throws SQLException{
		int cnt = 0;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		//1. 드라이버 로딩
		//2. 커넥션 얻기
			con=dbCon.getConn();
		//3. 쿼리문 생성 객체
			String deleteTestJdbc = "delete from test_jdbc where num=?";
			pstmt = con.prepareStatement(deleteTestJdbc);
		//4. 바인드 변수에 값 설정
			pstmt.setInt(1, num);
		//5. 쿼리문 수행 후 결과 얻기
			cnt=pstmt.executeUpdate();
			System.out.println(cnt);
		}finally {
		//6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
		
		return cnt;
	}//delete
	
	
	public List<TestJdbcVO> selectAll() throws SQLException{
		List<TestJdbcVO> list = new ArrayList<TestJdbcVO>();
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		DbConnection db  = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection 얻기
		con = db.getConn();
		//3. 쿼리문 생성 객체 얻기
		//4. 쿼리문 실행 후 결과 얻기
		String selectTestjdbc = "select num,name,gender,height,input_date from test_jdbc";
		pstmt=con.prepareStatement(selectTestjdbc.toString());
		
		rs = pstmt.executeQuery();//쿼리문을 실행하고 CURSOR의 제어권을 받는다.
		

		int num = 0;
		String name = "";
		String gender = "";
		double height = 0.0;
		Date date = null;
		
		TestJdbcVO tjVO = null;
		
		while(rs.next()) { // 레코드가 존재하는지 알 수 없지만 존재한 다면 모든 레코드를 가져와야 한다.
			//커서 다음에 레코드가 존재하면 TRUE를 반환하여 커서의 위치를 아래로 이동
//			num = rs.getInt("num");    //number = > int
//			name=rs.getString("name");  //vachar2 = > String
//			gender=rs.getString("gender"); //char = > String
//			height=rs.getDouble("height");  //number = > double
//			date = rs.getDate("input_date");  //date = > java.sql.date
			
			//조회한 레코드의 컬럼 값을 VO에 저장
			tjVO = new TestJdbcVO(rs.getInt("num"), rs.getString("name"), rs.getString("gender"), rs.getDouble("height"), rs.getDate("input_date"));
			//같은 이름의 VO객체를 사라지지 않게 관리하기 위해 List에 추가
			list.add(tjVO);
			// 생성되는 각각다른 tjVO의 객체 값들을 저장하기 위해 리스트에 에드
//			System.out.println(tjVO);
		}//end while
		
		}finally {
			//5. 연결 끊기
		db.dbClose(rs, pstmt, con);
			
		}//end finally
		
		return list;
		
	}//selectAll
	
	
	
	
	
	
	public TestJdbcVO selectOne( int num ) throws SQLException{
		TestJdbcVO tjVO = null;
		
		DbConnection db = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//1. 드라이버 로딩
		
		try {
		con = db.getConn();
		//4. 쿼리문 객체 사용 실행으로 결과 값 얻기
			StringBuilder selectTestJdbc = new StringBuilder();
			selectTestJdbc
			.append("	select		name,gender,height,input_date")
			.append("	from		test_jdbc   ")
			.append("	where		num=?   ");
			
			pstmt = con.prepareStatement(selectTestJdbc.toString());
			
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//검색된 레코드가 존재하니?
				//VO를 생성하여 검색 결과를 할당
				tjVO=new TestJdbcVO(num, rs.getString("name"), rs.getString("gender"), rs.getDouble("height"), rs.getDate("input_date"));
			}//end if
			
		}finally {
		//5. 커넥트, 생성객체, 결과 끊기
			db.dbClose(rs, pstmt, con);
		}
		
		return tjVO;
	}//selectOne
	
	
	
	
	
	
	

}//class
