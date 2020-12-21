package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class kelola_user extends JFrame {
	
	
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtemail;
	private JTable table;
	private JPasswordField txtpassword;
	private JTextField searchU;
	private JTextField searchE;
	
	
	//menampilkan data dari database ke tabel
	public void showTableData(){
		try{
			connection = sqlConnection.dbConnector();
			String sql = "SELECT * FROM user";
			pst = connection.prepareStatement(sql);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
	   }catch(Exception ex){
		   
	 }
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kelola_user frame = new kelola_user();
					frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public kelola_user() {
		setTitle("Kelola User");
		
		showTableData();	//memanggil method show table
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 575);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Home");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Dashboard");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard ds = new Dashboard();
				ds.setVisible(true);
	            dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu menu_user = new JMenu("User");
		menuBar.add(menu_user);
		
		JMenuItem menuitem_kelolauser = new JMenuItem("Kelola User");
		menuitem_kelolauser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kelola_user m = new kelola_user();
	            m.setVisible(true);
	            dispose();
			}
		});
		menu_user.add(menuitem_kelolauser);
		
		JMenu menu_barang = new JMenu("Barang");
		menuBar.add(menu_barang);
		
		JMenuItem menuitem_kelolabarang = new JMenuItem("Kelola Barang");
		menuitem_kelolabarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Databarang dbarang = new Databarang();
				dbarang.setVisible(true);
	            dispose();
			}
		});
		menu_barang.add(menuitem_kelolabarang);
		
		JMenuItem menuitem_restok = new JMenuItem("Restok Barang");
		menuitem_restok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Restok r = new Restok();
				r.setVisible(true);
	            dispose();
			}
		});
		menu_barang.add(menuitem_restok);
		
		JMenu menu_transaksi = new JMenu("Transaksi");
		menuBar.add(menu_transaksi);
		
		JMenuItem menuitem_transaksi = new JMenuItem("Kelola Transaksi");
		menuitem_transaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transaksi tr = new transaksi();
				tr.setVisible(true);
	            dispose();
			}
		});
		menu_transaksi.add(menuitem_transaksi);
		
		JMenu menu_laporan = new JMenu("Laporan");
		menuBar.add(menu_laporan);
		
		JMenuItem menuitem_lapjual = new JMenuItem("Laporan Penjualan");
		menuitem_lapjual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaporanPenjualan lapjual = new LaporanPenjualan();
				lapjual.setVisible(true);
	            dispose();
			}
		});
		menu_laporan.add(menuitem_lapjual);
		
		JMenuItem menuitem_lapuntung = new JMenuItem("Laporan Keuntungan");
		menuitem_lapuntung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaporanKeuntungan lapjual = new LaporanKeuntungan();
				lapjual.setVisible(true);
	            dispose();
			}
		});
		menu_laporan.add(menuitem_lapuntung);
		
		JMenu mnNewMenu = new JMenu("Logout");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Logout");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//melalukan logout atau pindah ke form login
				  Login l = new Login();
			       l.setVisible(true);
			       dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(188, 143, 143));
		panel.setBounds(0, -17, 965, 531);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtusername.setBounds(132, 130, 207, 28);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtemail.setColumns(10);
		txtemail.setBounds(132, 181, 207, 28);
		panel.add(txtemail);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(41, 130, 156, 28);
		panel.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(41, 181, 156, 28);
		panel.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(41, 232, 156, 28);
		panel.add(lblPassword);
		
		//button insert
		JButton btninsert = new JButton("Insert");
		btninsert.setBackground(new Color(253, 245, 230));
		btninsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String username = txtusername.getText();
				String password = txtpassword.getText();
				String email    = txtemail.getText();
				
				//menambah data ke database
				try {
					  if(txtusername.getText().equals("")||
						txtpassword.getText().equals("")||
						txtemail.getText().equals("")){
					   JOptionPane.showMessageDialog(btninsert,"Data Tidak Boleh Kosong");
						 
					  } else {
						  	
								if (username.length()<9 ) {
									JOptionPane.showMessageDialog(null, "Username harus lebih dari 8 karakter");
					  			
								} else if (password.length()< 9) {
									JOptionPane.showMessageDialog(null, "Password harus lebih dari 8 karakter" );
				  			
								} else if (email.length()<15) {
									JOptionPane.showMessageDialog(null, "Masukkan Email dengan benar!" +"\n"+"Email harus lebih dari 14 karakter" );					  											
								} else {
						  
									String sql = "INSERT INTO user "
											+"(username, email, password)"
											+" VALUES (?,?,?)";
									connection = sqlConnection.dbConnector();
									pst = connection.prepareStatement(sql);
									pst.setString(1,txtusername.getText());
									pst.setString(2,txtemail.getText());
									pst.setString(3,txtpassword.getText());
									pst.executeUpdate();
									JOptionPane.showMessageDialog(null, "inserted successfully");
									showTableData();
								}
							}
					  
			}catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null,"Username telah ada atau"+"\n"+"panjang data melebihi batas");
					txtusername.setText("");
				}
				
				
			}
		});
		btninsert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btninsert.setBounds(400, 180, 95, 28);
		panel.add(btninsert);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setBackground(new Color(253, 245, 230));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//menghapus data sesuai username yang dimasukkan
				

					try{
						  if(txtusername.getText().equals("")){
								
								JOptionPane.showMessageDialog(btninsert,"Masukkan data yang akan dihapus pada kotak username");
							 
						  }else {
			       
							  String sql = "DELETE FROM user WHERE username=?";				
							  connection = sqlConnection.dbConnector();
							  pst = connection.prepareStatement(sql);
							  pst.setString(1,txtusername.getText());
							  pst.executeUpdate();
							  
							  showTableData();
						  		}
					}catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex);
					}
			}
		});
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btndelete.setBounds(400, 129, 95, 28);
		panel.add(btndelete);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setBackground(new Color(253, 245, 230));
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mengedit data berdasarkan username
				
				//
				
				String password = txtpassword.getText();
				String email    = txtemail.getText();
				try{
					if(txtusername.getText().equals("")||
							txtpassword.getText().equals("")||
							txtemail.getText().equals("")){
						   JOptionPane.showMessageDialog(btninsert,"Data Tidak Boleh Kosong \n Data diedit berdasarkan Username");
							 
				  }else {
					  		if(password.length ()< 9) {
					  			JOptionPane.showMessageDialog(null, "Password harus lebih dari 8 karakter" );
							
					  		}else if(email.length()<15) {
					  			JOptionPane.showMessageDialog(null, "Masukkan Email dengan benar!" +"\n"+"Email harus lebih dari 14 karakter" );					  											
					  		}else {
					  
					  			String sql = "UPDATE user SET email=? ,password=? WHERE username=?";
					  			connection = sqlConnection.dbConnector();
					  			pst = connection.prepareStatement(sql);
					  			pst.setString(3, txtusername.getText());
					  			pst.setString(1,txtemail.getText());
					  			pst.setString(2,txtpassword.getText());
					  			pst.executeUpdate();
					 
					  			showTableData();
					  			}
					  }
			}catch(SQLException | HeadlessException ex){
					 JOptionPane.showMessageDialog(null, ex);
					 }
				
			}
		});
		btnupdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnupdate.setBounds(400, 232, 95, 28);
		panel.add(btnupdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		scrollPane.setBounds(30, 301, 763, 165);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//menampilkan data pada textfield ketika data diklik di tabel
				try {
					int row = table.getSelectedRow();
					
					String click = (table.getModel().getValueAt(row, 0).toString());
					String sql = "SELECT username, email, password FROM  user WHERE username='"+click+"'";	
					pst = connection.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs.next()) {
						String add1 = rs.getString("username");
						txtusername.setText(add1);
						String add2 = rs.getString("email");
						txtemail.setText(add2);
						String add3 = rs.getString("password");
						txtpassword.setText(add3);					
					}
			
			} catch(SQLException | HeadlessException ex){
			 JOptionPane.showMessageDialog(null, ex);
			 }
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnshow = new JButton("Show Data");
		btnshow.setBackground(new Color(253, 245, 230));
		btnshow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//button untuk menampilkan data pada tabel dengan memanggil method
				showTableData();
			}
		});
		btnshow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnshow.setBounds(803, 301, 107, 28);
		panel.add(btnshow);
		
		txtpassword = new JPasswordField();
		txtpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String u = txtpassword.getText();
				String u1= u.toLowerCase();
				txtpassword.setText(u1);
			}
		});
		txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpassword.setBounds(132, 232, 207, 28);
		panel.add(txtpassword);
		
		searchU = new JTextField();
		searchU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//mencari data dengan mengketik data username di textfield 
				try {
				
					
					String sql = "SELECT * FROM user where username=?";
					PreparedStatement pst  = connection.prepareStatement(sql);
					pst.setString(1,searchU.getText() );				
					rs=pst.executeQuery();
					
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(SQLException | HeadlessException ex){
					 JOptionPane.showMessageDialog(null, ex);
				 }
					
				}
			
		});
		searchU.setColumns(10);
		searchU.setBounds(703, 171, 207, 28);
		panel.add(searchU);
		
		JLabel lblNewLabel_1 = new JLabel("KELOLA USER");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(346, 31, 399, 70);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pencarian");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(703, 112, 183, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ketik username yang akan dicari");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(703, 147, 183, 25);
		panel.add(lblNewLabel_3);
		
		searchE = new JTextField();
		searchE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				//mencari data dengan mengketik data email di textfield 
				try {
				//	String filter = (String)comboBox.getSelectedItem();
					
					String sql = "SELECT * FROM user where email=?";
					PreparedStatement pst  = connection.prepareStatement(sql);
					pst.setString(1,searchE.getText() );						
					rs=pst.executeQuery();
									
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(SQLException | HeadlessException ex){
					 JOptionPane.showMessageDialog(null, ex);
				 }
				
			}
		});
		searchE.setColumns(10);
		searchE.setBounds(703, 233, 207, 28);
		panel.add(searchE);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ketik email yang akan dicari");
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setBackground(Color.WHITE);
		lblNewLabel_3_1.setBounds(703, 209, 183, 25);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_3 = new JLabel("*hapus data dengan memasukkan username");
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setBackground(Color.WHITE);
		lblNewLabel_3_3.setBounds(41, 477, 243, 25);
		panel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("*data diedit berdasarkan username yang dimasukkan");
		lblNewLabel_3_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_3_1.setBackground(Color.WHITE);
		lblNewLabel_3_3_1.setBounds(41, 492, 307, 25);
		panel.add(lblNewLabel_3_3_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(30, 91, 889, 199);
		panel.add(panel_1);
		panel_1.setBackground(SystemColor.menu);
		
		JLabel lblNewLabel_2_1 = new JLabel("Masukkan Data");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_2_1.setBounds(45, 91, 183, 31);
		panel.add(lblNewLabel_2_1);
	}
}

