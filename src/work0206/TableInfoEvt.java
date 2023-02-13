package work0206;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import day0202.TestJdbcVO;

public class TableInfoEvt implements ActionListener {
	
	private TableInfoService tis;
	private TableDAO tDAO;
	
	public TableInfoEvt(TableInfoService tis) {
		this.tis=tis;
		tDAO=TableDAO.getInstance();
		setTableName();
	}//TableInfoEvt
	
	private void setTableName() {
		//���̺���� ��ȸ�� ����� JCombobox�� ����
		DefaultComboBoxModel<String> dcbm = tis.getDcbmTable();
		try {
			dcbm.addElement("---���̺� ����---");
			dcbm.addAll(tDAO.selectAllTabName());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(tis, "���α׷����� ������ �߻��Ͽ����ϴ�.");
			e.printStackTrace();
		}//end
		
	}//setTableName
	

	
	public void asd() throws SQLException {
		String tableName  = tis.getDcbmTable().getElementAt(tis.getJcTable().getSelectedIndex());
		tis.getJtaTableView().setText(tDAO.selectOne(tableName));
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == tis.getJcTable()) { 
			if(tis.getJcTable().getSelectedIndex()!=0) {

				try {
					asd();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			
			}//end if
		}//end if
	}//actionPerformed

}//class
