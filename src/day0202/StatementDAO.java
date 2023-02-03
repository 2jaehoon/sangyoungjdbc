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
 * DB�۾��� ���õ� �ڵ常 �����ϴ� Ŭ����
 * @author user
 *
 */
public class StatementDAO {
	
	public void insert(TestJdbcVO tjVO) throws SQLException {
		
		
		
		//1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";// ���� db ����Ŭ ���콺 ������ properties
		String id = "scott";
		String pass = "tiger";
		
		//2. �ε��ȵ���̹��� ����Ͽ� "Connection ���"
		
			con=DriverManager.getConnection(url,id,pass);
		
		//3. �������� �����ϰ� ������ �� �ִ� "������ ���� ��ü ���"
			stmt=con.createStatement();
		
		//4. ������ ���� �� ��� �ޱ�
//			String insertTestJdbc = "insert,,,";
			StringBuilder insertTestJdbc = new StringBuilder();
			
			insertTestJdbc
			.append(" insert into test_jdbc(num, name, gender, height, input_date) values(  ")
			.append(tjVO.getNum()).append(",")
			.append("'").append(tjVO.getName()).append("',")
			.append("'").append(tjVO.getGender()).append("',")
			.append(tjVO.getHeight()).append(",")
			.append("sysdate)");//�ڹٿ��� sql�� ���鶧 ;�� ���� �ʴ´� error
			
//			System.out.println(insertTestJdbc.toString());
			
			/* int cnt = */stmt.executeUpdate(insertTestJdbc.toString());
			/*
			 * if(cnt == 1) { System.out.println("1�� insert ����!!"); }//end if
			 */			
		//5. ���� ���� nullpoint�ͼ��� ���� stmt,con�� ������ �Ǿ��ִٸ� ������ ����� ^��^
		
		if(stmt!=null) {
			stmt.close();
		}//end if
		if(con!=null) {
			con.close();
		}//end if
	}//end insert
	
	public int update(TestJdbcVO tjVo)throws SQLException{
		int cnt = 0;
		//1. ����̹� �ε�
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
		//2. Connection ���
		con = DriverManager.getConnection(url,id,pass);
		//3. ������ ���� ��ü ���
			stmt = con.createStatement();
		//4. ���� ���� �� ��� ���
			//��ȣ�� �ش��ϴ� Ű�� �̸��� ����
			StringBuilder updateTestJdbc = new StringBuilder();
			updateTestJdbc
			.append("update test_jdbc")
			.append(" set name = '").append(tjVo.getName()).append("',")
			.append("height=").append(tjVo.getHeight())
			.append("where num= ").append(tjVo.getNum());
			

			
			
			cnt=stmt.executeUpdate(updateTestJdbc.toString());
			System.out.println(cnt);
		//5. ���� ����
		}finally {
			//5. ���� ����
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
		
		//1. ����̹� �ε�
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
		//2. Ŀ�ؼ� ���
		con = DriverManager.getConnection(url, id, pass);
		//3. ������ ���� ��ü ���
		stmt = con.createStatement();
		//4. ������ ���� �� ��� ���
		StringBuilder deleteTestJdbc = new StringBuilder();
		deleteTestJdbc.append("delete from test_jdbc where num = ").append(num);
		
		cnt = stmt.executeUpdate(deleteTestJdbc.toString());
		
		
		}finally {
		//5. ���� ����
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
		
		//1. ����̹� �ε�
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
		//2. Connection ���
		con = DriverManager.getConnection(url, id, pass);
		//3. ������ ���� ��ü ���
		stmt=con.createStatement();
		//4. ������ ���� �� ��� ���
		String selectTestjdbc = "select num,name,gender,height,input_date from test_jdbc";
		
		rs = stmt.executeQuery(selectTestjdbc);//�������� �����ϰ� CURSOR�� ������� �޴´�.
		

		int num = 0;
		String name = "";
		String gender = "";
		double height = 0.0;
		Date date = null;
		
		TestJdbcVO tjVO = null;
		
		while(rs.next()) { // ���ڵ尡 �����ϴ��� �� �� ������ ������ �ٸ� ��� ���ڵ带 �����;� �Ѵ�.
			//Ŀ�� ������ ���ڵ尡 �����ϸ� TRUE�� ��ȯ�Ͽ� Ŀ���� ��ġ�� �Ʒ��� �̵�
			num = rs.getInt("num");    //number = > int
			name=rs.getString("name");  //vachar2 = > String
			gender=rs.getString("gender"); //char = > String
			height=rs.getDouble("height");  //number = > double
			date = rs.getDate("input_date");  //date = > java.sql.date
			
			//��ȸ�� ���ڵ��� �÷� ���� VO�� ����
			tjVO = new TestJdbcVO(num, name, gender, height, date);
			//���� �̸��� VO��ü�� ������� �ʰ� �����ϱ� ���� List�� �߰�
			list.add(tjVO);
		}//end while
		
		
		}finally {
			//5. ���� ����
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
		//1. ����̹� �ε�
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
		//2. Ŀ��Ʈ ����
			con = DriverManager.getConnection(url, id, pass);
		//3. ������ ���� ��ü ���
			stmt = con.createStatement();
		//4. ������ ��ü ��� �������� ��� �� ���
			StringBuilder selectTestJdbc = new StringBuilder();
			selectTestJdbc
			.append("	select		name,gender,height,input_date")
			.append("	from		test_jdbc")
			.append("	where		num=").append(num);
			
			rs=stmt.executeQuery(selectTestJdbc.toString());
			
			if(rs.next()) {//�˻��� ���ڵ尡 �����ϴ�?
				//VO�� �����Ͽ� �˻� ����� �Ҵ�
				tjVO=new TestJdbcVO(num, rs.getString("name"), rs.getString("gender"), rs.getDouble("height"), rs.getDate("input_date"));
			}//end if
			
		}finally {
		//5. Ŀ��Ʈ, ������ü, ��� ����
			if( rs != null ) { rs.close(); }//end if
			if( stmt != null ) { stmt.close(); }//end if
			if( con != null ) { con.close(); }//end if
		}
		
		return tjVO;
	}//selectOne
	
	
	
	
	

}//class
