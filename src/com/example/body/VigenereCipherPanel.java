package com.example.body;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class VigenereCipherPanel extends BaseCipherPanel {
	private String plainText; 
	private static final long serialVersionUID = 1L;
	
	public VigenereCipherPanel() {
		plaintextInput.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				plainText = plaintextInput.getText();
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {

			}
		});
	}
	
	public String getPlainText() {
		return plainText;
	}
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
}
