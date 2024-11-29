package com.example.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.example.body.BaseCipherPanel;
import com.example.seminar.Store;

public class FileChoosen extends JDialog {
	
	public String file;
	private JFileChooser fileChooser;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public FileChoosen() {
		setVisible(false);
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		fileChooser = new JFileChooser();
		contentPanel.add(fileChooser);
		
		fileChooser.addActionListener((ActionEvent e) -> {
			String action = e.getActionCommand();
			switch(action) {
				case "ApproveSelection":
					approvedFile();
					break;
				default:
					break;
			}
			setVisible(false);
		});
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}
	
	private void approvedFile() {
		file = fileChooser.getSelectedFile().getPath();
		((BaseCipherPanel) Store.currentTab.body).fileCipherPanel.setInput(file);
	}

}
