package backend.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs;

	public DBConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/weltensiedler", "root", "");
			stm = con.createStatement();
			getData();
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void getData(){
		try {
			String query = "Update `money` set `CURMNY` = 300 where `CURMNY` <> 300";
			rs = stm.executeQuery(query);
			
				String usrid = rs.getString("USRUUID");
				String usrname = rs.getString("usrname");
				
				System.out.println("Name: " + usrid + " :: " + usrname);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
