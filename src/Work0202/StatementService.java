package Work0202;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StatementService extends JFrame {

	JButton jbtnInsert;
	JTextField jtfName;
	JButton jbtnName;
	
	public StatementService() {
		super("입력");
		jbtnName = new JButton("이름");
		jbtnInsert = new JButton("입력");
		jtfName = new JTextField("이름을 입력해주세요.");

		add(jbtnName);
		add(jtfName);
		add(jbtnInsert);

		jbtnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jbtnInsert) {
					add();
				} // end if
			}// actionPerformed
		});

		setLayout(new GridLayout(1, 3));
		setBounds(500, 500, 400, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// StatementService

	public void add() {
//		String inputData = JOptionPane.showInputDialog("이름 입력");
		String name;
		
		if (!jtfName.getText().isEmpty()) {
			name = jtfName.getText();

			JdbcVO tjVO = new JdbcVO(name);

			StatementDAO stmtDAO = new StatementDAO();

			try {
				stmtDAO.insert(tjVO); // 잘하셨어요 감사합니당
				JOptionPane.showMessageDialog(this, "추가성공");
			} catch (SQLException se) {
				String errMsg = "";
				// SQLException은 발생된 예외의 코드를 얻을 수 있다.
				int errCode = se.getErrorCode();

				switch (errCode) {

				case 12899:
					errMsg = "이름은 한글 5자, 영문 15자 까지만 가능합니다.";
					break;
				}// end case

				JOptionPane.showMessageDialog(this, errMsg + "/" + errCode);
				se.printStackTrace();
			} // end catch
		} // end if
		
		if (jtfName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요.");
			return;
		} // end if
		
	}// add
	

	public static void main(String[] args) {
		new StatementService();

	}// main

}// class
