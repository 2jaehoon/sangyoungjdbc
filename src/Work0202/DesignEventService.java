package Work0202;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DesignEventService extends WindowAdapter implements ActionListener {
	private Design ds;
	//액션이 발생되면 객체가 design객체가 들어온다.

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
		//액션이 발생되면 객체가 design ds객체가 들어온다.
		//da를 객체 생성해 인설트 메서드 실행
		//ds객체는 들어왔고 vo 주소를 넣어주기 위해 객체 생성후 넣어줌
		try {
			if(!(vo.getName(ds).isEmpty())) { //값이 들어있다면 인설트 
			da.insert(vo,ds); //insert에서 ds (디자인 객체) 를 넣는 이유가 ?뭐에요?  vo 객체역시나 값을 넣지 않았는데 
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		if (e.getSource() == ds.getJbName()) {
			JOptionPane.showMessageDialog(ds, "준비중입니다 ^^");
		}

		if (e.getSource() == ds.getJbInput()) {
			insert();
		
			
		}

	}

}
