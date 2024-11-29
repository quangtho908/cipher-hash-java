package com.example.keyPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.example.Tabs.CaesarConfig;
import com.example.seminar.Store;

public class KeyPanelCaesar extends BasePanelKey {
	private JPanel actionPanel;
	private JButton loadKey;
	private JButton genKey;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public KeyPanelCaesar() {
		setLayout(new BorderLayout(0, 0));
		JFormattedTextField keySize = new JFormattedTextField(NumberFormat.getNumberInstance());
		keySize.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				int value = Integer.parseInt(keySize.getText());
				if((value < 1 || value > 25)) {
					keySize.setText(String.valueOf(4));	
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		keySize.setValue(4);
		keySize.setColumns(5);
		add(keySize);
		
		JLabel label = new JLabel("Key");
		
		add(label, BorderLayout.NORTH);
		setVisible(false);
		actionPanel = new JPanel();
		add(actionPanel, BorderLayout.SOUTH);
		
		loadKey = new JButton("Load Key");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaesarConfig.key = Integer.parseInt(keySize.getText());
			}
		});
		actionPanel.add(loadKey);
		
		genKey = new JButton("Gen Key");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.keyHandler.genKey();
				keySize.setText(String.valueOf(CaesarConfig.key));
			}
		});
		actionPanel.add(genKey);
	}

}
