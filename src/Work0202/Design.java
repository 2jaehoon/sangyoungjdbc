package Work0202;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Design extends JFrame{
	//�ν��Ͻ� ����
	private JPanel jpJdbc;
	private JButton jbName;
	private JTextField jtfName;
	private JButton jbInput;
	
	public Design() {
		super("DBMS ����");
		//������Ʈ ����
		jpJdbc = new JPanel();
		jbName = new JButton("�̸�");
		jbInput = new JButton("�Է�");
		jtfName = new JTextField();
		
	    jpJdbc.add(jbName);
	    jpJdbc.add(jbInput);
	    jpJdbc.add(jtfName);
	    
	    //��ư ũ��
	    jpJdbc.setBounds(300, 300, 500, 100);
	    jbName.setBounds(20, 30, 150, 50);
	    jtfName.setBounds(180, 30, 150, 50);
	    jbInput.setBounds(340, 30, 150, 50);

	    
	    
	    //����
	    setLayout(null);
	    add(jpJdbc);
	    add(jbName);
	    add(jbInput);
	    add(jtfName);
	    
	    //�̺�Ʈ �ο�
	    jbName.addActionListener(new DesignEventService(this));// �̺�Ʈ ó�� Ŭ������ �ι� ������ �ʿ�� �����
	    jbInput.addActionListener(new DesignEventService(this));
	    setBounds(300, 300, 530, 150);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	
	}//Design

	public JTextField getJtfName() {
		return jtfName;
	}// getJtfName

	public JButton getJbName() {
		return jbName;
	}

	public void setJbName(JButton jbName) {
		this.jbName = jbName;
	}

	public JButton getJbInput() {
		return jbInput;
	}

	public void setJbInput(JButton jbInput) {
		this.jbInput = jbInput;
	}


}
