package day0206;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TableInfoService extends JFrame {

	private DefaultComboBoxModel<String> dcbmTable;
	private JComboBox<String> jcTable;
	private JTextArea jtaTableView;
	
	private TableInfoEvt tie;
	
	public TableInfoService() {
		super("테이블 구조");
		
		dcbmTable=new DefaultComboBoxModel<String>();
		jcTable=new JComboBox<String>(dcbmTable);
		jtaTableView=new JTextArea();
		JScrollPane jsp=new JScrollPane(jtaTableView);
		
		JPanel jpNorth=new JPanel();
		jpNorth.add( jcTable );
		
		add("North", jpNorth);
		add("Center",jsp);
		
		tie = new TableInfoEvt(this);
		jcTable.addActionListener(tie);
		
		setBounds(100, 100, 600, 400);
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
	}
	
	
	public DefaultComboBoxModel<String> getDcbmTable() {
		return dcbmTable;
	}





	public JComboBox<String> getJcTable() {
		return jcTable;
	}





	public JTextArea getJtaTableView() {
		return jtaTableView;
	}

	


	


	public static void main(String[] args) {
		new TableInfoService();
	}//main

}//class
