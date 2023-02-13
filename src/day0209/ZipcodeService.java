package day0209;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ZipcodeService {
	
	public void searchZipcode() {
		
		String dong = JOptionPane.showInputDialog("동 이름을 입력!!!\n예)상도동, 역상동", "상도동");
		
		if(dong != null) {
			ZipcodeDAO zDAO = new ZipcodeDAO();
			
			try {
				List<ZipcodeVO> list = zDAO.selectZipcode(dong);
//				List<ZipcodeVO> list = zDAO.statementSelectZipcode(dong);
				
				StringBuilder output = new StringBuilder();
				output.append(dong + "으로 검색된 결과\n")
				.append("-----------------------------------------\n")
				.append("우편번호\t주소\n")
				.append("-----------------------------------------\n");
				
				if(list.isEmpty()) {
					output.append("없습니다. 동 이름을 확인 해주세요.");
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
				output.append("검색된 동의 개수").append(list.size()).append("건 입니다.");
				
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
