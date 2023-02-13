package work0203;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CarService {
	
	public void searchCarInMaker() {
		CarDAO cDAO = new CarDAO();
		try {
			String maker = JOptionPane.showInputDialog("제조사 입력");
			List<String> list = cDAO.selectCars(maker);
			
			StringBuilder outData  = new StringBuilder();
			
			for(String carInf : list) {
				outData.append(carInf).append(" ");
			}
			
			JTextArea jta = new JTextArea(5,60);
			jta.setText(outData.toString());
			
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

	public static void main(String[] args) {
		CarService cs = new CarService();
		cs.searchCarInMaker();

	}

}
