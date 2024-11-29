package com.example.Header;

import javax.swing.JPanel;
import com.example.keySize.RSAKeySize;

public class RSAHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public RSAHeader() {
		setVisible(false);
		add(new RSAKeySize());
		
	}

}
