package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.SystemColor;
import java.awt.Font;

public class transaksi extends JFrame {
	
	private Statement stm;
	private ResultSet result;
	private String sql="";
	private String sql2="";
	
	
	private String kode_barang;
	private String tran;
	private Integer id_trans;
	
	Connection connection = null;

	private String trans, kb, nb, km, nm, tgl;
    private Integer s, j, h, t;
	
	public transaksi() {
		setTitle("Kelola Transaksi");
		connection = sqlConnection.dbConnector();
		initComponents();
//		koneksi();
		update();
		waktu();
	}
	
	public void initComponents() {
		
		tgl_transaksi = new com.toedter.calendar.JDateChooser();
		jumlah = new javax.swing.JTextField();
		stok = new javax.swing.JTextField();
		
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
		
		JLabel label = new JLabel("New label");
		label.setBounds(26, 214, 23, -134);
		contentPane.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 909, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JDateChooser tgl_transaksi_1 = new JDateChooser();
		tgl_transaksi_1.setBounds(162, 135, 254, 26);
		panel_1.add(tgl_transaksi_1);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(320, 95, 96, 29);
		panel_1.add(btnGenerate);
		
		id = new JTextField();
		id.setBounds(162, 96, 104, 26);
		panel_1.add(id);
		id.setColumns(10);
		
		id.setEnabled(false);
		
		JLabel lblNewLabel_2 = new JLabel("Tgl Transaksi");
		lblNewLabel_2.setBounds(62, 141, 96, 20);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_10 = new JLabel("ID Transaksi");
		lblNewLabel_10.setBounds(62, 96, 90, 23);
		panel_1.add(lblNewLabel_10);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("SKU");
		lblNewLabel_3.setBounds(62, 216, 49, 23);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		sku = new JTextField();
		sku.setBounds(162, 210, 254, 26);
		panel_1.add(sku);
		sku.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("Nama Barang");
		lblNewLabel_4.setBounds(62, 253, 106, 23);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nama_barang = new JTextField();
		nama_barang.setBounds(162, 247, 254, 26);
		panel_1.add(nama_barang);
		nama_barang.setColumns(10);
		
		nama_barang.setEnabled(false);
		
		JLabel lblNewLabel_5 = new JLabel("Harga");
		lblNewLabel_5.setBounds(62, 290, 67, 21);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		harga = new JTextField();
		harga.setBounds(162, 284, 254, 24);
		panel_1.add(harga);
		harga.setColumns(10);
		
		
		harga.setEnabled(false);
		
		JLabel lblNewLabel_7 = new JLabel("Stok");
		lblNewLabel_7.setBounds(62, 328, 67, 20);
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		stok_1 = new JTextField();
		stok_1.setBounds(162, 319, 254, 26);
		panel_1.add(stok_1);
		stok_1.setColumns(10);
		
		stok_1.setEnabled(false);
		
		JLabel lblNewLabel_6 = new JLabel("Jumlah");
		lblNewLabel_6.setBounds(62, 359, 67, 23);
		panel_1.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jumlah_1 = new JTextField();
		jumlah_1.setBounds(162, 356, 254, 26);
		panel_1.add(jumlah_1);
		jumlah_1.setText("");
		jumlah_1.setColumns(10);
		
		kode_member = new JTextField();
		kode_member.setBounds(641, 132, 182, 26);
		panel_1.add(kode_member);
		kode_member.setColumns(10);
		
		
		nama_member = new JTextField();
		nama_member.setBounds(641, 173, 182, 26);
		panel_1.add(nama_member);
		nama_member.setColumns(10);
		
		nama_member.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("TRANSAKSI PENJUALAN");
		lblNewLabel.setBounds(348, 28, 229, 30);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		no_resi = new JTextField();
		no_resi.setBounds(162, 175, 254, 24);
		panel_1.add(no_resi);
		no_resi.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("No. Resi");
		lblNewLabel_1.setBounds(62, 182, 67, 20);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_8 = new JLabel("Kode Member");
		lblNewLabel_8.setBounds(473, 132, 127, 23);
		panel_1.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_9 = new JLabel("Nama ");
		lblNewLabel_9.setBounds(473, 173, 127, 26);
		panel_1.add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_12 = new JLabel("Total");
		lblNewLabel_12.setBounds(473, 290, 49, 14);
		panel_1.add(lblNewLabel_12);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		total = new JTextField();
		total.setBounds(473, 315, 193, 31);
		panel_1.add(total);
		total.setColumns(10);
		
		total.setEnabled(false);
		
		JButton button_add = new JButton("ADD");
		button_add.setBounds(473, 357, 89, 23);
		panel_1.add(button_add);
		
		JButton button_clear = new JButton("CLEAR");
		button_clear.setBounds(577, 357, 89, 23);
		panel_1.add(button_clear);
		
		button_clear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearActionPerformed(evt);
			}
		});
		
		button_add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});
		
		kode_member.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				memberFocusLost(evt);
			}
		});
		
		kode_member.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				kode_memberKeyReleased(evt);
			}
		});
		
		jumlah_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				jumlahKeyReleased(evt);
			}
		});
		
		sku.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				skuFocusLost(evt);
			}
		});
		
		sku.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				skuKeyReleased(evt);
			}
		});
		
		btnGenerate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGenerateActionPerformed(evt);
			}
		});
		
		tgl_transaksi_1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				tgl_tranPropertyChange(evt);
			}
		});
	}
	
	private void clear() {
		id.setText("");
		no_resi.setText("");
		sku.setText("");
		nama_barang.setText("");
		harga.setText("");
		stok_1.setText("");
		jumlah_1.setText("");
		kode_member.setText("");
		nama_member.setText("");
		total.setText("");
	}
	
	private void update() {
		try {
			String x=jumlah_1.getText();
            String y=stok_1.getText();
            Integer stk=Integer.parseInt(y);
            Integer jmlh=Integer.parseInt(x);
			
			if (jmlh<=stk) {
			Integer hasil= stk-jmlh;
			sql="UPDATE barang SET stok='"+ hasil +"' WHERE sku = '"+ kb +"'";
			stm=connection.createStatement();
            stm.execute(sql);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void cekStok() {
		try {
            String a=stok_1.getText();
            int mStok=Integer.parseInt(a);
            if(mStok <= 0){
               JOptionPane.showMessageDialog(null, "STOK BARANG HABIS \n Silahkan Cek Persediaan Barang Terlebih Dahulu");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR"+e.getMessage());
        }
	}
	
	public void waktu() {
		Date tgl = new Date ();
		tgl_transaksi.setDate(tgl);
	}

	
	private void skuFocusLost(java.awt.event.FocusEvent evt) {
		try {
			connection = sqlConnection.dbConnector();

			stm=connection.createStatement();
			result=stm.executeQuery("SELECT*FROM barang WHERE sku='"+sku.getText()+"'");
			
			if (result.next()) {
				nama_barang.setText(result.getString("nama"));
				harga.setText(result.getString("harga_jual"));
				stok_1.setText(result.getString("stok"));
				jumlah_1.setEnabled(true);
			}
			else {
				nama_barang.setText(null);
				harga.setText(null);
				stok_1.setText(null);
				jumlah_1.setEnabled(false);

			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Ditemukan "+e.getMessage());
		}
	}
	
	private void skuKeyReleased(java.awt.event.KeyEvent evt) {
		tran=no_resi.getText();
		Integer tekanenter=evt.getKeyCode();
		if (tekanenter==10) {
			try {
				connection = sqlConnection.dbConnector();

				stm=connection.createStatement();
				result=stm.executeQuery("SELECT*FROM barang WHERE sku='"+sku.getText()+"'");
				
				if (result.next()) {
					nama_barang.setText(result.getString("nama"));
					stok_1.setText(result.getString("stok"));
					harga.setText(result.getString("harga_jual"));
					jumlah_1.setEnabled(true);
					
					String w=stok_1.getText();
					Integer sk=Integer.parseInt(w);
					if (sk<=0) {
						JOptionPane.showMessageDialog(null, "STOK BARANG HABIS \n Silahkan Cek Persediaan Barang Terlebih Dahulu");
					}
				}
				else {
					nama_barang.setText(null);
	                stok_1.setText(null);
	                harga.setText(null);
	                jumlah_1.setEnabled(false);
	                JOptionPane.showMessageDialog(null, "Gagal Memuat Data \nData Tidak Di Temukan");
				}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Di Temukan"+e.getMessage());
			}
		}
	}
	
	private void tgl_tranPropertyChange(java.beans.PropertyChangeEvent evt) {
		if (tgl_transaksi.getDate()!=null) {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			tgl=format.format(tgl_transaksi.getDate());
		}
	}
	
	private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
		clear();
	}
	
	private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {
		try {
		connection = sqlConnection.dbConnector();

		stm=connection.createStatement();
		result=stm.executeQuery("SELECT * FROM transaksi_detail ORDER BY id DESC LIMIT 1");
		
		if (result.next()) {
			String a=result.getString("id");
			Integer nomor=Integer.parseInt(a);
			Integer no=nomor+1;
			String nn=String.valueOf(no);
			id.setText(nn);
		}
		else {
			id.setText(null);
		}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void jumlahKeyReleased(java.awt.event.KeyEvent evt) {
		String price=harga.getText();
		String qty=jumlah_1.getText();
		Double nPrice=Double.parseDouble(price);
		Double nQty=Double.parseDouble(qty);
		DecimalFormat df=new DecimalFormat("#.##");
		total.setText(df.format(nPrice*nQty));
	}
	
	private void memberFocusLost(java.awt.event.FocusEvent evt) {
		try {
			connection = sqlConnection.dbConnector();
			stm=connection.createStatement();
			result=stm.executeQuery("SELECT*FROM member WHERE kode_member='"+kode_member.getText()+"'");
			
			if (result.next()) {
				nama_member.setText(result.getString("nama_member"));
			}
			else {
				kode_member.setText(null);
				nama_member.setText(null);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Memuat Data \n Data Tidak Di Temukan"+e.getMessage());
		}
	}
	
	private void kode_memberKeyReleased(java.awt.event.KeyEvent evt) {
		tran=no_resi.getText();
		Integer tekanenter=evt.getKeyCode();
		if (tekanenter==10) {
			try {
				connection = sqlConnection.dbConnector();
                stm=connection.createStatement();
                result=stm.executeQuery("SELECT*FROM member WHERE kode_member='"+kode_member.getText()+"'");
                
                if (result.next()) {
                	nama_member.setText(result.getString("nama_member"));
                }
                else {
                	kode_member.setText(null);
                	nama_member.setText(null);
                	JOptionPane.showMessageDialog(null, "Gagal Memuat Data \nData Tidak Di Temukan");
                }
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR \n Data Tidak Ada / Tidak Ditemukan" +e.getMessage());
			}
		}
	}
	
	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
		trans=String.valueOf(no_resi.getText());
		kb=String.valueOf(sku.getText());
		nb=String.valueOf(nama_barang.getText());
		s=Integer.valueOf(stok_1.getText());
		j=Integer.valueOf(jumlah_1.getText());
		h=Integer.valueOf(harga.getText());
		t=Integer.parseInt(total.getText());
		km=String.valueOf(kode_member.getText());
		id_trans=Integer.valueOf(id.getText());
        
        try {
        	String aa=jumlah_1.getText();
        	String bb=stok_1.getText();
        	Integer stk=Integer.parseInt(bb);
        	Integer jmlh=Integer.parseInt(aa);
        	
        	if (jmlh<=stk&&jmlh!=0) {
        		sql="INSERT INTO transaksi_detail (id, sku,noresi, jumlah, harga)" 
        			+ "value ('"+id_trans+"', '"+kb+"','"+trans+"', '"+j+"','"+h+"')";
        		sql2="INSERT INTO transaksi (noresi, tanggal, username)"
        				+"value ('"+trans+"', '"+tgl+"', '"+km+"')";
        		stm=connection.createStatement();
        		stm.execute(sql2);
        		stm.execute(sql);
        		update();
        		cekStok();
        		JOptionPane.showMessageDialog(null, "Transaksi Berhasil");
        		clear();
        	}
        	else if (jmlh==0) {
        		JOptionPane.showMessageDialog(null, "Jumlah tidak boleh 0");
        	}
        	else {
            	JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
        	}
        }
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "ERROR ADD \n"+e.getMessage());
        }
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transaksi frame = new transaksi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private JTextField no_resi;
	private JTextField sku;
	private JTextField nama_barang;
	private JTextField harga;
	private JTextField stok;
	private JTextField stok_1;
	private JTextField jumlah;
	private JTextField jumlah_1;
	private JTextField kode_member;
	private JTextField nama_member;
	private JTextField total;
	private com.toedter.calendar.JDateChooser tgl_transaksi;
	private JTextField id;
}
