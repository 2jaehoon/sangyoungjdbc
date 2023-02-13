package day0207;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class WorkCardService extends JFrame {
	
	private JLabel jlblNum;
	private JLabel jlblName;
	private JLabel jlblPhone;
	private JLabel jlblEmail;
	private JLabel jlblInputDate;
	
	private JTextField jtfNum;
	private JTextField jtfName;
	private JTextField jtfPhone;
	private JTextField jtfEmail;
	private JTextField jtfInputDate;
	
	private JButton jbtnAdd;
	private JButton jbtnModify;
	private JButton jbtnDelete;
	private JButton jbtnExit;
	
	private WorkCardEvt wce;
	
	private JTextArea jtaOutput;
	private DefaultListModel<WorkVO> dlmVO;
	private DefaultListModel<String> dlmStr;
	
//	private JList<WorkVO> jlVO;
	private JList<String> jlVO;
	
	public WorkCardService() {
		super("명함관리");
		
		dlmVO = new DefaultListModel<WorkVO>();
		dlmStr = new DefaultListModel<String>();
//		jlVO = new JList<WorkVO>(dlmVO);
		jlVO = new JList<String>(dlmStr);
		
		
		jlVO.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		jlblNum=new JLabel("번호");
		jlblName=new JLabel("이름");
		jlblPhone=new JLabel("핸드폰번호");
		jlblEmail=new JLabel("이메일");
		jlblInputDate=new JLabel("입력일");
		
		jtfNum=new JTextField();
		jtfName=new JTextField();
		jtfPhone=new JTextField();
		jtfEmail=new JTextField();
		jtfInputDate=new JTextField();
		
		jbtnAdd=new JButton("추가");
		jbtnModify=new JButton("변경");
		jbtnDelete=new JButton("삭제");
		jbtnExit=new JButton("종료");
		
		wce=new WorkCardEvt(this);
		
		jbtnAdd.addActionListener(wce);
		jbtnModify.addActionListener(wce);
		jbtnDelete.addActionListener(wce);
		jbtnExit.addActionListener(wce);
		
		jlVO.addListSelectionListener(wce);
		
		
		JScrollPane jsp=new JScrollPane(jlVO);
		
		JPanel jpSouth=new JPanel();
		jpSouth.add(jbtnAdd);
		jpSouth.add(jbtnModify);
		jpSouth.add(jbtnDelete);
		jpSouth.add(jbtnExit);
		
		//수동배치
		setLayout(null);
		
		add(jlblNum);
		add(jlblName);
		add(jlblPhone);
		add(jlblEmail);
		add(jlblInputDate);
		
		add(jtfNum);
		add(jtfName);
		add(jtfPhone);
		add(jtfEmail);
		add(jtfInputDate);
		
		add(jsp);
		
		add(jpSouth);
		
		jlblNum.setBounds(30,40,70,50);
		jlblName.setBounds(30,90,70,50);
		jlblPhone.setBounds(30,140,70,50);
		jlblEmail.setBounds(30,190,70,50);
		jlblInputDate.setBounds(30,240,70,50);
		
		jtfNum.setBounds(120, 50, 100, 30);
		jtfName.setBounds(120, 100, 100, 30);
		jtfPhone.setBounds(120, 150, 100, 30);
		jtfEmail.setBounds(120, 200, 100, 30);
		jtfInputDate.setBounds(120, 250, 100, 30);
		
		jsp.setBounds(280, 50, 350, 230);
		
		jpSouth.setBounds(200, 310, 300, 100);
		
		setBounds(100, 100, 700, 400);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(JFrame.ABORT);
			}
		});
		
	}//WorkBusinessCardService
	
	public DefaultListModel<String> getDlmStr() {
		return dlmStr;
	}

	public void setDlmStr(DefaultListModel<String> dlmStr) {
		this.dlmStr = dlmStr;
	}

	public void setDlmVO(DefaultListModel<WorkVO> dlmVO) {
		this.dlmVO = dlmVO;
	}

	public DefaultListModel<WorkVO> getDlmVO() {
		return dlmVO;
	}

//	public JList<WorkVO> getJlVO() {
//		return jlVO;
//	}

	public JList<String> getJlVO() {
		return jlVO;
	}

	public JTextField getJtfNum() {
		return jtfNum;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public JButton getJbtnDelete() {
		return jbtnDelete;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public JTextArea getJtaOutput() {
		return jtaOutput;
	}

	public static void main(String[] args) {
		new WorkCardService();
	}//main

}//class
