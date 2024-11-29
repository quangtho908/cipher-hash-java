package com.example.Tabs;

import javax.swing.JPanel;

import com.example.Header.HashHeader;
import com.example.KeyHandler.IKeyHandler;
import com.example.body.HashCipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.MessageDigestHandler;

public class HashConfig extends TabConfig{
	
	public static String[] hash = {
			"MD5",
			"SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
			"SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512",
			"SHAKE128", "SHAKE256",
			"RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320"
	};
	
	public static String algorithm;
	
	public HashConfig() {
		super();
	}

	@Override
	public JPanel getHeader() {
		// TODO Auto-generated method stub
		return new HashHeader();
	}

	@Override
	public JPanel getBody() {
		// TODO Auto-generated method stub
		return new HashCipherPanel(true);
	}

	@Override
	public JPanel getFooter() {
		// TODO Auto-generated method stub
		return new JPanel();
	}

	@Override
	public IKeyHandler getKeyHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		// TODO Auto-generated method stub
		return new MessageDigestHandler();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.HASH;
	}
	
	@Override
	public void setupConfig() {
		super.setupConfig();
		algorithm = hash[0];
	}

}
