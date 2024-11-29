package com.example.seminar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.cipher.IHashFile;

public class FileHashPanel extends BaseFilePanel{
	private JTextArea outputFile;
	private static final long serialVersionUID = 1L;
	
	public FileHashPanel() {
		super();
		
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		add(new JLabel("Encrypt file"), BorderLayout.NORTH);
		
		JPanel bodyPanel = new JPanel();
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
		
		outputFile = new JTextArea();
		outputFile.setRows(4);
		bodyPanel.add(new JScrollPane(outputFile));
		
		JPanel action = new JPanel();
		bodyPanel.add(action);
		

		JButton hashBtn = new JButton("Hash");
		hashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hashText = ((IHashFile) Store.currentTab.cipherHandler).hash(inputFile.getText());
				outputFile.setText(hashText);
			}
		});
		action.add(hashBtn);
		
		add(bodyPanel);
	}
}
