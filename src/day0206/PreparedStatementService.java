package day0206;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import day0202.TestJdbcVO;

/**
 * Service : business logic 처리
 * 
 * @author user
 *
 */
public class PreparedStatementService {

	public void add() {
		String inputData = JOptionPane.showInputDialog("번호, 이름, 성별, 키를 ,로 구분하여 입력");

		if (inputData != null) {
			String[] data = inputData.split(",");
			if (data.length != 4) {
				JOptionPane.showMessageDialog(null, "입력 값은 4개이여야만 합니다.");
				return;
			} // end if
			for(int i=0; i<data.length;i++) {
				System.out.println(data[i]);
			}//end for
			
			//데이터 전처리
			int num = 0;
			double height = 0.0;
					try {
					height = Double.parseDouble(data[3]);
					num=Integer.parseInt(data[0]);
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "번호 또는 키는 숫자 형태이어야 합니다.");
						return; //early 아래 줄을 실행 안하고 올라간다. return else를 안해도 간단하게 else효과를 누림
					}//end catch
					
					String name = data[1];
					String gender = data[2];
					if(!(gender.toUpperCase().equals("M") || gender.toUpperCase().equals("F") ) ) {
						JOptionPane.showMessageDialog(null, "성별은 M,m,F,f로만 입력 가능합니다.");
						return; //early return
					}//end if
					
		
			// 입력된 값으로 VO를 생성
//			TestJdbcVO tjVO = new TestJdbcVO(); setter 사용
//			tjVO.setNum(Integer.parseInt(data[0]));
			
			// 전 처리 이용
			TestJdbcVO tjVO = new TestJdbcVO(num, name, gender, height, null);
			
			PreparedStatementDAO pstmtDAO = PreparedStatementDAO.getInstance();
			
			try {
				pstmtDAO.insert(tjVO);
				 JOptionPane.showMessageDialog(null, "추가 성공");
			} catch (SQLException e) {
				
				String errMsg = "";
				//SQLException은 발생된 예외의 코드를 얻을 수 있다.
				int errCode = e.getErrorCode();
				
				switch(errCode) {
				case 1: errMsg = "존재하는 번호입니다. 다른 번호를 입력해주세요.";
				break;
				case 12899: errMsg = "이름은 한글 10자, 영문 30자까지만 가능합니다.";
				break;
				case 1438: errMsg = "번호는 5자 키는 소수점 포함 4자까지만 가능";
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
		String inputData = JOptionPane.showInputDialog("번호, 이름, 키를 입력해주세요.\n번호에 해당하는 이름과 키를 변경,");
		
		if(inputData!=null) {
			String[] data = inputData.split(",");
			
			if(data.length!=3) {
				JOptionPane.showMessageDialog(null, "번호, 이름, 키를 입력해주세요.");
				return;// 아랫줄이 싱행되지 않게 ealry return
			}//end if
			
			TestJdbcVO tjVO = new TestJdbcVO();
			tjVO.setName(data[1]);
			try {
				tjVO.setNum(Integer.parseInt(data[0]));
				tjVO.setHeight(Double.parseDouble(data[2]));
				
			}catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "번호나 키는 숫자입니다.");
				return;
			}
			
			PreparedStatementDAO psDAO = PreparedStatementDAO.getInstance();
			try {
			int cnt = psDAO.update(tjVO);
			String msg = "정보가 변경되지 않았습니다. 번호를 확인하세요.";
			if(cnt>0) {// 변경된 정보가 1건 이상
				msg = tjVO.getNum()+"번 정보가 번경되었습니다.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
			}catch(SQLException e) {
				
				String errMsg = "";
				//SQLException은 발생된 예외의 코드를 얻을 수 있다.
				int errCode = e.getErrorCode();
				
				switch(errCode) {
				case 12899: errMsg = "이름은 한글 10자, 영문 30자까지만 가능합니다.";
				break;
				case 1438: errMsg = "번호는 5자 키는 소수점 포함 4자까지만 가능";
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
		String inputData = JOptionPane.showInputDialog("삭제할 번호를 입력하세요");
		
		if(inputData!=null){
			int num = 0;
			try {
			num = Integer.parseInt(inputData);
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "번호는 숫자로만 입력해주세요.");
				return;
			}//end catch
			
			PreparedStatementDAO pstmtDAO = PreparedStatementDAO.getInstance();
			try {
				
				int cnt = pstmtDAO.delete(num);//1이 되니깐 아무것도 없을 땐 0 오류뜰떈 그냥 오류
				String  msg = "번호를 확인하세요.";
				if(cnt!=0) {
					msg=num+"번이 삭제되었습니다.";
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
				System.out.println("레코드가 존재하지 않습니다.");
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
				searchResult.append("검색된 결과가 없습니다.");
			}else {
				searchResult.append("검색 결과 \n")
				.append("이름 : ").append(tjVO.getName()).append("\n")
				.append("성별 : ").append(tjVO.getGender()).append("\n")
				.append("키 : ").append(tjVO.getHeight()).append("\n")
				.append("입력일 : ").append(tjVO.getInput_date()).append("\n");
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
