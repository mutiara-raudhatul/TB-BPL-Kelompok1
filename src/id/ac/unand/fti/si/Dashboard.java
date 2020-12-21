package id.ac.unand.fti.si;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_menu = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 507);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel_menu.setBackground(SystemColor.control);
		panel_menu.setBounds(7, 11, 854, 446);
		contentPane.add(panel_menu);
		panel_menu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(188, 143, 143));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("D:\\Mutiara\\Semester 3\\Eclipse BPL\\Aplikasi Penjualan\\img\\shops.png"));
		lblNewLabel.setBounds(329, 86, 190, 80);
		panel_menu.add(lblNewLabel);
		
		JLabel label_welcome = new JLabel("WELCOME");
		label_welcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_welcome.setBounds(377, 21, 95, 33);
		panel_menu.add(label_welcome);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(10, 177, 834, 258);
		panel_menu.add(panel);
		panel.setLayout(null);
		
		JPanel panel_user_1 = new JPanel();
		panel_user_1.setBounds(416, 5, 1, 1);
		panel_user_1.setLayout(null);
		panel.add(panel_user_1);
		
		JLabel label_user_1 = new JLabel("USER");
		label_user_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_user_1.setBounds(81, 23, 73, 33);
		panel_user_1.add(label_user_1);
		
		JLabel label_userr_1 = new JLabel("");
		label_userr_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_userr_1.setBounds(21, 11, 52, 57);
		panel_user_1.add(label_userr_1);
		
		JButton btnNewButton = new JButton("USER");
		btnNewButton.setIcon(new ImageIcon("D:\\Mutiara\\Semester 3\\Eclipse BPL\\Aplikasi Penjualan\\img\\user.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kelola_user m = new kelola_user();
	            m.setVisible(true);
	            dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(26, 29, 162, 78);
		panel.add(btnNewButton);
		
		JButton btnBarang = new JButton("BARANG");
		btnBarang.setIcon(new ImageIcon("D:\\Mutiara\\Semester 3\\Eclipse BPL\\Aplikasi Penjualan\\img\\box.png"));
		btnBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Databarang dbarang = new Databarang();
				dbarang.setVisible(true);
	            dispose();
			}
		});
		btnBarang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBarang.setBounds(223, 29, 162, 78);
		panel.add(btnBarang);
		
		JButton btnTransaksi = new JButton("TRANSAKSI");
		btnTransaksi.setIcon(new ImageIcon("D:\\Mutiara\\Semester 3\\Eclipse BPL\\Aplikasi Penjualan\\img\\transaction.png"));
		btnTransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transaksi tr = new transaksi();
				tr.setVisible(true);
	            dispose();
			}
		});
		btnTransaksi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTransaksi.setBounds(426, 29, 175, 78);
		panel.add(btnTransaksi);
		
		JButton btnBarang_1 = new JButton("LAPORAN");
		btnBarang_1.setIcon(new ImageIcon("D:\\Mutiara\\Semester 3\\Eclipse BPL\\Aplikasi Penjualan\\img\\report.png"));
		btnBarang_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaporanPenjualan lapjual = new LaporanPenjualan();
				lapjual.setVisible(true);
	            dispose();
			}
		});
		btnBarang_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBarang_1.setBounds(640, 29, 162, 78);
		panel.add(btnBarang_1);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setIcon(new ImageIcon("D:\\Mutiara\\Semester 3\\Eclipse BPL\\Aplikasi Penjualan\\img\\logout.png"));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
			    l.setVisible(true);
			    dispose();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(310, 119, 190, 78);
		panel.add(btnLogout);
		
		JLabel label_welcome_1 = new JLabel("Aplikasi Kasir Penjualan");
		label_welcome_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_welcome_1.setBounds(338, 57, 239, 33);
		panel_menu.add(label_welcome_1);
	}
}
