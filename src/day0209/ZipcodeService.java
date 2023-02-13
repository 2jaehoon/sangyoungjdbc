package day0209;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ZipcodeService {
	
	public void searchZipcode() {
		
		String dong = JOptionPane.showInputDialog("�� �̸��� �Է�!!!\n��)�󵵵�, ����", "�󵵵�");
		
		if(dong != null) {
			ZipcodeDAO zDAO = new ZipcodeDAO();
			
			try {
				List<ZipcodeVO> list = zDAO.selectZipcode(dong);
//				List<ZipcodeVO> list = zDAO.statementSelectZipcode(dong);
				
				StringBuilder output = new StringBuilder();
				output.append(dong + "���� �˻��� ���\n")
				.append("-----------------------------------------\n")
				.append("�����ȣ\t�ּ�\n")
				.append("-----------------------------------------\n");
				
				if(list.isEmpty()) {
					output.append("�����ϴ�. �� �̸��� Ȯ�� ���ּ���.");
				}//end if
				
				for(ZipcodeVO zVO : list) {
					output
					.append(zVO.getZipcode()).append("\t")
					.append(zVO.getSido()).append(" ")
					.append(zVO.getGugun()).append(" ")
					.append(zVO.getDong()).append(" ")
					.append(zVO.getBunji()).append(" \n");
				}//end for
				output.append("-----------------------------------------------------\n");
				output.append("�˻��� ���� ����").append(list.size()).append("�� �Դϴ�.");
				
				JTextArea jta = new JTextArea(10,50);
				jta.setText(output.toString());
				JScrollPane jsp = new JScrollPane(jta);
				
				JOptionPane.showMessageDialog(null, jsp);
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}//searchZipcode

	public static void main(String[] args) {
		new ZipcodeService().searchZipcode();

	}//main

}//class
