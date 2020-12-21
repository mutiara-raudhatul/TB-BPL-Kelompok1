package id.ac.unand.fti.si;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.print.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;


public class LaporanKeuntungan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxBulanan;
	private JComboBox<String> comboBoxHarian;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaporanKeuntungan frame = new LaporanKeuntungan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JPanel panel_1;
	private JTextField textFieldTotalKeuntungan;
	private JTextField textField_maxKeuntungan;
	private JTextField textField_minKeuntungan;
	private JTextField textField_rata2Keuntungan;

	public void fillComboBoxBulanan() {
		
		try {
			String query ="select MONTHNAME(tanggal) as bulan from transaksi group by bulan";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				comboBoxBulanan.addItem(rs.getString("bulan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fillComboBoxHarian() {
		
		try {
			String query ="select tanggal from transaksi group by tanggal";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				comboBoxHarian.addItem(rs.getString("tanggal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public float getSumTotalKeuntungan() {
		int sum=0;
		for (int i=0; i< table.getRowCount(); i++) {
			sum= sum + Integer.parseInt(table.getValueAt(i, 6).toString());
		}
		textFieldTotalKeuntungan.setText(Integer.toString(sum));
		return sum;
	}
	
	public float getavgKeuntungan () {
		float sum= getSumTotalKeuntungan();
		int rowsCount = table.getRowCount();
		float average = sum/rowsCount;
		textField_rata2Keuntungan.setText(Float.toString(average));
		return average;
	}
	
	public void getMaxMin() {
		HashSet<Integer>hs = new HashSet<Integer>();
		for (int i=0; i<table.getRowCount(); i++) {
			hs.add(Integer.parseInt(table.getValueAt(i, 6).toString()));
		}
		Integer max=Collections.max(hs);
		Integer min=Collections.min(hs);
		
		textField_maxKeuntungan.setText(Integer.toString(max));
		textField_minKeuntungan.setText(Integer.toString(min));
	}
	
	public void initcomponents () {
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
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	/**
	 * Create the frame.
	 */
	public LaporanKeuntungan() {
		setTitle("Laporan Keuntungan");
		connection = sqlConnection.dbConnector();
		initcomponents();
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 909, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LAPORAN KEUNTUNGAN");
		lblNewLabel.setBounds(368, 11, 182, 20);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	//MENAMPILKAN SELURUH DATA LAPORAN KEUNTUNGAN
		JButton btnNewButtonVA = new JButton("View All");
		btnNewButtonVA.setBounds(486, 58, 111, 30);
		panel_1.add(btnNewButtonVA);
		btnNewButtonVA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query ="select transaksi.tanggal, "
								+ "transaksi.noresi, "
								+ "transaksi_detail.sku, "
								+ "transaksi_detail.jumlah, "
								+ "barang.harga_beli*transaksi_detail.jumlah as total_pembelian, "
								+ "transaksi_detail.harga as total_penjualan, "
								+ "transaksi_detail.harga - barang.harga_beli*transaksi_detail.jumlah as keuntungan "
								+ "from transaksi "
								+ "INNER JOIN transaksi_detail ON transaksi.noresi=transaksi_detail.noresi "
								+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
								+ "GROUP BY transaksi_detail.id "
								+ "ORDER BY tanggal ASC";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalKeuntungan();
					getavgKeuntungan();
					getMaxMin();
					pst.close();
					rs.close();
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButtonVA.setBackground(SystemColor.text);

	//LAPORAN KEUNTUNGAN
	//BERDASARKAN BULAN
		comboBoxBulanan = new JComboBox();
		comboBoxBulanan.setBounds(145, 59, 158, 29);
		panel_1.add(comboBoxBulanan);
		comboBoxBulanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query ="select transaksi.tanggal, "
								+ "transaksi.noresi, "
								+ "transaksi_detail.sku, "
								+ "transaksi_detail.jumlah, "
								+ "barang.harga_beli*transaksi_detail.jumlah as total_pembelian, "
								+ "transaksi_detail.harga as total_penjualan, "
								+ "transaksi_detail.harga - barang.harga_beli*transaksi_detail.jumlah as keuntungan "
								+ "from transaksi "
								+ "INNER JOIN transaksi_detail ON transaksi.noresi=transaksi_detail.noresi "
								+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
								+ "WHERE MONTHNAME(tanggal)=? "
								+ "ORDER BY tanggal ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)comboBoxBulanan.getSelectedItem());
					ResultSet rs=pst.executeQuery();

					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalKeuntungan();
					getavgKeuntungan();
					getMaxMin();
					
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBoxBulanan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxBulanan.setBackground(SystemColor.text);
		
	//LAPORAN KEUNTUNGAN
	//BERDASARKAN HARI	
		comboBoxHarian = new JComboBox();
		comboBoxHarian.setBounds(326, 59, 141, 29);
		panel_1.add(comboBoxHarian);
		comboBoxHarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query ="SELECT transaksi.tanggal, "
								+ "transaksi.noresi, "
								+ "transaksi_detail.sku, "
								+ "transaksi_detail.jumlah, "
								+ "barang.harga_beli*transaksi_detail.jumlah as total_pembelian, "
								+ "transaksi_detail.harga as total_penjualan, "
								+ "transaksi_detail.harga - barang.harga_beli*transaksi_detail.jumlah as keuntungan "
								+ "FROM transaksi "
								+ "INNER JOIN transaksi_detail ON transaksi.noresi=transaksi_detail.noresi "
								+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
								+ "WHERE tanggal=? "
								+ "ORDER BY transaksi_detail.sku";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)comboBoxHarian.getSelectedItem());
					ResultSet rs=pst.executeQuery();

					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalKeuntungan();
					getavgKeuntungan();
					getMaxMin();
					
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		comboBoxHarian.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxHarian.setBackground(SystemColor.text);
		
	//PRINT LAPORAN KEUNTUNGAN
		JButton btnNewButtonPrint = new JButton("Print");
		btnNewButtonPrint.setBounds(607, 58, 119, 30);
		panel_1.add(btnNewButtonPrint);
		btnNewButtonPrint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButtonPrint.setBackground(SystemColor.text);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 117, 733, 224);
		panel_1.add(scrollPane);
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 240));
		scrollPane.setForeground(Color.BLACK);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	
	//TOTAL KEUNTUNGAN
		JLabel lblNewLabel_TotalKeuntungan = new JLabel("Total Keuntungan Rp.");
		lblNewLabel_TotalKeuntungan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_TotalKeuntungan.setBounds(524, 382, 147, 20);
		panel_1.add(lblNewLabel_TotalKeuntungan);
		
		textFieldTotalKeuntungan = new JTextField();
		textFieldTotalKeuntungan.setBounds(681, 379, 127, 23);
		panel_1.add(textFieldTotalKeuntungan);
		textFieldTotalKeuntungan.setEditable(false);
		textFieldTotalKeuntungan.setColumns(10);
		
	//MAX KEUNTUNGAN
		JLabel lbl_maxKeuntungan = new JLabel("Keuntungan Max Rp.");
		lbl_maxKeuntungan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_maxKeuntungan.setBounds(75, 378, 147, 27);
		panel_1.add(lbl_maxKeuntungan);
		
		textField_maxKeuntungan = new JTextField();
		textField_maxKeuntungan.setEditable(false);
		textField_maxKeuntungan.setColumns(10);
		textField_maxKeuntungan.setBounds(226, 378, 124, 27);
		panel_1.add(textField_maxKeuntungan);
	
	//MIN KEUNTUNGAN
		JLabel lbl_minKeuntungan = new JLabel("Keuntungan Min Rp.");
		lbl_minKeuntungan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_minKeuntungan.setBounds(75, 416, 125, 27);
		panel_1.add(lbl_minKeuntungan);
		
		textField_minKeuntungan = new JTextField();
		textField_minKeuntungan.setEditable(false);
		textField_minKeuntungan.setColumns(10);
		textField_minKeuntungan.setBounds(226, 416, 124, 27);
		panel_1.add(textField_minKeuntungan);
	
	//RATA-RATA KEUNTUNGAN
		JLabel lbl_rata2Keuntungan = new JLabel("Rata-Rata Keuntungan Rp.");
		lbl_rata2Keuntungan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_rata2Keuntungan.setBounds(504, 416, 167, 27);
		panel_1.add(lbl_rata2Keuntungan);
		
		textField_rata2Keuntungan = new JTextField();
		textField_rata2Keuntungan.setEditable(false);
		textField_rata2Keuntungan.setColumns(10);
		textField_rata2Keuntungan.setBounds(684, 413, 124, 27);
		panel_1.add(textField_rata2Keuntungan);
		btnNewButtonPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
					textFieldTotalKeuntungan.print();
					textField_maxKeuntungan.print();
					textField_minKeuntungan.print();
					textField_rata2Keuntungan.print();
				}catch (java.awt.print.PrinterException e1) {
					System.err.format("No Printer Found", e1.getMessage());
				}
			}
		});
		

		fillComboBoxBulanan();
		fillComboBoxHarian();
		
	}
}
