package com.example.seminar;

import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class BaseFilePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JTextField inputFile;
	
	public void setInput(String path) {
		inputFile.setText(path);
	}
	
	public String getInput() {
		return inputFile.getText();
	}
}
