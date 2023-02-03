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
	
	//�÷� �ϳ��� ���� ���� ��ȸ�Ǵ� ��� ( ��ȸ����� List�� ������ �� ��ȯ)
	//������� �ߺ��� �� �ִ�.
	public List<String> selectEnames(int deptno)throws SQLException{
		List<String> enameList = new ArrayList<String>();
		
		Connection con = null; // ����
		Statement stmt = null; // ������
		ResultSet rs = null; // cursor �����
		
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
			
			while(rs.next() ) { //���ڵ尡 �����ϸ� 
				enameList.add(rs.getString("ename") ); //Ŀ���� ��ġ���� ������� �����ͼ� ����Ʈ�� �߰��Ѵ�.
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
	
	
	//�÷� �ϳ��� ���� ���� ��ȸ�Ǵ� ��� (��ȸ����� List�� ������ �� ��ȯ)
	//��� ��ȣ�� �ߺ��� �� ����. Set��� ����
	public Set<Integer> selectEmpno(int deptno)throws SQLException{
		Set<Integer> empnoList = new HashSet<Integer>();
		
		Connection con = null; // ����
		Statement stmt = null; // ������
		ResultSet rs = null; // cursor �����
		
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
			
			while(rs.next() ) { //���ڵ尡 �����ϸ� 
				empnoList.add(rs.getInt("empno") ); //Ŀ���� ��ġ���� �����ȣ�� �����ͼ� ����Ʈ�� �߰��Ѵ�.
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
	
	//�÷� �� �ϳ� ��ȯ ( �����ȣ�� �Է¹޾� ����� ��ȯ)
 public String selectEname(int empno)throws SQLException{
	 String ename = "";
	 
	 Connection con = null;
	 Statement stmt = null;
	 ResultSet rs = null;
	 
	 try {
	 //1.
	 //2.
		 con = getConnection(); // 1,2���� ������ �ߺ��ڵ带 �ٿ���.
	 //3.
		 stmt=con.createStatement();
	 //4.
		 StringBuilder selectEname = new StringBuilder();
		 selectEname
		 .append("select ename                                       ")
		 .append("from emp                                      ")
		 .append("where empno = ").append(empno);
		 
		 rs=stmt.executeQuery(selectEname.toString());
		 
		 if(rs.next() ) {// �˻��� ���ڵ尡 �ִ�?
			 ename=rs.getString("ename"); //�˻� ����� �����ͼ� ���� ����
			 
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
