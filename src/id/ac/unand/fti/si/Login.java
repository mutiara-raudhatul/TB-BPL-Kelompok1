package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpass;

	
//	Connection con = null;
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int gagal = 0;
	String username, password;
  
	
	//untuk login
		 public boolean login(String username_dt, String password_dt)
		    {	     
			 boolean data=false;
		        try
		        {	        	
//		            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/username?serverTimezone=Asia/Jakarta", "root", "");
		    		connection = sqlConnection.dbConnector();
		        	String sql = "SELECT username FROM user WHERE username='"+username_dt+"' AND password='"+password_dt+"'";             
		             pst = connection.prepareStatement(sql);	           
		             rs=pst.executeQuery(sql);
		            
		            if (rs.next())
		            {
		                sql = "UPDATE user SET "
		                    + "login_terakhir = NOW() WHERE username=?";
		                PreparedStatement update = connection.prepareStatement(sql);
		                update.setString(1, username_dt);
		                update.executeUpdate();
		                update.close();
		               
		                JOptionPane.showMessageDialog(null, "Login berhasil");
		                return true;
		            }
		            else
		            {
		                JOptionPane.showMessageDialog(null, "Username dan/atau Password salah!");
		            }
		        }catch (Exception e)
		        {
		            JOptionPane.showMessageDialog(null, e.getMessage());
		        }
		        return data;
		    }
			
		
		// method untuk salah password
		    public boolean salah_password(String username_dt)
		    {
		      
		        boolean data=false;
		        try
		        {
//		        	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/username?serverTimezone=Asia/Jakarta", "root", "");
		    		connection = sqlConnection.dbConnector();

		        	String sql = "SELECT username FROM user WHERE username='"+username_dt+"'";
		            pst = connection.prepareStatement(sql);
		          
		            rs=pst.executeQuery(sql);
		            	if (rs.next())
		            	{
		            		return true;
		            	}
		        }catch (Exception e)
		        	{
		        		JOptionPane.showMessageDialog(null, e.getMessage());
		        	}
		       return data;
		    }
		
		  //method untuk reset password random
		    public void reset_password(String username_dt) {
		      
		        String password_random = random_string(15);
		        
		        // mengubah password menjadi random di database
		     
		        try
		        {        	
//		        	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/username?serverTimezone=Asia/Jakarta", "root", "");
		    		connection = sqlConnection.dbConnector();

		        	String sql = "UPDATE user SET "
		            		 	+ "password = ? WHERE username=?";
		            
		             PreparedStatement update = connection.prepareStatement(sql);
		             update.setString(1, password_random);
		             update.setString(2, username_dt);
		             update.executeUpdate();
		             update.close();
		            
		            JOptionPane.showMessageDialog(null, "Salah memasukkan password sebanyak 3 kali, password direset");
		       }catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e.getMessage());
		        }
		    }
		    
		    // method untuk membuat random string
		    public String random_string(int n) 
		    { 
		        // memilih karakter dari string 
		        String AlphaNumericString = "0123456789"
		                                    + "abcdefghijklmnopqrstuvxyz"; 
		        // membuat string bufferdari  AlphaNumericString 
		        StringBuilder sb = new StringBuilder(n);
		        for (int i = 0; i < n; i++) { 
		            
		            int index 
		                = (int)(AlphaNumericString.length() 
		                        * Math.random()); 
		            
		            sb.append(AlphaNumericString 
		                          .charAt(index)); 
		        } 

		        return sb.toString(); 
		    }
		    private void bersihkan_teks()
		    {
		        txtusername.setText("");
		        txtpass.setText("");
		    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	public Login() {
		setTitle("Login");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(479, 0, 493, 536);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Back,");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(46, 34, 318, 55);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(60, 165, 73, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(60, 258, 73, 34);
		panel.add(lblNewLabel_1_1);
		
		txtusername = new JTextField();
		txtusername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//mengubah isi textfield menjadi huruf kecil
				String s = txtusername.getText();
				String s1= s.toLowerCase();
				txtusername.setText(s1);
				
			}
		});
		this.setVisible(true);		
		txtusername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		txtusername.setForeground(new Color(0, 0, 0));
		txtusername.setBackground(new Color(250, 235, 215));
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtusername.setBounds(60, 209, 286, 39);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		txtpass = new JPasswordField();
		txtpass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String t = txtpass.getText();
				String t1= t.toLowerCase();
				txtpass.setText(t1);
			}
		});
		txtpass.setBackground(new Color(250, 235, 215));
		txtpass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpass.setBounds(60, 295, 286, 39);
		panel.add(txtpass);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.setForeground(new Color(0, 0, 0));
		btnlogin.setBackground(new Color(188, 143, 143));
		btnlogin.addActionListener(new ActionListener() {
			//untuk menjalankan method login
			public void actionPerformed(ActionEvent e) {
	
		        username = txtusername.getText();
		        password = txtpass.getText();
		        if (login(username,password))
		        {
		             Dashboard ds = new Dashboard();
		             ds.setVisible(true);
		             dispose();
		        }
		        else
		        {
		            bersihkan_teks();
		            if(salah_password(username)){
		                gagal = gagal + 1;
		                if(gagal == 3){
		                    reset_password(username);		
		                }
		            }
		        }
			}	
			});
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnlogin.setBounds(60, 368, 286, 55);
		panel.add(btnlogin);
		
		//untuk kembali ke halaman registrasi
		JButton btnRegistrasi = new JButton("Sign Up");
		btnRegistrasi.setForeground(new Color(0, 0, 0));
		btnRegistrasi.setBackground(new Color(255, 255, 255));
		btnRegistrasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Registrasi r = new Registrasi();
			        r.setVisible(true);
			        dispose();
				}
		});
		btnRegistrasi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistrasi.setBounds(202, 460, 88, 25);
		panel.add(btnRegistrasi);
		
		JLabel lblNewLabel_2 = new JLabel("Sign In To Continue");
		lblNewLabel_2.setForeground(new Color(105, 105, 105));
		lblNewLabel_2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(54, 81, 171, 40);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New User ?");
		lblNewLabel_3.setForeground(new Color(105, 105, 105));
		lblNewLabel_3.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(121, 456, 133, 28);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(188, 143, 143));
		panel_1.setBounds(0, 0, 481, 543);
		contentPane.add(panel_1);
	}	
	
	
}


