package com.example.body;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.seminar.BaseFilePanel;
import com.example.seminar.FileHashPanel;
import com.example.seminar.Store;

public abstract class BaseCipherPanel extends JPanel{
	protected JTextArea plaintextInput;
	protected JTextArea cipherInput;
	protected JButton encryptBtn;
	protected JButton decryptBtn;
	public BaseFilePanel fileCipherPanel;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BaseCipherPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel cipherBox = new JPanel();
		add(cipherBox);
		cipherBox.setLayout(new BorderLayout(0, 0));
		
		JLabel cipherTextLabel = new JLabel("Cipher");
		cipherBox.add(cipherTextLabel, BorderLayout.NORTH);
		
		cipherInput = new JTextArea();
		JScrollPane cipherScrollpane = new JScrollPane(cipherInput);
		cipherBox.add(cipherScrollpane);
		cipherInput.setRows(4);
		cipherInput.setColumns(45);
		
		JPanel plaintextBox = new JPanel();
		add(plaintextBox);
		plaintextBox.setLayout(new BorderLayout(0, 0));
	
		JLabel plaintextLabel = new JLabel("Plaintext");
		plaintextBox.add(plaintextLabel, BorderLayout.NORTH);
		
		plaintextInput = new JTextArea();
		JScrollPane plainScrollPane = new JScrollPane(plaintextInput);
		plaintextInput.setRows(4);
		plaintextInput.setColumns(45);
		plaintextBox.add(plainScrollPane);
		
		JPanel action = new JPanel();
		add(action);
		
		encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cipher = Store.currentTab.cipherHandler.encryptToBase64(plaintextInput.getText());
				cipherInput.setText(cipher);
			}
		});
		action.add(encryptBtn);
		
		decryptBtn = new JButton("Decrypt");
		decryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plainText = Store.currentTab.cipherHandler.decrypt(cipherInput.getText());
				plaintextInput.setText(plainText);
			}
		});
		action.add(decryptBtn);
		setVisible(false);
	}
	
	public BaseCipherPanel(boolean hash) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		JPanel plainTextPanel = new JPanel();
		plainTextPanel.setLayout(new BorderLayout(0, 0));;
		JLabel plainTextLabel = new JLabel("Plain text");
		JTextArea plainTextArea = new JTextArea();
		JScrollPane plainScrollPane = new JScrollPane(plainTextArea);
		plainTextArea.setColumns(45);
		plainTextArea.setRows(4);
		
		plainTextPanel.add(plainTextLabel, BorderLayout.NORTH);
		plainTextPanel.add(plainScrollPane);
		
		body.add(plainTextPanel);
		
		JPanel cipherTextPanel = new JPanel();
		cipherTextPanel.setLayout(new BorderLayout(0, 0));;
		JLabel cipherTextLabel = new JLabel("Hash");
		JTextArea cipherTextArea = new JTextArea();
		JScrollPane cipherScrollpane = new JScrollPane(cipherTextArea);
		cipherTextArea.setColumns(45);
		cipherTextArea.setRows(4);
		
		cipherTextPanel.add(cipherTextLabel, BorderLayout.NORTH);
		cipherTextPanel.add(cipherScrollpane);
		
		body.add(cipherTextPanel);
		
		JPanel action = new JPanel();
		body.add(action);
		
	 	JButton hashBtn = new JButton("Hash");
		hashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hashText = Store.currentTab.cipherHandler.encryptToBase64(plainTextArea.getText());
				cipherTextArea.setText(hashText);
			}
		});
		action.add(hashBtn);
		add(body);
		fileCipherPanel = new FileHashPanel();
		body.add(fileCipherPanel);
		setVisible(false);
	}
}
