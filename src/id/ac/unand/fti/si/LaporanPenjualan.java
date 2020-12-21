package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.*;
import java.awt.Font;


public class LaporanPenjualan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxBulan;
	private JComboBox comboBoxHari;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaporanPenjualan frame = new LaporanPenjualan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField textFieldTotalPenjualan;
	private JTextField textField_maxPenjualan;
	private JTextField textField_minPenjualan;
	private JTextField textField_rata2Penjualan;

	
	public void fillComboBoxBulan() {
		
		try {
			String query ="select MONTHNAME(tanggal) as bulan from transaksi group by bulan";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				comboBoxBulan.addItem(rs.getString("bulan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void fillComboBoxHari() {	
		try {
			String query ="select tanggal from transaksi group by tanggal";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				comboBoxHari.addItem(rs.getString("tanggal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public float getSumTotalPenjualan() {
		int sum=0;
		for (int i=0; i< table.getRowCount(); i++) {
			sum= sum + Integer.parseInt(table.getValueAt(i, 6).toString());
		}
		textFieldTotalPenjualan.setText(Integer.toString(sum));
		return sum;
	}
	
	public float getavgPenjualan () {
		float sum= getSumTotalPenjualan();
		int rowsCount = table.getRowCount();
		float average = sum/rowsCount;
		textField_rata2Penjualan.setText(Float.toString(average));
		return average;
	}
	
	public void getMaxMin() {
		ArrayList<Integer> list = new ArrayList <Integer>();
		for (int i=0; i<table.getRowCount(); i++) {
			list.add(Integer.parseInt(table.getValueAt(i, 6).toString()));
		}
		Integer max=Collections.max(list);
		Integer min=Collections.min(list);
		
		textField_maxPenjualan.setText(Integer.toString(max));
		textField_minPenjualan.setText(Integer.toString(min));
	}


	/**
	 * Create the frame.
	 */
	public LaporanPenjualan() {
		setTitle("Laporan Penjualan");
		connection = sqlConnection.dbConnector();
		
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 909, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 143, 758, 169);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//SELURUH LAPORAN PENJUALAN		
		JButton btn_ViewAll = new JButton("View All");
		btn_ViewAll.setBounds(518, 83, 124, 35);
		panel_1.add(btn_ViewAll);
		btn_ViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query ="SELECT transaksi.tanggal, "
								+ "transaksi_detail.sku, "
								+ "barang.nama as nama_barang, "
								+ "COUNT(transaksi_detail.sku) as banyak_transaksi, "
								+ "SUM(transaksi_detail.jumlah) as terjual, "
								+ "barang.stok-transaksi_detail.jumlah as sisa_stok, "
								+ "SUM(transaksi_detail.harga) as total_penjualan "
								+ "FROM transaksi_detail "
								+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
								+ "INNER JOIN transaksi ON transaksi_detail.noresi=transaksi.noresi "
								+ "GROUP BY transaksi_detail.id ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel (DbUtils.resultSetToTableModel(rs));
					getSumTotalPenjualan();
					getavgPenjualan();
					getMaxMin();
					
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}			}
		});
		btn_ViewAll.setBackground(SystemColor.text);
		btn_ViewAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		//LAPORAN PENJUALAN
		//BERDASARKAN BULAN	
				comboBoxBulan = new JComboBox();
				comboBoxBulan.setBounds(142, 83, 171, 35);
				panel_1.add(comboBoxBulan);
				comboBoxBulan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							String query ="SELECT transaksi.tanggal, "
										+ "transaksi_detail.sku, "
										+ "barang.nama as nama_barang, "
										+ "COUNT(transaksi_detail.sku) as banyak_transaksi, "
										+ "SUM(transaksi_detail.jumlah) as terjual, "
										+ "barang.stok-transaksi_detail.jumlah as sisa_stok, "
										+ "SUM(transaksi_detail.harga) as total_penjualan "
										+ "FROM transaksi_detail "
										+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
										+ "INNER JOIN transaksi ON transaksi_detail.noresi=transaksi.noresi "
										+ "WHERE MONTHNAME(tanggal)=? "
										+ "GROUP BY transaksi_detail.sku";
							
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, (String)comboBoxBulan.getSelectedItem());
							ResultSet rs=pst.executeQuery();

							table.setModel (DbUtils.resultSetToTableModel(rs));
							getSumTotalPenjualan();					
							getavgPenjualan();
							getMaxMin();

							pst.close();
							rs.close();
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				comboBoxBulan.setName("");
				comboBoxBulan.setToolTipText("");
				comboBoxBulan.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxBulan.setBackground(SystemColor.text);
				
		//LAPORAN PENJUALAN
		//BERDASARKAN HARI		
				comboBoxHari = new JComboBox();
				comboBoxHari.setBounds(337, 83, 171, 35);
				panel_1.add(comboBoxHari);
				comboBoxHari.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String query ="SELECT transaksi.tanggal, "
										+ "transaksi_detail.sku, "
										+ "barang.nama as nama_barang, "
										+ "COUNT(transaksi_detail.sku) as banyak_transaksi, "
										+ "SUM(transaksi_detail.jumlah) as terjual, "
										+ "barang.stok-transaksi_detail.jumlah as sisa_stok, "
										+ "SUM(transaksi_detail.harga) as total_penjualan "
										+ "FROM transaksi_detail "
										+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
										+ "INNER JOIN transaksi ON transaksi_detail.noresi=transaksi.noresi "
										+ "WHERE tanggal=? "
										+ "GROUP BY transaksi_detail.sku";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, (String)comboBoxHari.getSelectedItem());
							ResultSet rs=pst.executeQuery();

							table.setModel (DbUtils.resultSetToTableModel(rs));
							getSumTotalPenjualan();
							getavgPenjualan();
							getMaxMin();

							pst.close();
							rs.close();
									
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				comboBoxHari.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxHari.setBackground(SystemColor.text);
			
				
		//PRINT LAPORAN PENJUALAN
				JButton btnNewButton_Print = new JButton("Print");
				btnNewButton_Print.setBounds(652, 83, 124, 35);
				panel_1.add(btnNewButton_Print);
				btnNewButton_Print.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							table.print();
						}catch (java.awt.print.PrinterException e1) {
							System.err.format("No Printer Found", e1.getMessage());
						}
					}
				});
				btnNewButton_Print.setBackground(SystemColor.text);
				btnNewButton_Print.setFont(new Font("Tahoma", Font.PLAIN, 14));
						
				JLabel lblNewLabel_2 = new JLabel("LAPORAN PENJUALAN");
				lblNewLabel_2.setBounds(348, 11, 206, 27);
				panel_1.add(lblNewLabel_2);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
						
	//TOTAL PENJUALAN		
		textFieldTotalPenjualan = new JTextField();
		textFieldTotalPenjualan.setBounds(711, 343, 124, 27);
		panel_1.add(textFieldTotalPenjualan);
		textFieldTotalPenjualan.setEditable(false);
		textFieldTotalPenjualan.setColumns(10);
		
		JLabel lblNewLabel_TotalPenjualan = new JLabel("Total Penjualan Rp.");
		lblNewLabel_TotalPenjualan.setBounds(568, 341, 142, 27);
		panel_1.add(lblNewLabel_TotalPenjualan);
		lblNewLabel_TotalPenjualan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
	//MAX PENJUALAN
		textField_maxPenjualan = new JTextField();
		textField_maxPenjualan.setEditable(false);
		textField_maxPenjualan.setColumns(10);
		textField_maxPenjualan.setBounds(224, 343, 124, 27);
		panel_1.add(textField_maxPenjualan);
		
		JLabel lbl_maxPenjualaan = new JLabel("Penjualan Max Rp.");
		lbl_maxPenjualaan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_maxPenjualaan.setBounds(97, 341, 125, 27);
		panel_1.add(lbl_maxPenjualaan);
		
	//MIN PENJUALAN
		textField_minPenjualan = new JTextField();
		textField_minPenjualan.setEditable(false);
		textField_minPenjualan.setColumns(10);
		textField_minPenjualan.setBounds(224, 381, 124, 27);
		panel_1.add(textField_minPenjualan);
		
		JLabel lbl_minPenjualaan = new JLabel("Penjualan Min Rp.");
		lbl_minPenjualaan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_minPenjualaan.setBounds(97, 379, 125, 27);
		panel_1.add(lbl_minPenjualaan);
		
	//RATA-RATA PENJUALAN
		JLabel lbl_rata2Penjualan = new JLabel("Rata-Rata Penjualan  Rp.");
		lbl_rata2Penjualan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_rata2Penjualan.setBounds(531, 383, 171, 27);
		panel_1.add(lbl_rata2Penjualan);
		
		textField_rata2Penjualan = new JTextField();
		textField_rata2Penjualan.setEditable(false);
		textField_rata2Penjualan.setColumns(10);
		textField_rata2Penjualan.setBounds(711, 383, 124, 27);
		panel_1.add(textField_rata2Penjualan);
											
		fillComboBoxBulan();
		fillComboBoxHari();
		
	}
}
