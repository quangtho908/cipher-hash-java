package com.example.Tabs;

import javax.crypto.SecretKey;
import javax.swing.JPanel;

import com.example.Header.HmacHeader;
import com.example.KeyHandler.HmacKeyHandler;
import com.example.KeyHandler.IKeyHandler;
import com.example.body.HmacCipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.Hmac;
import com.example.keyPanel.KeyPanelHmac;

public class HmacConfig extends TabConfig{
	
	public static String[] hash = {
			"HmacMD5",
			"HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512",
			"HmacSHA3-224", "HmacSHA3-256", "HmacSHA3-384", "HmacSHA3-512",
			"HmacRIPEMD128", "HmacRIPEMD160", "HmacRIPEMD256", "HmacRIPEMD320"
	};
	
	public static String algorithm;
	public static SecretKey key;
	
	public HmacConfig() {
		super();
	}

	@Override
	public JPanel getHeader() {
		// TODO Auto-generated method stub
		return new HmacHeader();
	}

	@Override
	public JPanel getBody() {
		return new HmacCipherPanel(true);
	}

	@Override
	public JPanel getFooter() {
		// TODO Auto-generated method stub
		return new KeyPanelHmac();
	}

	@Override
	public IKeyHandler getKeyHandler() {
		// TODO Auto-generated method stub
		return new HmacKeyHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		// TODO Auto-generated method stub
		return new Hmac();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.HMAC;
	}
	
	@Override
	public void setupConfig() {
		// TODO Auto-generated method stub
		super.setupConfig();
		algorithm = "HmacMD5";
	}

}
