package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Databarang extends JFrame {
	
	private JPanel contentPane;
	private JTextField Sku;
	private JTextField Nama;
	private JTextField Stok;
	private JTextField Hargabeli;
	private JTextField Hargajual;
	
	private String sku, nama;
	private Integer stok,hbeli,hjual;
	private Statement st;
	private ResultSet rs;
	private String sql="";
	private PreparedStatement ps;
	Connection connection = null;

	
	public Databarang() {
		setTitle("Kelola Barang");
		connection = sqlConnection.dbConnector();
		initComponents();	
	}
	
	private void ShowData() {	
		try {
			
			sql = "select * from barang";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			tabeldetail.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR \n Gagal Memuat keDatabase \n Aktifkan Database Sebelum Memulai \n"+e.getMessage());
		}
	}
	
	private void Clear() {
		Sku.setText("");
		Nama.setText("");
		Stok.setText("");
		Hargabeli.setText("");
		Hargajual.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Databarang frame = new Databarang();
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
	
	private void initComponents() {
		
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
		contentPane.setRequestFocusEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pengelolaan Data Master Barang");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.PLAIN, 25));
		lblNewLabel.setBounds(267, 11, 406, 46);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 68, 874, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sku");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 11, 38, 33);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nama");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 54, 76, 33);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Stok");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 97, 38, 33);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Harga Beli");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_3.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(435, 11, 93, 33);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Harga Jual");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_4.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(435, 55, 93, 33);
		panel.add(lblNewLabel_1_4);
		
		Sku = new JTextField();
		Sku.setFont(new Font("Cambria", Font.PLAIN, 14));
		Sku.setText("");
		Sku.setBounds(119, 11, 229, 33);
		panel.add(Sku);
		Sku.setColumns(10);
		
		Nama = new JTextField();
		Nama.setText("");
		Nama.setFont(new Font("Cambria", Font.PLAIN, 14));
		Nama.setColumns(10);
		Nama.setBounds(119, 54, 229, 33);
		panel.add(Nama);
		
		Stok = new JTextField();
		Stok.setText("");
		Stok.setFont(new Font("Cambria", Font.PLAIN, 14));
		Stok.setColumns(10);
		Stok.setBounds(119, 97, 229, 33);
		panel.add(Stok);
		
		Hargabeli = new JTextField();
		Hargabeli.setText("");
		Hargabeli.setFont(new Font("Cambria", Font.PLAIN, 14));
		Hargabeli.setColumns(10);
		Hargabeli.setBounds(544, 11, 229, 33);
		panel.add(Hargabeli);
		
		Hargajual = new JTextField();
		Hargajual.setText("");
		Hargajual.setFont(new Font("Cambria", Font.PLAIN, 14));
		Hargajual.setColumns(10);
		Hargajual.setBounds(544, 55, 229, 33);
		panel.add(Hargajual);
		
		btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sku=String.valueOf(Sku.getText());
				nama=String.valueOf(Nama.getText());
				stok=Integer.parseInt(Stok.getText());
				hbeli=Integer.parseInt(Hargabeli.getText());
				hjual=Integer.parseInt(Hargajual.getText());
				
				try {
					sql="INSERT INTO barang (sku, nama, stok, harga_beli, harga_jual) values"
						+"('"+sku+"', '"+nama+"', '"+stok+"', '"+hbeli+"', '"+hjual+"')";
					st = connection.createStatement();
					st.execute(sql);
					Clear();
					ShowData();
					JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
				} catch (Exception et) {
					JOptionPane.showMessageDialog(null, "ERRO \n Data Gagal Ditambah \n"+et.getMessage());
				}
			}
		});
		btnTambah.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnTambah.setBounds(543, 97, 105, 33);
		panel.add(btnTambah);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sku=String.valueOf(Sku.getText());
				nama=String.valueOf(Nama.getText());
				stok=Integer.parseInt(Stok.getText());
				hbeli=Integer.parseInt(Hargabeli.getText());
				hjual=Integer.parseInt(Hargajual.getText());
				
				try {
					sql = "UPDATE barang SET nama = '"+nama+"', stok = '"+stok+"', harga_beli = '"+hbeli+"', harga_jual = '"+hjual+"' WHERE sku ='"+sku+"'";
					st = connection.createStatement();
					st.execute(sql);
					Clear();
//					ShowData();
					JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
				} catch (Exception eu) {
					JOptionPane.showMessageDialog(null, "ERROR \n "+eu.getMessage());
				}
			}
		});
		btnUpdate.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnUpdate.setBounds(668, 97, 105, 33);
		panel.add(btnUpdate);
		
		JLabel lblNewLabel_2 = new JLabel("Pencarian");
		lblNewLabel_2.setFont(new Font("Cambria", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(34, 224, 90, 30);
		contentPane.add(lblNewLabel_2);
		
		Cari = new JTextField();
		Cari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				sku=String.valueOf(Sku.getText());
				
				try {
					
					sql = "SELECT * FROM barang WHERE sku=?";
					ps = connection.prepareStatement(sql);
					ps.setString(1,Cari.getText());
					rs = ps.executeQuery();
					
					tabeldetail.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception es) {
					JOptionPane.showMessageDialog(null, "ERROR \n Data Tidak Ada / Tidak Ditemukan \n "+es.getMessage());
				}
			}
		});
		Cari.setFont(new Font("Cambria", Font.PLAIN, 20));
		Cari.setBounds(143, 224, 229, 30);
		contentPane.add(Cari);
		Cari.setColumns(10);
		
		Hapus = new JTextField();
		Hapus.setFont(new Font("Cambria", Font.PLAIN, 20));
		Hapus.setBounds(34, 462, 143, 30);
		contentPane.add(Hapus);
		Hapus.setColumns(10);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sku = String.valueOf(Hapus.getText());
				try {
					sql = "DELETE FROM barang WHERE sku = '"+sku+"'";
					st = connection.createStatement();
					st.execute(sql);
					Clear();
					ShowData();
					JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
				} catch (Exception eh) {
					JOptionPane.showMessageDialog(null, "ERROR \n"+eh.getMessage());
				}
			}
		});
		btnHapus.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnHapus.setBounds(200, 462, 143, 30);
		contentPane.add(btnHapus);
		
		JButton btnLihatDetail = new JButton("Lihat Detail");
		btnLihatDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowData();
			}
		});
		btnLihatDetail.setFont(new Font("Cambria", Font.PLAIN, 14));
		btnLihatDetail.setBounds(738, 223, 143, 33);
		contentPane.add(btnLihatDetail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(34, 265, 864, 186);
		contentPane.add(scrollPane);
		
		tabeldetail = new JTable();
		tabeldetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					Integer row = tabeldetail.getSelectedRow();
					String click = (tabeldetail.getModel().getValueAt(row, 0).toString());
					sql = "SELECT sku, nama, stok, harga_beli, harga_jual FROM barang WHERE sku='"+click+"'";
					ps = connection.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						String add1 = rs.getString("sku");
						Sku.setText(add1);
						String add2 = rs.getString("nama");
						Nama.setText(add2);
						String add3 = rs.getString("stok");
						Stok.setText(add3);
						String add4 = rs.getString("harga_beli");
						Hargabeli.setText(add4);
						String add5 = rs.getString("harga_jual");
						Hargajual.setText(add5);
						
					}
					
				} catch (Exception et) {
					JOptionPane.showMessageDialog(null, et);
				}
			}
		});
		tabeldetail.setCellSelectionEnabled(true);
		tabeldetail.setAutoscrolls(false);
		tabeldetail.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Sku", "Nama", "Stok", "Harga Beli", "Harga Jual"
			}
		));
		tabeldetail.setFont(new Font("Cambria", Font.PLAIN, 20));
		scrollPane.setViewportView(tabeldetail);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(10, 11, 909, 492);
		contentPane.add(panel_1);

	}
	private JButton btnTambah;
	private JTextField Cari;
	private JTextField Hapus;
	private JTable tabeldetail;
}
