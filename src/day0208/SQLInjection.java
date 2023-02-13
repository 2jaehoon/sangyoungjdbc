package day0208;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.dao.conn.DbConnection;

@SuppressWarnings("serial")
public class SQLInjection extends JFrame implements ActionListener {
	
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JLabel jlblOutput;
	
	 public SQLInjection() {
		super("로그인");
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		jlblOutput=new JLabel("출력창");
		
		jtfId.setBorder(new TitledBorder("아이디"));
		jpfPass.setBorder(new TitledBorder("비밀번호"));
		jlblOutput.setBorder(new TitledBorder("출력창"));
		
		setLayout(new GridLayout(3, 1));
		
		add(jtfId);
		add(jpfPass);
		add(jlblOutput);
		
		jtfId.addActionListener(this);
		jpfPass.addActionListener(this);
		
		setBounds(100, 100, 300,200);
		setVisible(true);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
	}//SQLInjection
	
	 private void chkNull() {
		 
		 String id=jtfId.getText().trim();
		 
		 if( "".equals(id)) {
			 JOptionPane.showMessageDialog(this, "아이디는 필수 입력");
			 jtfId.requestFocus();
			 return;
		 }//end if
		 
		 String pass=new String(jpfPass.getPassword());
		 if( "".equals(pass)) {
			 JOptionPane.showMessageDialog(this, "비밀번호는 필수 입력");
			 jpfPass.requestFocus();
			 return;
		 }//end if
		 
		 
		
		try {
//			login( id, pass);
			preparedStatementLogin(id, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		 
	 }//chkNull
	 
	 private void login(String id, String pass) throws SQLException{
		 DbConnection db = DbConnection.getInstance();
		 
		 Connection con = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		 
		 try {
		 //1.
		 //2.
			 con=db.getConn();
		 //3.
			 stmt = con.createStatement();
		 //4.
			 StringBuilder selectName = new StringBuilder();
			 selectName
			 .append(" select name")
			 .append(" from test_injection ")
			 .append(" where id = '").append(blockInjection(id)).append("' and pass = '").append(blockInjection(pass)).append("'");
			 System.out.println(selectName.toString());
			 
			 rs=stmt.executeQuery(selectName.toString());
			 
			 String name = "";
			 if( rs.next() ) {
				 name = rs.getString("name");
			 }//end if
			 
			 String result = "아이디나 비밀번호를 확인하세요";
			 if( !name.isEmpty() ) {
				 result=name+"님 로그인하셨습니다.";
			 }//end if
			 
			 jtfId.setText("");
			 jpfPass.setText("");
			 
			 jlblOutput.setText(result);
			 
		 }finally {
		 //5.
			 db.dbClose(rs, stmt, con);
		 }//end finally
	 }//login
	
	 
	 
	 
	 
	 private void preparedStatementLogin(String id, String pass) throws SQLException{
		 DbConnection db = DbConnection.getInstance();
		 
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
		 //1.
		 //2.
			 con=db.getConn();
		 //3.
			 StringBuilder selectName = new StringBuilder();
			 selectName
			 .append(" select name")
			 .append(" from test_injection ")
			 .append(" where id = ? and pass = ?");
			 System.out.println(selectName.toString());
			 
			 pstmt = con.prepareStatement(selectName.toString());
		 //4.
			 
			 pstmt.setString(1, id);
			 pstmt.setString(2, pass);
			 rs=pstmt.executeQuery(); //pstmt는 쿼리를 위에서 처리
			 
			 String name = "";
			 if( rs.next() ) {
				 name = rs.getString("name");
			 }//end if
			 
			 String result = "아이디나 비밀번호를 확인하세요";
			 if( !name.isEmpty() ) {
				 result=name+"님 로그인하셨습니다.";
			 }//end if
			 
			 jtfId.setText("");
			 jpfPass.setText("");
			 
			 jlblOutput.setText(result);
			 
		 }finally {
		 //5.
			 db.dbClose(rs, pstmt, con);
		 }//end finally
	 }//nnection.getInstance();
	 

	 
	 private String blockInjection(String sql) {
		 String block = "";
		 
		 block = sql.replaceAll(" ", "").replaceAll("'", "").replaceAll("-", "").
				 replaceAll("select", "").replaceAll("insert", "");
		 
		 return block;
	 }
	 
	 

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jtfId || ae.getSource() == jpfPass) {
			chkNull();
		}//end if
	}//actionPerformed

	public static void main(String[] args) {
		new SQLInjection();
	}//main

}//class
