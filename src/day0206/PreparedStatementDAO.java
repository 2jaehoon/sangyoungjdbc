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
 * DB�۾��� ���õ� �ڵ常 �����ϴ� Ŭ����
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
		
		
		
		//1. ����̹� �ε�
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		//2. �ε��ȵ���̹��� ����Ͽ� "Connection ���"
		try {
			con=dbCon.getConn();
		
		//3. �������� �����ϰ� ������ �� �ִ� "������ ���� ��ü ���"
			// ���������� ���� ���� ������ ?(bind ����)�� ó���Ѵ�.
			String insertTestJdbc 
			= " insert into test_jdbc(num, name, gender, height, input_date) values(?,?,?,?,sysdate)";
			
			pstmt=con.prepareStatement(insertTestJdbc);
			
			//4. �������� ���� �������� �ʱ� ������ bind����(?)�� �� ����
			pstmt.setInt(1, tjVO.getNum() );
			pstmt.setString(2, tjVO.getName());
			pstmt.setString(3, tjVO.getGender());
			pstmt.setDouble(4, tjVO.getHeight());
			
		//5. ������ ���� �� ��� �ޱ�
			pstmt.executeUpdate();
			
		}finally {
			//5. ���� ���� 
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
	}//insert
	
	public int update(TestJdbcVO tjVo)throws SQLException{
		
		int cnt = 0;
DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		//1. ����̹� �ε�
		//2. Ŀ�ؼ� ���
			con=dbCon.getConn();
		//3. ������ ���� ��ü ���
			StringBuilder updateTestJdbc = new StringBuilder();
			updateTestJdbc
			.append("update test_jdbc ")
			.append(" set name=?, height=? ")
			.append("where num=?");
			
			pstmt=con.prepareStatement(updateTestJdbc.toString());
			
		//4. ���ε� ������ �� ����
			pstmt.setString(1, tjVo.getName());
			pstmt.setDouble(2, tjVo.getHeight());
			pstmt.setInt(3, tjVo.getNum());
		//5. ������ ���� �� ��� ���
			cnt = pstmt.executeUpdate();
		}finally {
		//6. ���� ����
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
		//1. ����̹� �ε�
		//2. Ŀ�ؼ� ���
			con=dbCon.getConn();
		//3. ������ ���� ��ü
			String deleteTestJdbc = "delete from test_jdbc where num=?";
			pstmt = con.prepareStatement(deleteTestJdbc);
		//4. ���ε� ������ �� ����
			pstmt.setInt(1, num);
		//5. ������ ���� �� ��� ���
			cnt=pstmt.executeUpdate();
			System.out.println(cnt);
		}finally {
		//6. ���� ����
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
		
		return cnt;
	}//delete
	
	
	public List<TestJdbcVO> selectAll() throws SQLException{
		List<TestJdbcVO> list = new ArrayList<TestJdbcVO>();
		
		//1. ����̹� �ε�
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
		//2. Connection ���
		con = db.getConn();
		//3. ������ ���� ��ü ���
		//4. ������ ���� �� ��� ���
		String selectTestjdbc = "select num,name,gender,height,input_date from test_jdbc";
		pstmt=con.prepareStatement(selectTestjdbc.toString());
		
		rs = pstmt.executeQuery();//�������� �����ϰ� CURSOR�� ������� �޴´�.
		

		int num = 0;
		String name = "";
		String gender = "";
		double height = 0.0;
		Date date = null;
		
		TestJdbcVO tjVO = null;
		
		while(rs.next()) { // ���ڵ尡 �����ϴ��� �� �� ������ ������ �ٸ� ��� ���ڵ带 �����;� �Ѵ�.
			//Ŀ�� ������ ���ڵ尡 �����ϸ� TRUE�� ��ȯ�Ͽ� Ŀ���� ��ġ�� �Ʒ��� �̵�
//			num = rs.getInt("num");    //number = > int
//			name=rs.getString("name");  //vachar2 = > String
//			gender=rs.getString("gender"); //char = > String
//			height=rs.getDouble("height");  //number = > double
//			date = rs.getDate("input_date");  //date = > java.sql.date
			
			//��ȸ�� ���ڵ��� �÷� ���� VO�� ����
			tjVO = new TestJdbcVO(rs.getInt("num"), rs.getString("name"), rs.getString("gender"), rs.getDouble("height"), rs.getDate("input_date"));
			//���� �̸��� VO��ü�� ������� �ʰ� �����ϱ� ���� List�� �߰�
			list.add(tjVO);
			// �����Ǵ� �����ٸ� tjVO�� ��ü ������ �����ϱ� ���� ����Ʈ�� ����
//			System.out.println(tjVO);
		}//end while
		
		}finally {
			//5. ���� ����
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
		//1. ����̹� �ε�
		
		try {
		con = db.getConn();
		//4. ������ ��ü ��� �������� ��� �� ���
			StringBuilder selectTestJdbc = new StringBuilder();
			selectTestJdbc
			.append("	select		name,gender,height,input_date")
			.append("	from		test_jdbc   ")
			.append("	where		num=?   ");
			
			pstmt = con.prepareStatement(selectTestJdbc.toString());
			
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//�˻��� ���ڵ尡 �����ϴ�?
				//VO�� �����Ͽ� �˻� ����� �Ҵ�
				tjVO=new TestJdbcVO(num, rs.getString("name"), rs.getString("gender"), rs.getDouble("height"), rs.getDate("input_date"));
			}//end if
			
		}finally {
		//5. Ŀ��Ʈ, ������ü, ��� ����
			db.dbClose(rs, pstmt, con);
		}
		
		return tjVO;
	}//selectOne
	
	
	
	
	
	
	

}//class
