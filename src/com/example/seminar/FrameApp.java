package com.example.seminar;

import java.awt.EventQueue;
import java.security.Security;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.example.Tabs.AffineConfig;
import com.example.Tabs.CaesarConfig;
import com.example.Tabs.HashConfig;
import com.example.Tabs.HillConfig;
import com.example.Tabs.HmacConfig;
import com.example.Tabs.RSAConfig;
import com.example.Tabs.SyncModernConfig;
import com.example.Tabs.TabConfig;
import com.example.Tabs.Tabs;
import com.example.Tabs.VigenereConfig;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new SyncModernConfig();
		new RSAConfig();
		new CaesarConfig();
		new VigenereConfig();
		new AffineConfig();
		new HillConfig();
		new HashConfig();
		new HmacConfig();
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameApp frame = new FrameApp();
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
	public FrameApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton syncModern = new JButton("Sync Modern");
		syncModern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.SyncModern);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(syncModern);
		
		JButton RSATab = new JButton("RSA");
		RSATab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.RSA);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(RSATab);
		
		JButton caesarTab = new JButton("Caesar");
		caesarTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.Caesar);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(caesarTab);
		
		JButton affineTab = new JButton("Affine");
		affineTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.Affine);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(affineTab);
		
		JButton vigenereTab = new JButton("Vigenere");
		vigenereTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.Vigenere);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(vigenereTab);
		
		JButton hillTab = new JButton("Hill");
		hillTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.Hill);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(hillTab);
		
		JButton hashTab = new JButton("Hash");
		hashTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.HASH);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(hashTab);
		
		JButton hmacTab = new JButton("Hmac");
		hmacTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.downConfig();
				Store.currentTab = TabConfig.configs.get(Tabs.HMAC);
				Store.currentTab.setupConfig();
			}
		});
		toolBar.add(hmacTab);
		

		JPanel footer = new JPanel();
		contentPane.add(footer, BorderLayout.SOUTH);

		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);

		body.setLayout(new BorderLayout(0, 0));

		JPanel cipherHeader = new JPanel();
		body.add(cipherHeader, BorderLayout.NORTH);

		JPanel cipherBody = new JPanel();
		body.add(cipherBody, BorderLayout.CENTER);
		_load(cipherHeader, cipherBody, footer);
		Store.currentTab = TabConfig.configs.get(Tabs.SyncModern);
		Store.currentTab.setupConfig();

	}

	private void _load(JPanel header, JPanel body, JPanel footer) {
		for (Map.Entry<Tabs, TabConfig> tabConfig : TabConfig.configs.entrySet()) {
			body.add(tabConfig.getValue().header);
			body.add(tabConfig.getValue().body);
			footer.add(tabConfig.getValue().footer);
		}
	}

}
