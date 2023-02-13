package work0203;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.conn.DbConnection;

public class CarDAO {

	public List<String> selectCars(String maker) throws SQLException{
		List<String> carList = new ArrayList<String>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		try {
			con = dbCon.getConn();
			
			stmt = con.createStatement();
			
			StringBuilder selectCar = new StringBuilder();
			selectCar
			.append("	select cc.country, cc.maker, cma.model, cmo.car_year, cmo.price, cmo.car_option 			") 	
			.append("	from car_country cc, car_maker cma, car_model cmo		") 	
			.append("	where (cc.maker=cma.maker and cma.model=cmo.model(+)) and (cc.maker = '").append(maker).append("')");
			
			

			
			rs = stmt.executeQuery(selectCar.toString());
			
			while(rs.next()){
				carList.add(rs.getString("country"));
				carList.add(rs.getString("maker"));
				carList.add(rs.getString("model"));
				carList.add(rs.getString("car_year"));
				carList.add(rs.getString("price"));
				carList.add(rs.getString("car_option"));
				carList.add("\n");
			}
			
			
		}finally {
			dbCon.dbClose(null, stmt, con);
		}
		
		
		return carList;
	}

}
