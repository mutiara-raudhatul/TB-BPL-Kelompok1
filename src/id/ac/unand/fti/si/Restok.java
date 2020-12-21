package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.sql.*;
import java.util.Date;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Restok extends JFrame {

	private Connection connection = null;
	private Statement stm;
	private ResultSet result;
	private String sql="";
	private String kodebarang;
	private String kb, nb ;
    private Integer s, j;
	
	
	/**
	 * Launch the application.
	 */
	public void initComponents() {
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
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 909, 492);
		contentPane.add(panel_1);
		
		ReStock = new JTextField();
		ReStock.setBounds(364, 310, 168, 32);
		panel_1.add(ReStock);
		ReStock.setColumns(10);
		
		Stock = new JTextField();
		Stock.setBounds(364, 241, 168, 32);
		panel_1.add(Stock);
		Stock.setColumns(10);
		
		Stock.setEnabled(false);
		
		Nama_barang = new JTextField();
		Nama_barang.setBounds(364, 178, 168, 32);
		panel_1.add(Nama_barang);
		Nama_barang.setColumns(10);
		
		Nama_barang.setEnabled(false);
		
		SKU = new JTextField();
		SKU.setBounds(364, 118, 168, 32);
		panel_1.add(SKU);
		SKU.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SKU");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(229, 126, 45, 13);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Barang");
		lblNewLabel_1.setBounds(229, 177, 89, 32);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Stok");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(229, 249, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ReStock");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(229, 318, 76, 13);
		panel_1.add(lblNewLabel_3);
		
		JButton cls = new JButton("Clear");
		cls.setBounds(551, 384, 157, 32);
		panel_1.add(cls);
		cls.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton rstk = new JButton("ReStock");
		rstk.setBounds(364, 384, 168, 32);
		panel_1.add(rstk);
		rstk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4 = new JLabel("RESTOK BARANG");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(377, 11, 174, 32);
		panel_1.add(lblNewLabel_4);
		
		rstk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});
		
		cls.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearActionPerformed(evt);
			}
		});
		
		SKU.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				skuFocusLost(evt);
			}
		});
		
		SKU.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				skuKeyReleased(evt);
			}
		});
	}
	
	public void clear() {
		SKU.setText("");
		Nama_barang.setText("");
		Stock.setText("");
		ReStock.setText("");
	}
	
	public void update() {
		try {
			String x=Stock.getText();
            String y=ReStock.getText();
            kb=String.valueOf(SKU.getText());
            Integer stk=Integer.parseInt(x);
            Integer rstk=Integer.parseInt(y);
			
            Integer hasil= stk+rstk;
			sql="UPDATE barang SET stok='"+ hasil +"' WHERE sku = '"+ kb +"'";
			stm=connection.createStatement();
            stm.execute(sql);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Gagal Mengupdate Data \n Data Tidak Di Temukan "+e.getMessage());
		}
	}
	
	private void skuFocusLost(java.awt.event.FocusEvent evt) {
		try {
			connection = sqlConnection.dbConnector();
			stm=connection.createStatement();
			result=stm.executeQuery("SELECT*FROM barang WHERE sku='"+SKU.getText()+"'");
			
			if (result.next()) {
				Nama_barang.setText(result.getString("nama"));
				Stock.setText(result.getString("stok"));
			}
			else {
				Nama_barang.setText(null);
				Stock.setText(null);
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Di Temukan "+e.getMessage());
		}
	}
	
	private void skuKeyReleased(java.awt.event.KeyEvent evt) {
		kodebarang=SKU.getText();
		Integer tekanenter=evt.getKeyCode();
		if (tekanenter==10) {
			try {
				connection = sqlConnection.dbConnector();
				stm=connection.createStatement();
				result=stm.executeQuery("SELECT*FROM barang WHERE sku='"+SKU.getText()+"'");
				
				if (result.next()) {
					Nama_barang.setText(result.getString("nama"));
					Stock.setText(result.getString("stok"));
					
				}
				else {
					Nama_barang.setText(null);
	                Stock.setText(null);
				}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Di Temukan"+e.getMessage());
			}
		}
	}

	private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
		clear();
	}
	
	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        		update();
        		JOptionPane.showMessageDialog(null, "ReStock Berhasil");
        		clear();
        }
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "ERROR ADD \n"+e.getMessage());
        }
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Restok frame = new Restok();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Restok () {
		setTitle("Restok Barang");
		initComponents();
	}
	private JPanel contentPane;
	private JTextField SKU;
	private JTextField Nama_barang;
	private JTextField Stock;
	private JTextField ReStock;
}
