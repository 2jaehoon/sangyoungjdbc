package day0207;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WorkCardEvt implements ActionListener, ListSelectionListener {
	private WorkCardService wcs;
	private WorkDAO wDAO;
	private DefaultListModel<WorkVO> dlmVO;
	private DefaultListModel<String> dlmStr;
	
	public WorkCardEvt( WorkCardService wcs) {
		this.wcs=wcs;
		wDAO=WorkDAO.getInstance();
//		setList();
		setList2();
		
	}//WorkCardEvt
	
	private void setList() {
		
		dlmVO = wcs.getDlmVO();
		dlmVO.removeAllElements();
		try {
//			dlmVO.addAll();
			dlmVO.addAll(wDAO.selectAllTabNum());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(wcs, "프로그램에서 문제가 발생하였습니다.");
			e.printStackTrace();
		}//end
		
	}//setList
	
private void setList2() {
		
		dlmStr = wcs.getDlmStr();
		dlmStr.removeAllElements();
		dlmStr.addAll(searchAll());
		
	}//setList
	
	public List<String> searchAll() {
		
		String data="";
		WorkDAO wDAO=WorkDAO.getInstance();
		List<String> list2 = new ArrayList<String>();
		
		try {
			List<WorkVO> list=wDAO.selectAllTabNum();
			if( list.isEmpty() ) {
				System.out.println("레코드가 존재하지 않습니다.");
			}//end if
			
			StringBuilder outData=new StringBuilder();
			String str =  "";
			
			for( WorkVO wVO : list ) {
				str = wVO.getNum() + ", "  + wVO.getName() + ", "  + wVO.getPhone() + ", "  + wVO.getEmail() + ", "  + wVO.getInputDate() ;
				
				list2.add(str);
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list2;
		
	}//searchAll
	
	public void insertVO() throws SQLException {
		WorkDAO wDAO=WorkDAO.getInstance();
		
			wDAO.insert(wcs.getJtfName().getText(), wcs.getJtfPhone().getText(), wcs.getJtfEmail().getText());
			
	}//insertVO
	
	public void deleteVO() throws SQLException {
		WorkDAO wDAO=WorkDAO.getInstance();
		
			wDAO.delete(Integer.parseInt(wcs.getJtfNum().getText() ) );
			//중복 값 삭제 안

	}//deleteVO
	
	public void updateVO() throws SQLException{
		WorkDAO wDAO = WorkDAO.getInstance();
		
		wDAO.modify(wcs.getJtfName().getText(), wcs.getJtfPhone().getText(), wcs.getJtfEmail().getText(), Integer.parseInt(wcs.getJtfNum().getText() )  );
//		wDAO.modify("아예","아예","아예",2);
		
	}//updateVO

	public void setJtf() {
		String jlValue = wcs.getJlVO().getSelectedValue().toString();
		
		wcs.getJtfNum().setText(jlValue.substring(jlValue.indexOf("=")+1,jlValue.indexOf("," ) ) );
		wcs.getJtfName().setText(jlValue.substring(jlValue.indexOf(",")+2 , jlValue.indexOf(",",jlValue.indexOf(",")+1 ) ) );
		wcs.getJtfPhone().setText(jlValue.substring(jlValue.indexOf(",",jlValue.indexOf(",")+1)+2 , jlValue.indexOf(",",jlValue.indexOf(",",jlValue.indexOf(",")+1)+1 )) );
		wcs.getJtfEmail().setText(jlValue.substring(jlValue.indexOf(",",jlValue.indexOf(",",jlValue.indexOf(",")+1)+1 )+2, jlValue.indexOf(",",jlValue.indexOf(",",jlValue.indexOf(",",jlValue.indexOf(",")+1)+1)+1 ) ) );
		wcs.getJtfInputDate().setText(jlValue.substring( jlValue.indexOf(",",jlValue.indexOf(",",jlValue.indexOf(",",jlValue.indexOf(",")+1)+1)+1 )+2 ) );
		
	}//setJtf
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == wcs.getJbtnAdd()) {
		try {
			insertVO();
			setList2();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//end catch
		}//end if
		
		if(e.getSource() == wcs.getJbtnModify()) {
			try {
				updateVO();
				setList2();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//end catch
		}//end if
		
		if(e.getSource() == wcs.getJbtnDelete()) {
			try {
				deleteVO();
				setList2();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//end catch
		}//end if
		
		if(e.getSource() == wcs.getJbtnExit()) {
			wcs.dispose();
		}//end if
		
	}//actionPerformed

	@Override
	public void valueChanged(ListSelectionEvent e) {
		setJtf();
	}//valueChanged

}//class
