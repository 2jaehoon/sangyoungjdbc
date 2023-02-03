package day0203;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class TestSelectService {
	public void searchDeptInEname() {
		TestSelectDAO tsDAO = new TestSelectDAO();
		
		try {
			List<String> list  = tsDAO.selectEnames(30); //조회결과를 List에 저장하고
			System.out.println("검색 결과");
			if(list.isEmpty()) {
				System.out.println("해당부서는 사원이 존재하지 않습니다.");
			}//end if
			
			for ( String ename : list) { //리스트에 저장된 결과를 출력한다.
				System.out.println(ename);
			}//end for
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
	}//searchDeptInEname

	
	public void searchDeptInEmpno() {
		TestSelectDAO tsDAO = new TestSelectDAO();
		
		try {
			Set<Integer> list  = tsDAO.selectEmpno(30); //조회결과를 List에 저장하고
			System.out.println("검색 결과");
			if(list.isEmpty()) {
				System.out.println("해당부서는 사원이 존재하지 않습니다.");
			}//end if
			
			for ( Integer empno : list) { //리스트에 저장된 결과를 출력한다.
				System.out.println(empno);
			}//end for
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
	}//searchDeptInEname
	
	public void searchOneEmp() {
		TestSelectDAO tsDAO = new TestSelectDAO();
		
		try {
			int empno = 7521;
			String ename  = tsDAO.selectEname(empno);
			if(ename.isEmpty()) {
				System.out.println(empno + " 존재하지 않는 사원번호");
				return;
			}//end else
			JOptionPane.showInternalMessageDialog(null, empno+" 사원명은 " + ename);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestSelectService tss = new TestSelectService();
//		tss.searchDeptInEname();
//		tss.searchDeptInEmpno();
		tss.searchOneEmp();
	}//main

}//class
