package id.ac.unand.fti.si;

import java.sql.*;

public class Program {
	// Menyiapkan paramter JDBC untuk koneksi ke database
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db_kasir?serverTimezone=Asia/Jakarta";
	static final String USER = "root";
	static final String PASS = "";
	
	// Menyiapkan objek yang diperlukan untuk mengelola database
	static Connection conn;
	
	public static void main(String[] args) throws Exception {		

		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
     	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Terjadi Kesalahan : Driver tidak ditemukan");
        }
    
		System.out.print("Program");
	}

}
