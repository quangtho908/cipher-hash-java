package com.example.Tabs;

import javax.swing.JPanel;

import com.example.KeyHandler.CaesarKeyHandler;
import com.example.KeyHandler.IKeyHandler;
import com.example.body.CaesarCipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.Caesar;
import com.example.keyPanel.KeyPanelCaesar;

public class CaesarConfig extends TabConfig implements IBaseTabConfig{
	public static int key;
	
	@Override
	public JPanel getHeader() {
		// TODO Auto-generated method stub
		return new JPanel();
	}

	@Override
	public JPanel getBody() {
		// TODO Auto-generated method stub
		return new CaesarCipherPanel();
	}

	@Override
	public JPanel getFooter() {
		// TODO Auto-generated method stub
		return new KeyPanelCaesar();
	}

	@Override
	public IKeyHandler getKeyHandler() {
		// TODO Auto-generated method stub
		return new CaesarKeyHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		// TODO Auto-generated method stub
		return new Caesar();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.Caesar;
	}
}
