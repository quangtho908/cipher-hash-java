package com.example.keyPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.KeyHandler.HillKeyHandler;
import com.example.Tabs.HillConfig;
import com.example.Tabs.VigenereConfig;
import com.example.seminar.Store;

public class KeyPanelHill extends BasePanelKey{
	private JPanel actionPanel;
	private JButton genKey;
	private JTextArea key;
	private static final long serialVersionUID = 1L;
	
	public KeyPanelHill() {
		super();
		
		setLayout(new BorderLayout(0, 0));
		key = new JTextArea();
		key.setColumns(45);
		key.setRows(4);
		key.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(key.getText().isEmpty()) {
					VigenereConfig.key = "";
					return;
				};
				if (!key.getText().matches("[a-zA-Z]+")) {
	                Store.notice.setVisible(true);
	                Store.notice.setContent("Key must a - z or A - Z");
	                key.setText("");
	                return;
	            }
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(new JScrollPane(key));
		
		JLabel label = new JLabel("Key");
		
		add(label, BorderLayout.NORTH);
		setVisible(false);
		actionPanel = new JPanel();
		add(actionPanel, BorderLayout.SOUTH);
		
		genKey = new JButton("Gen Key");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((HillKeyHandler) Store.currentTab.keyHandler).genKey();
				StringBuilder keyString = new StringBuilder();
				for(int i = 0; i < 2; i++) {
					keyString.append("[\s");
					for(int j = 0; j < 2; j++) {
						keyString.append(HillConfig.key[i][j]);
						keyString.append("\s");
					}
					keyString.append("]\n");
				}
				
				key.setText(keyString.toString());
				
			}
		});
		actionPanel.add(genKey);
	}

}
