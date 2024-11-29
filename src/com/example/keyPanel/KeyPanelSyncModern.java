package com.example.keyPanel;

import java.awt.BorderLayout;

import javax.crypto.SecretKey;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.example.IVSize.IVPanel;
import com.example.KeyHandler.ISecretHandler;
import com.example.Tabs.SyncModernConfig;
import com.example.seminar.Agorithm;
import com.example.seminar.Store;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class KeyPanelSyncModern extends BasePanelKey {

	private JTextArea keyInput;
	private JPanel actionPanel;
	private JButton loadKey;
	private JButton genKey;
	private IVPanel ivPanel;

	private static final long serialVersionUID = 1L;
	
	public KeyPanelSyncModern() {
		super();
		ivPanel = new IVPanel();
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
		this.add(ivPanel);
		
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
				Agorithm agorithm = Store.agorithm;
				Store.currentTab.keyHandler.genKey();
				SecretKey secretKey = SyncModernConfig.secrets.get(agorithm);
				
				keyInput.setText(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
			}
		});
		actionPanel.add(genKey);
	}
	
	public void setVisibleIV(boolean status) {
		this.ivPanel.setVisible(status);
	}
	
	public IVPanel getIvPanel() {
		return ivPanel;
	}

}
