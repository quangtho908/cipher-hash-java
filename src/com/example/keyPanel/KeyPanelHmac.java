package com.example.keyPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.example.KeyHandler.ISecretHandler;
import com.example.Tabs.HmacConfig;
import com.example.seminar.Store;

public class KeyPanelHmac extends BasePanelKey{
	private JTextArea keyInput;
	private JPanel actionPanel;
	private JButton loadKey;
	private JButton genKey;

	private static final long serialVersionUID = 1L;
	
	public KeyPanelHmac() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new BorderLayout(10, 0));
		JLabel Key = new JLabel("Key");
		keyInput = new JTextArea();
		keyInput.setRows(3);
		keyInput.setColumns(45);
		keyPanel.add(Key, BorderLayout.NORTH);
		keyPanel.add(new JScrollPane(keyInput));
		this.add(keyPanel);
		
		actionPanel = new JPanel();
		keyPanel.add(actionPanel, BorderLayout.SOUTH);
		
		loadKey = new JButton("Load Key");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((ISecretHandler) Store.currentTab.keyHandler).loadKey(keyInput.getText());
			}
		});
		actionPanel.add(loadKey);
		
		genKey = new JButton("Gen Key");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.keyHandler.genKey();
				keyInput.setText(Base64.getEncoder().encodeToString(HmacConfig.key.getEncoded()));
			}
		});
		actionPanel.add(genKey);
	}

}
