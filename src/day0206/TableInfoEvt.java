package day0206;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
	
	private void setTableDesc(String tName) {
		try {
			List<TableVO> list = tDAO.selectTableColumnData(tName);
			StringBuilder output = new StringBuilder();
			output
			.append("�÷���\t��������\tnull���\n")
			.append("----------------------------------------------------------\n");
			
		 for(TableVO tVO : list) {
			 output
			 .append(tVO.getColumnName()).append("\t")
			 .append(tVO.getcolumnLabel());
			 
			 if(tVO.getColumSize()!=0) {
			 output.append("(")
			 .append(tVO.getColumSize()).append(")");
			 }
			 
			 output.append("\t").append(tVO.getIsNull()).append("\n");
		 }//end for
		 
		 tis.getJtaTableView().setText(output.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//setTableDesc
	
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == tis.getJcTable()) { 
			if(tis.getJcTable().getSelectedIndex()!=0) {
			String tableName  = tis.getDcbmTable().getElementAt(tis.getJcTable().getSelectedIndex());
			
			switch(JOptionPane.showConfirmDialog(tis, tableName+"���̺��� ������ Ȯ���Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				setTableDesc(tableName);
			}//end switch
			
			
			}//end if
		}//end if
	}//actionPerformed

}//class
