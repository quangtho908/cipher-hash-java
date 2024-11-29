package com.example.body;

import com.example.seminar.FileCipherPanel;

public class SyncModernCipherPanel extends BaseCipherPanel {

	private static final long serialVersionUID = 1L;
	
	public SyncModernCipherPanel() {
		super();
		fileCipherPanel = new FileCipherPanel();
		
		add(fileCipherPanel);
	}
	
	

}
