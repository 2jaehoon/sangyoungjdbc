package day0203;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class TestSelectService {
	public void searchDeptInEname() {
		TestSelectDAO tsDAO = new TestSelectDAO();
		
		try {
			List<String> list  = tsDAO.selectEnames(30); //��ȸ����� List�� �����ϰ�
			System.out.println("�˻� ���");
			if(list.isEmpty()) {
				System.out.println("�ش�μ��� ����� �������� �ʽ��ϴ�.");
			}//end if
			
			for ( String ename : list) { //����Ʈ�� ����� ����� ����Ѵ�.
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
			Set<Integer> list  = tsDAO.selectEmpno(30); //��ȸ����� List�� �����ϰ�
			System.out.println("�˻� ���");
			if(list.isEmpty()) {
				System.out.println("�ش�μ��� ����� �������� �ʽ��ϴ�.");
			}//end if
			
			for ( Integer empno : list) { //����Ʈ�� ����� ����� ����Ѵ�.
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
				System.out.println(empno + " �������� �ʴ� �����ȣ");
				return;
			}//end else
			JOptionPane.showInternalMessageDialog(null, empno+" ������� " + ename);
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
