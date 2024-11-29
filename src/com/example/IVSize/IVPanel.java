package com.example.IVSize;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.KeyHandler.ISecretHandler;
import com.example.seminar.Store;

public class IVPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea ivInput;
	/**
	 * Create the panel.
	 */
	public IVPanel() {
		setLayout(new BorderLayout(10, 0));
		JLabel ivLabel = new JLabel("IV");
		ivInput = new JTextArea();
		ivInput.setRows(3);
		ivInput.setColumns(45);
		add(ivLabel, BorderLayout.NORTH);
		add(new JScrollPane(ivInput));
		
		JPanel actionPanel = new JPanel();
		add(actionPanel, BorderLayout.SOUTH);
		
		JButton loadKey = new JButton("Load IV");
		loadKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((ISecretHandler) Store.currentTab.keyHandler).loadIV(ivInput.getText());
			}
		});
		actionPanel.add(loadKey);
		
		JButton genKey = new JButton("Gen IV");
		genKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IvParameterSpec iv = ((ISecretHandler) Store.currentTab.keyHandler).genIV();
				setIV(iv);
			}
		});
		actionPanel.add(genKey);
	}
	
	public void setIV(IvParameterSpec ivParameterSpec) {
		ivInput.setText(Base64.getEncoder().encodeToString(ivParameterSpec.getIV()));
	}
	
	public void clear() {
		ivInput.setText("");
	}

}
