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
import com.example.Tabs.VigenereConfig;
import com.example.seminar.Store;

public class KeyPanelVigenere extends BasePanelKey {
	private JPanel actionPanel;
	private JButton loadKey;
	private JTextArea key;
	private static final long serialVersionUID = 1L;

	public KeyPanelVigenere() {
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
		
		loadKey = new JButton("Load Key");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VigenereConfig.key = key.getText();
			}
		});
		actionPanel.add(loadKey);
	}
	
	public void setKeyValue(String text) {
		key.setText(text);
	};

}
