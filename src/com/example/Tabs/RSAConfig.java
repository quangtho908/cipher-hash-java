package com.example.Tabs;

import java.security.KeyPair;
import javax.swing.JPanel;
import com.example.Header.RSAHeader;
import com.example.KeyHandler.IKeyHandler;
import com.example.KeyHandler.KeyPairHandler;
import com.example.body.RSACipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.RSA;
import com.example.keyPanel.KeyPanelAsync;
import com.example.keySize.RSAKeySize;
import com.example.seminar.Mode;
import com.example.seminar.Store;

public class RSAConfig extends TabConfig implements IBaseTabConfig{
	
	public static KeyPair keyPair;
	
	public RSAConfig() {
		super();
		new RSAKeySize();
	}

	@Override
	public JPanel getHeader() {
		return new RSAHeader();
	}

	@Override
	public JPanel getBody() {
		return new RSACipherPanel();
	}

	@Override
	public JPanel getFooter() {
		return new KeyPanelAsync();
	}

	@Override
	public Tabs getType() {
		return Tabs.RSA;
	}
	
	@Override
	public IKeyHandler getKeyHandler() {
		return new KeyPairHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		return new RSA();
	}

	@Override
	public void setupConfig() {
		super.setupConfig();
		Store.keySize = 1024;
		Store.mode = Mode.CFB;
	}

}
