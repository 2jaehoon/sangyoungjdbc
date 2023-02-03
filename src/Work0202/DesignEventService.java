package Work0202;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DesignEventService extends WindowAdapter implements ActionListener {
	private Design ds;
	//�׼��� �߻��Ǹ� ��ü�� design��ü�� ���´�.

	public DesignEventService(Design ds) {
		this.ds = ds;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ds.dispose();
	}// windowClosing
	
	public void insert() {
		DAO da = new DAO();
		VO vo = new VO();	
		//�׼��� �߻��Ǹ� ��ü�� design ds��ü�� ���´�.
		//da�� ��ü ������ �μ�Ʈ �޼��� ����
		//ds��ü�� ���԰� vo �ּҸ� �־��ֱ� ���� ��ü ������ �־���
		try {
			if(!(vo.getName(ds).isEmpty())) { //���� ����ִٸ� �μ�Ʈ 
			da.insert(vo,ds); //insert���� ds (������ ��ü) �� �ִ� ������ ?������?  vo ��ü���ó� ���� ���� �ʾҴµ� 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		if (e.getSource() == ds.getJbName()) {
			JOptionPane.showMessageDialog(ds, "�غ����Դϴ� ^^");
		}

		if (e.getSource() == ds.getJbInput()) {
			insert();
		
			
		}

	}

}
