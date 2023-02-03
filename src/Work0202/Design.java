package Work0202;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Design extends JFrame{
	//인스턴스 변수
	private JPanel jpJdbc;
	private JButton jbName;
	private JTextField jtfName;
	private JButton jbInput;
	
	public Design() {
		super("DBMS 연습");
		//컴포넌트 생성
		jpJdbc = new JPanel();
		jbName = new JButton("이름");
		jbInput = new JButton("입력");
		jtfName = new JTextField();
		
	    jpJdbc.add(jbName);
	    jpJdbc.add(jbInput);
	    jpJdbc.add(jtfName);
	    
	    //버튼 크기
	    jpJdbc.setBounds(300, 300, 500, 100);
	    jbName.setBounds(20, 30, 150, 50);
	    jtfName.setBounds(180, 30, 150, 50);
	    jbInput.setBounds(340, 30, 150, 50);

	    
	    
	    //수동
	    setLayout(null);
	    add(jpJdbc);
	    add(jbName);
	    add(jbInput);
	    add(jtfName);
	    
	    //이벤트 부여
	    jbName.addActionListener(new DesignEventService(this));// 이벤트 처리 클래스를 두번 생성할 필요는 없어요
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
