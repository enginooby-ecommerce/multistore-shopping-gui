package test;

import java.sql.*;

class MysqlCon {
	public static void main(String args[]) {
		try {  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root", "Minhhieu123");
			//Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			PreparedStatement stmt=con.prepareStatement("select * from item");  
			ResultSet rs = stmt.executeQuery();//"select * from item");
			
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getString(3));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}	