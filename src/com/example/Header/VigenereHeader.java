package com.example.Header;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VigenereHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public VigenereHeader() {
		setVisible(false);
		add(new JLabel("Thuật toán tự động gen key dựa trên độ dài plain text"));
	}

}
