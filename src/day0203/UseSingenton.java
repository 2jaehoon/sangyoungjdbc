package day0203;

import java.sql.SQLException;

import kr.co.sist.dao.conn.DbConnection;

public class UseSingenton {

	public static void main(String[] args) {
		
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		Singleton single3 = Singleton.getInstance();
		
		System.out.println("single : "  + single);
		System.out.println("single2 : " + single2);
		System.out.println("single3 : " + single3);
		
		DbConnection dc = DbConnection.getInstance();
		DbConnection dc2 = DbConnection.getInstance();
		try {
			System.out.println(dc.getConn());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}//main

}//class
