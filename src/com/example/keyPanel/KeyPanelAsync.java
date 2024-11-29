package com.example.keyPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.example.seminar.Store;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.example.KeyHandler.IKeyPairHandler;
import com.example.Tabs.RSAConfig;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class KeyPanelAsync extends BasePanelKey {

	private static final long serialVersionUID = 1L;


	/**
	 * Create the panel.
	 */
	public KeyPanelAsync() {
		super();
		setVisible(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel privateKeyPanel = new JPanel();
		add(privateKeyPanel);
		privateKeyPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel privateKey = new JLabel("Private Key");
		privateKeyPanel.add(privateKey, BorderLayout.NORTH);
		
		JTextArea privateKeyInput = new JTextArea();
		JScrollPane privScrollPane = new JScrollPane(privateKeyInput);
		privateKeyInput.setRows(5);
		privateKeyInput.setColumns(40);
		privateKeyInput.setWrapStyleWord(true);
		privateKeyInput.setLineWrap(true);
		privateKeyPanel.add(privScrollPane, BorderLayout.CENTER);
		
		JPanel publicKeyPanel = new JPanel();
		add(publicKeyPanel);
		publicKeyPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel publicKey = new JLabel("Public Key");
		publicKeyPanel.add(publicKey, BorderLayout.NORTH);
		
		JTextArea publicKeyInput = new JTextArea();
		JScrollPane pubScrollPane = new JScrollPane(publicKeyInput);
		publicKeyInput.setRows(5);
		publicKeyInput.setColumns(45);
		publicKeyInput.setWrapStyleWord(true);
		publicKeyInput.setLineWrap(true);
		publicKeyPanel.add(pubScrollPane, BorderLayout.CENTER);
		
		
		JPanel actionPanel = new JPanel();
		add(actionPanel);
		
		JButton loadKey = new JButton("Load Key");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((IKeyPairHandler) Store.currentTab.keyHandler).loadKey(publicKeyInput.getText(), privateKeyInput.getText());
			}
		});
		actionPanel.add(loadKey);
		
		JButton genKey = new JButton("Gen Key");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.keyHandler.genKey();
				KeyPair keyPair = RSAConfig.keyPair;
				publicKeyInput.setText(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
				privateKeyInput.setText(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
			}
		});
		actionPanel.add(genKey);

	}

}
