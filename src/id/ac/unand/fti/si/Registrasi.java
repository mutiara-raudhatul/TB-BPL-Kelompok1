package id.ac.unand.fti.si;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Registrasi extends JFrame {


	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtketikpassword;
	private JTextField txtemail;
	private JPasswordField txtpassword;
	private final JPanel panel_1 = new JPanel();
//	Connection con = null;
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	
	//untuk hapus layar pada kotak txt field
		private void hapuslayar() {
			txtusername.setText("");
			txtpassword.setText("");
			txtketikpassword.setText("");
			txtemail.setText("");
		
		}
		
		// method untuk registrasi
	    public void registrasi(String username_dt, String email_dt, String password_dt)
	    {      
	        try{
//	        	connection=DriverManager.getConnection("jdbc:mysql://localhost/db_kasir?serverTimezone=Asia/Jakarta", "root", "");
	    		connection = sqlConnection.dbConnector();

	            String sql = "INSERT INTO `username`.`user` (`username`,"
	                    + "`email`,"
	                    + "`password`) VALUES "
	                    + "('"+username_dt+"',"
	                    + "'"+email_dt+"',"
	                    + "'"+password_dt+"');";
	            
	            pst = connection.prepareStatement(sql);     
	            pst.executeUpdate(sql);
	            pst.close();
	          
	            JOptionPane.showMessageDialog(null, "Registrasi Berhasil");
	            
	        }catch(SQLException e){
	            
	        	JOptionPane.showMessageDialog(null, e.getMessage());
	        }
	    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Registrasi().setVisible(true);
				
			}
		});
	}


	//membuat frame
	public Registrasi() {
		setTitle("Registrasi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 180, 945, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 128, 0));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(451, 0, 478, 675);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setBounds(47, 29, 329, 50);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(52, 127, 118, 18);
		panel.add(lblNewLabel_1);
		
		txtusername = new JTextField();
		txtusername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String s = txtusername.getText();
				String s1= s.toLowerCase();
				txtusername.setText(s1);
			}
		});
		txtusername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtusername.setBounds(52, 155, 252, 29);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(56, 291, 118, 18);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password Again");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(56, 375, 118, 18);
		panel.add(lblNewLabel_1_2);
		
		txtketikpassword = new JTextField();
		txtketikpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String p = txtketikpassword.getText();
				String p1= p.toLowerCase();
				txtketikpassword.setText(p1);
			}
		});
		txtketikpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtketikpassword.setColumns(10);
		txtketikpassword.setBounds(56, 403, 248, 29);
		panel.add(txtketikpassword);
		
		JLabel labelnama = new JLabel("Email");
		labelnama.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelnama.setBounds(52, 213, 118, 18);
		panel.add(labelnama);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtemail.setColumns(10);
		txtemail.setBounds(52, 241, 252, 29);
		panel.add(txtemail);
		
		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpassword.setBounds(52, 320, 252, 29);
		panel.add(txtpassword);
		
		//untuk button registrasi
		JButton btnregistrasi = new JButton("Create");
		btnregistrasi.setBackground(new Color(188, 143, 143));
		btnregistrasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtusername.getText();
				String password = txtpassword.getText();
				String email    = txtemail.getText();
				String ketik	= txtketikpassword.getText();
				
				 			  
				  try {//mengecek apakah data kosong
					  if(txtusername.getText().equals("")||
						txtpassword.getText().equals("")||
						txtemail.getText().equals("")||
						txtketikpassword.getText().equals("")){
						  
						  JOptionPane.showMessageDialog(btnregistrasi,"Data Tidak Boleh Kosong");
						  hapuslayar();
						  
					  }else {	
						  
						  	if(username.length()<9 ) {
						  			JOptionPane.showMessageDialog(null, "Username harus lebih dari 8 karakter");
						  			
						  	}else if(password.length ()< 9) {
					  			JOptionPane.showMessageDialog(null, "Password harus lebih dari 8 karakter" );
					  			
						  	}else if(email.length()<15) {
					  			JOptionPane.showMessageDialog(null, "Masukkan Email dengan benar!" +"\n"+"Email harus lebih dari 14 karakter" );	
					  			
						  	}else if(!ketik.equals(password)) {
					  			JOptionPane.showMessageDialog(null, "Password tidak sama" );
						  
						  	}else {	//melakukan registrasi jika kondisi tidak sesuai 
						  		registrasi(txtusername.getText(), txtemail.getText(), txtpassword.getText());}
						 }
					
				  }catch (Exception error) {
						JOptionPane.showMessageDialog(btnregistrasi, "Akun Sudah Ada");}
			}
		});
		btnregistrasi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnregistrasi.setBounds(56, 469, 223, 38);
		panel.add(btnregistrasi);
		
		JLabel lblNewLabel_2 = new JLabel("Complete Your Details");
		lblNewLabel_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_2.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(51, 71, 160, 32);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("*username huruf kecil");
		lblNewLabel_2_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_2_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(52, 175, 160, 32);
		panel.add(lblNewLabel_2_1);
		panel_1.setBackground(new Color(188, 143, 143));
		panel_1.setBounds(0, 0, 455, 675);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setBackground(new Color(245, 245, 220));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(123, 258, 149, 45);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//kembali ke halaman login
				Login l = new Login();
		        l.setVisible(true);
		        dispose();
			}
		});
		

	}
		
	
}

