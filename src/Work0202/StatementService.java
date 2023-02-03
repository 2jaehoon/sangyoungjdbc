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
		super("�Է�");
		jbtnName = new JButton("�̸�");
		jbtnInsert = new JButton("�Է�");
		jtfName = new JTextField("�̸��� �Է����ּ���.");

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
//		String inputData = JOptionPane.showInputDialog("�̸� �Է�");
		String name;
		
		if (!jtfName.getText().isEmpty()) {
			name = jtfName.getText();

			JdbcVO tjVO = new JdbcVO(name);

			StatementDAO stmtDAO = new StatementDAO();

			try {
				stmtDAO.insert(tjVO); // ���ϼ̾�� �����մϴ�
				JOptionPane.showMessageDialog(this, "�߰�����");
			} catch (SQLException se) {
				String errMsg = "";
				// SQLException�� �߻��� ������ �ڵ带 ���� �� �ִ�.
				int errCode = se.getErrorCode();

				switch (errCode) {

				case 12899:
					errMsg = "�̸��� �ѱ� 5��, ���� 15�� ������ �����մϴ�.";
					break;
				}// end case

				JOptionPane.showMessageDialog(this, errMsg + "/" + errCode);
				se.printStackTrace();
			} // end catch
		} // end if
		
		if (jtfName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���.");
			return;
		} // end if
		
	}// add
	

	public static void main(String[] args) {
		new StatementService();

	}// main

}// class
