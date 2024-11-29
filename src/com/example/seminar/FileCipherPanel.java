package com.example.seminar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.cipher.ICipherFile;

public class FileCipherPanel extends BaseFilePanel {
	
	private JTextField outputFile;
	private JPanel modePanel;
	private JPanel action;
	private JPanel bodyPanel;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public FileCipherPanel() {
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		add(new JLabel("Encrypt file"), BorderLayout.NORTH);
		modePanel = new JPanel();
		JLabel modeLabel = new JLabel("Mode For AES Key");
		modeLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		modePanel.add(modeLabel);
		
		JComboBox<Mode> modeBox = new JComboBox<Mode>();
		modeBox.setModel(new DefaultComboBoxModel<Mode>(new Mode[]{Mode.CFB, Mode.OFB, Mode.CTR}));
		modeBox.addActionListener((ActionEvent e) ->  {
			Store.mode = (Mode) modeBox.getSelectedItem();
		});
		modePanel.add(modeBox);
		modePanel.setVisible(false);
		
		bodyPanel = new JPanel();
		bodyPanel.add(modePanel);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		JPanel fileChooserPanel = new JPanel();
		fileChooserPanel.setLayout(new BoxLayout(fileChooserPanel, BoxLayout.X_AXIS));
		inputFile = new CustomTextField("File Input");
		JButton browseFile = new JButton("Browse");
		 
		browseFile.addActionListener((ActionEvent e) -> {
			Store.fileChooser.setVisible(true);
		});
		
		fileChooserPanel.add(inputFile);
		fileChooserPanel.add(browseFile);
		bodyPanel.add(fileChooserPanel);
		
		outputFile = new CustomTextField("File Output");
		bodyPanel.add(outputFile);
		
		action = new JPanel();
		bodyPanel.add(action);
		
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ICipherFile cipherHandler = ((ICipherFile) Store.currentTab.getBaseCipherHandler());
				cipherHandler.encryptFile(inputFile.getText(), outputFile.getText());
			}
		});
		action.add(encryptBtn);
		
		JButton decryptBtn = new JButton("Decrypt");
		decryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ICipherFile cipherHandler = ((ICipherFile) Store.currentTab.getBaseCipherHandler());
				cipherHandler.decryptFile(inputFile.getText(), outputFile.getText());
			}
		});
		action.add(decryptBtn);
		add(bodyPanel);
	}

	
	public void setOutput(String path) {
		outputFile.setText(path);
	}
	
	public void useSperateMode() {
		modePanel.setVisible(true);
	}
	
	public String getOutput() {
		return outputFile.getText();
	}

	public JPanel getAction() {
		return action;
	}

	public void setAction(JPanel action) {
		this.action = action;
	}

	public JPanel getBodyPanel() {
		return bodyPanel;
	}

	public void setBodyPanel(JPanel bodyPanel) {
		this.bodyPanel = bodyPanel;
	}
}
