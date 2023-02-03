package day0202;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO : ( Data Access Object )
 * DB작업에 관련된 코드만 정의하는 클래스
 * @author user
 *
 */
public class StatementDAO {
	
	public void insert(TestJdbcVO tjVO) throws SQLException {
		
		
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";// 쿼텀 db 오라클 마우스 오른쪽 properties
		String id = "scott";
		String pass = "tiger";
		
		//2. 로딩된드라이버를 사용하여 "Connection 얻기"
		
			con=DriverManager.getConnection(url,id,pass);
		
		//3. 쿼리문을 생성하고 실행할 수 있는 "쿼리문 생성 객체 얻기"
			stmt=con.createStatement();
		
		//4. 쿼리문 실행 후 결과 받기
//			String insertTestJdbc = "insert,,,";
			StringBuilder insertTestJdbc = new StringBuilder();
			
			insertTestJdbc
			.append(" insert into test_jdbc(num, name, gender, height, input_date) values(  ")
			.append(tjVO.getNum()).append(",")
			.append("'").append(tjVO.getName()).append("',")
			.append("'").append(tjVO.getGender()).append("',")
			.append(tjVO.getHeight()).append(",")
			.append("sysdate)");//자바에서 sql을 만들때 ;를 넣지 않는다 error
			
//			System.out.println(insertTestJdbc.toString());
			
			/* int cnt = */stmt.executeUpdate(insertTestJdbc.toString());
			/*
			 * if(cnt == 1) { System.out.println("1건 insert 성공!!"); }//end if
			 */			
		//5. 연결 끊기 nullpoint익셉션 방지 stmt,con가 연결이 되어있다면 연결을 끊어랑 ^ㅡ^
		
		if(stmt!=null) {
			stmt.close();
		}//end if
		if(con!=null) {
			con.close();
		}//end if
	}//end insert
	
	public int update(TestJdbcVO tjVo)throws SQLException{
		int cnt = 0;
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection con = null;
		Statement stmt = null;
		
		try {
		//2. Connection 얻기
		con = DriverManager.getConnection(url,id,pass);
		//3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();
		//4. 쿼리 수행 후 결과 얻기
			//번호에 해당하는 키와 이름을 변경
			StringBuilder updateTestJdbc = new StringBuilder();
			updateTestJdbc
			.append("update test_jdbc")
			.append(" set name = '").append(tjVo.getName()).append("',")
			.append("height=").append(tjVo.getHeight())
			.append("where num= ").append(tjVo.getNum());
			

			
			
			cnt=stmt.executeUpdate(updateTestJdbc.toString());
			System.out.println(cnt);
		//5. 연결 끊기
		}finally {
			//5. 연결 끊기
			if(stmt!=null) {
				stmt.close();
			}//end if
			if(con!=null) {
				con.close();
			}//end if	
		}//end finally
		return cnt;
	}//end update
	
	public int delete(int num)throws SQLException{
		int cnt = 0;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con = null;
		Statement stmt = null;
		try {
		//2. 커넥션 얻기
		con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성 객체 얻기
		stmt = con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
		StringBuilder deleteTestJdbc = new StringBuilder();
		deleteTestJdbc.append("delete from test_jdbc where num = ").append(num);
		
		cnt = stmt.executeUpdate(deleteTestJdbc.toString());
		
		
		}finally {
		//5. 연결 끊기
			if(con!=null) {
				con.close();
			}//end if
			if(stmt!=null) {
				stmt.close();
			}//end if
			
		}//end finally
		
		return cnt;
	}
	
	
	public List<TestJdbcVO> selectAll() throws SQLException{
		List<TestJdbcVO> list = new ArrayList<TestJdbcVO>();
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection 얻기
		con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성 객체 얻기
		stmt=con.createStatement();
		//4. 쿼리문 실행 후 결과 얻기
		String selectTestjdbc = "select num,name,gender,height,input_date from test_jdbc";
		
		rs = stmt.executeQuery(selectTestjdbc);//쿼리문을 실행하고 CURSOR의 제어권을 받는다.
		

		int num = 0;
		String name = "";
		String gender = "";
		double height = 0.0;
		Date date = null;
		
		TestJdbcVO tjVO = null;
		
		while(rs.next()) { // 레코드가 존재하는지 알 수 없지만 존재한 다면 모든 레코드를 가져와야 한다.
			//커서 다음에 레코드가 존재하면 TRUE를 반환하여 커서의 위치를 아래로 이동
			num = rs.getInt("num");    //number = > int
			name=rs.getString("name");  //vachar2 = > String
			gender=rs.getString("gender"); //char = > String
			height=rs.getDouble("height");  //number = > double
			date = rs.getDate("input_date");  //date = > java.sql.date
			
			//조회한 레코드의 컬럼 값을 VO에 저장
			tjVO = new TestJdbcVO(num, name, gender, height, date);
			//같은 이름의 VO객체를 사라지지 않게 관리하기 위해 List에 추가
			list.add(tjVO);
		}//end while
		
		
		}finally {
			//5. 연결 끊기
			if(rs!=null) {
				rs.close();
			}
			if(con!=null) {
				con.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			
		}//end finally
		
		return list;
		
	}//selectAll
	
	public TestJdbcVO selectOne( int num ) throws SQLException{
		TestJdbcVO tjVO = null;
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. 커넥트 연결
			con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 객체 사용 실행으로 결과 값 얻기
			StringBuilder selectTestJdbc = new StringBuilder();
			selectTestJdbc
			.append("	select		name,gender,height,input_date")
			.append("	from		test_jdbc")
			.append("	where		num=").append(num);
			
			rs=stmt.executeQuery(selectTestJdbc.toString());
			
			if(rs.next()) {//검색된 레코드가 존재하니?
				//VO를 생성하여 검색 결과를 할당
				tjVO=new TestJdbcVO(num, rs.getString("name"), rs.getString("gender"), rs.getDouble("height"), rs.getDate("input_date"));
			}//end if
			
		}finally {
		//5. 커넥트, 생성객체, 결과 끊기
			if( rs != null ) { rs.close(); }//end if
			if( stmt != null ) { stmt.close(); }//end if
			if( con != null ) { con.close(); }//end if
		}
		
		return tjVO;
	}//selectOne
	
	
	
	
	

}//class
