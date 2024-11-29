package com.example.keyPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.KeyHandler.AffineKeyHandler;
import com.example.Tabs.AffineConfig;
import com.example.seminar.Store;

public class KeyPanelAffine extends BasePanelKey{

	private static final long serialVersionUID = 1L;
	
	public KeyPanelAffine() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JPanel keyPanel = new JPanel();
		
		JFormattedTextField aNumber = new JFormattedTextField(NumberFormat.getNumberInstance());
		aNumber.setValue(0);
		aNumber.setColumns(4);
		keyPanel.add(new JLabel("a : "));
		keyPanel.add(aNumber);
		
		JFormattedTextField bNumber = new JFormattedTextField(NumberFormat.getNumberInstance());
		bNumber.setValue(0);
		bNumber.setColumns(4);
		keyPanel.add(new JLabel("b : "));
		keyPanel.add(bNumber);
		
		add(keyPanel);
		
		JPanel actionPanel = new JPanel();
		add(actionPanel, BorderLayout.SOUTH);
		
		JButton loadKey = new JButton("Load Key");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((AffineKeyHandler) Store.currentTab.keyHandler).loadKey(
					 Integer.parseInt(aNumber.getText()),
					 Integer.parseInt(bNumber.getText())
				);
			}
		});
		actionPanel.add(loadKey);
		
		JButton genKey = new JButton("Gen Key");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.currentTab.keyHandler.genKey();
				
				aNumber.setText(String.valueOf(AffineConfig.key[0]));
				bNumber.setText(String.valueOf(AffineConfig.key[1]));
			}
		});
		actionPanel.add(genKey);
	}

}
