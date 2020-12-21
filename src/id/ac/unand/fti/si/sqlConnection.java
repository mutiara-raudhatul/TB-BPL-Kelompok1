package id.ac.unand.fti.si;

import java.sql.*;

import javax.swing.*;

public class sqlConnection {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db_kasir?serverTimezone=Asia/Jakarta";
	static final String USER = "root";
	static final String PASS = "";
	
	Connection conn=null;
	public static Connection dbConnector() {
		try {
			Class.forName(JDBC_DRIVER);
            Connection conn= DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Successfull");
            return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
}
