package day0206;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import day0202.TestJdbcVO;

/**
 * Service : business logic ó��
 * 
 * @author user
 *
 */
public class PreparedStatementService {

	public void add() {
		String inputData = JOptionPane.showInputDialog("��ȣ, �̸�, ����, Ű�� ,�� �����Ͽ� �Է�");

		if (inputData != null) {
			String[] data = inputData.split(",");
			if (data.length != 4) {
				JOptionPane.showMessageDialog(null, "�Է� ���� 4���̿��߸� �մϴ�.");
				return;
			} // end if
			for(int i=0; i<data.length;i++) {
				System.out.println(data[i]);
			}//end for
			
			//������ ��ó��
			int num = 0;
			double height = 0.0;
					try {
					height = Double.parseDouble(data[3]);
					num=Integer.parseInt(data[0]);
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "��ȣ �Ǵ� Ű�� ���� �����̾�� �մϴ�.");
						return; //early �Ʒ� ���� ���� ���ϰ� �ö󰣴�. return else�� ���ص� �����ϰ� elseȿ���� ����
					}//end catch
					
					String name = data[1];
					String gender = data[2];
					if(!(gender.toUpperCase().equals("M") || gender.toUpperCase().equals("F") ) ) {
						JOptionPane.showMessageDialog(null, "������ M,m,F,f�θ� �Է� �����մϴ�.");
						return; //early return
					}//end if
					
		
			// �Էµ� ������ VO�� ����
//			TestJdbcVO tjVO = new TestJdbcVO(); setter ���
//			tjVO.setNum(Integer.parseInt(data[0]));
			
			// �� ó�� �̿�
			TestJdbcVO tjVO = new TestJdbcVO(num, name, gender, height, null);
			
			PreparedStatementDAO pstmtDAO = PreparedStatementDAO.getInstance();
			
			try {
				pstmtDAO.insert(tjVO);
				 JOptionPane.showMessageDialog(null, "�߰� ����");
			} catch (SQLException e) {
				
				String errMsg = "";
				//SQLException�� �߻��� ������ �ڵ带 ���� �� �ִ�.
				int errCode = e.getErrorCode();
				
				switch(errCode) {
				case 1: errMsg = "�����ϴ� ��ȣ�Դϴ�. �ٸ� ��ȣ�� �Է����ּ���.";
				break;
				case 12899: errMsg = "�̸��� �ѱ� 10��, ���� 30�ڱ����� �����մϴ�.";
				break;
				case 1438: errMsg = "��ȣ�� 5�� Ű�� �Ҽ��� ���� 4�ڱ����� ����";
				break;
				default:
					break;
				}//end switch
				JOptionPane.showMessageDialog(null, errMsg + " / " + errCode);
				e.printStackTrace();
			}//end catch
			
			
		}// end if
	}//add
	
	public void modify() {
		String inputData = JOptionPane.showInputDialog("��ȣ, �̸�, Ű�� �Է����ּ���.\n��ȣ�� �ش��ϴ� �̸��� Ű�� ����,");
		
		if(inputData!=null) {
			String[] data = inputData.split(",");
			
			if(data.length!=3) {
				JOptionPane.showMessageDialog(null, "��ȣ, �̸�, Ű�� �Է����ּ���.");
				return;// �Ʒ����� ������� �ʰ� ealry return
			}//end if
			
			TestJdbcVO tjVO = new TestJdbcVO();
			tjVO.setName(data[1]);
			try {
				tjVO.setNum(Integer.parseInt(data[0]));
				tjVO.setHeight(Double.parseDouble(data[2]));
				
			}catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "��ȣ�� Ű�� �����Դϴ�.");
				return;
			}
			
			PreparedStatementDAO psDAO = PreparedStatementDAO.getInstance();
			try {
			int cnt = psDAO.update(tjVO);
			String msg = "������ ������� �ʾҽ��ϴ�. ��ȣ�� Ȯ���ϼ���.";
			if(cnt>0) {// ����� ������ 1�� �̻�
				msg = tjVO.getNum()+"�� ������ ����Ǿ����ϴ�.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
			}catch(SQLException e) {
				
				String errMsg = "";
				//SQLException�� �߻��� ������ �ڵ带 ���� �� �ִ�.
				int errCode = e.getErrorCode();
				
				switch(errCode) {
				case 12899: errMsg = "�̸��� �ѱ� 10��, ���� 30�ڱ����� �����մϴ�.";
				break;
				case 1438: errMsg = "��ȣ�� 5�� Ű�� �Ҽ��� ���� 4�ڱ����� ����";
				break;
				default:
					break;
				}//end switch
				JOptionPane.showMessageDialog(null, errMsg + " / " + errCode);		
				e.printStackTrace();
			}//end catch
			
		}//end if
	}//modify
//	
	public void remove() {
		String inputData = JOptionPane.showInputDialog("������ ��ȣ�� �Է��ϼ���");
		
		if(inputData!=null){
			int num = 0;
			try {
			num = Integer.parseInt(inputData);
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "��ȣ�� ���ڷθ� �Է����ּ���.");
				return;
			}//end catch
			
			PreparedStatementDAO pstmtDAO = PreparedStatementDAO.getInstance();
			try {
				
				int cnt = pstmtDAO.delete(num);//1�� �Ǵϱ� �ƹ��͵� ���� �� 0 �����㋞ �׳� ����
				String  msg = "��ȣ�� Ȯ���ϼ���.";
				if(cnt!=0) {
					msg=num+"���� �����Ǿ����ϴ�.";
				}//end if
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end if
		
	}//remove
//	
//	
	public void searchAll() {
		PreparedStatementDAO pstmtDAO = PreparedStatementDAO.getInstance();
		
		try {
			List<TestJdbcVO> list = pstmtDAO.selectAll();
			if( list.isEmpty() ) {
				System.out.println("���ڵ尡 �������� �ʽ��ϴ�.");
			}//end if
			
			StringBuilder outData = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy EEEE");
			for(TestJdbcVO tjvo : list) {
				outData.append(tjvo.getNum() ).append(" ")
				.append(tjvo.getName()).append(" ")
				.append(tjvo.getGender()).append(" ")
				.append(tjvo.getHeight()).append(" ")
				.append(sdf.format(tjvo.getInput_date()) ).append("\n");
			}//end for
			
			JTextArea jta  = new JTextArea(5,60);
			jta.setText(outData.toString());
			
			JScrollPane jsp = new JScrollPane(jta);
			
			JOptionPane.showMessageDialog(null, jsp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//searchAll
//	
	public void searchOne() {
		int num = 2;
		
		PreparedStatementDAO pstmtDAO = PreparedStatementDAO.getInstance();
		try {
			TestJdbcVO tjVO = pstmtDAO.selectOne(num);
			StringBuilder searchResult = new StringBuilder();
			if(tjVO == null) {
				searchResult.append("�˻��� ����� �����ϴ�.");
			}else {
				searchResult.append("�˻� ��� \n")
				.append("�̸� : ").append(tjVO.getName()).append("\n")
				.append("���� : ").append(tjVO.getGender()).append("\n")
				.append("Ű : ").append(tjVO.getHeight()).append("\n")
				.append("�Է��� : ").append(tjVO.getInput_date()).append("\n");
			}
			
			JOptionPane.showMessageDialog(null, searchResult.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//searchOne
	
	

	public static void main(String[] args) {
		PreparedStatementService ss = new PreparedStatementService();
//		ss.add();
		ss.modify();
//		ss.remove();
//		ss.searchAll();
//		ss.searchOne();
	}// main

}// class
