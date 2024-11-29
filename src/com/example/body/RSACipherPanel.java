package com.example.body;

import com.example.seminar.FileCipherPanel;

public class RSACipherPanel extends BaseCipherPanel {
	private static final long serialVersionUID = 1L;
	
	public RSACipherPanel() {
		super();
		fileCipherPanel = new FileCipherPanel();
		((FileCipherPanel) fileCipherPanel).useSperateMode();
		add(fileCipherPanel);
	}
}
