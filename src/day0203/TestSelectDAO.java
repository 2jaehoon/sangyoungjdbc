package day0203;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestSelectDAO {
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
				//1.
				try {
					Class.forName("oracle.jdbc.OracleDriver");
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}//end catch
				//2.
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				String id = "scott";
				String pass = "tiger";
				
				con = DriverManager.getConnection(url, id, pass);
				return con;
	}//getConnection
	
	//컬럼 하나에 여러 행이 조회되는 경우 ( 조회결과를 List에 저장한 후 반환)
	//사원명은 중복될 수 있다.
	public List<String> selectEnames(int deptno)throws SQLException{
		List<String> enameList = new ArrayList<String>();
		
		Connection con = null; // 연결
		Statement stmt = null; // 쿼리문
		ResultSet rs = null; // cursor 제어권
		
		try {
		//1.
		//2.
			con = getConnection();
		//3.
			stmt = con.createStatement();
		//4.
			StringBuilder selectEname = new StringBuilder();
			selectEname
			.append("		select ename															")
			.append("		from emp																")
			.append("		where deptno =").append(deptno);
			
			rs = stmt.executeQuery( selectEname.toString() );
			
			while(rs.next() ) { //레코드가 존재하면 
				enameList.add(rs.getString("ename") ); //커서의 위치에서 사원명을 가져와서 리스트에 추가한다.
			}//end while
//			System.out.println(enameList);
			
		}finally {
		//5
			if(rs!=null) {
				rs.close();
			}//end if
			if(stmt!=null) {
				stmt.close();
			}//end if
			if(con!=null) {
				con.close();
			}//end if
		}
		
		return enameList;
	}//selectEnames
	
	
	//컬럼 하나에 여러 행이 조회되는 경우 (조회결과를 List에 저장한 후 반환)
	//사원 번호는 중복될 수 없다. Set사용 가능
	public Set<Integer> selectEmpno(int deptno)throws SQLException{
		Set<Integer> empnoList = new HashSet<Integer>();
		
		Connection con = null; // 연결
		Statement stmt = null; // 쿼리문
		ResultSet rs = null; // cursor 제어권
		
		try {
		//1.
		//2.
			con = getConnection();
		//3.
			stmt = con.createStatement();
		//4.
			StringBuilder selectEmpno = new StringBuilder();
			selectEmpno
			.append("		select empno															")
			.append("		from emp																")
			.append("		where deptno =").append(deptno);
			
			rs = stmt.executeQuery( selectEmpno.toString() );
			
			while(rs.next() ) { //레코드가 존재하면 
				empnoList.add(rs.getInt("empno") ); //커서의 위치에서 사원번호을 가져와서 리스트에 추가한다.
			}//end while
//			System.out.println(enameList);
			
		}finally {
		//5
			if(rs!=null) {
				rs.close();
			}//end if
			if(stmt!=null) {
				stmt.close();
			}//end if
			if(con!=null) {
				con.close();
			}//end if
		}
		
		return empnoList;
	}//selectEnames
	
	//컬럼 값 하나 반환 ( 사원번호를 입력받아 사원명 반환)
 public String selectEname(int empno)throws SQLException{
	 String ename = "";
	 
	 Connection con = null;
	 Statement stmt = null;
	 ResultSet rs = null;
	 
	 try {
	 //1.
	 //2.
		 con = getConnection(); // 1,2번을 포함해 중복코드를 줄였다.
	 //3.
		 stmt=con.createStatement();
	 //4.
		 StringBuilder selectEname = new StringBuilder();
		 selectEname
		 .append("select ename                                       ")
		 .append("from emp                                      ")
		 .append("where empno = ").append(empno);
		 
		 rs=stmt.executeQuery(selectEname.toString());
		 
		 if(rs.next() ) {// 검색된 레코드가 있니?
			 ename=rs.getString("ename"); //검색 결과를 가져와서 변수 저장
			 
		 }//end if
		 
	 }finally {
	 //5.
		 if(rs!=null) {
			 rs.close();
		 }
		 if(stmt!=null) {
			 stmt.close();
		 }
		 if(con!=null) {
			 con.close();
		 }
	 }
	 return ename;
 }
	
	
	
	

}//class
