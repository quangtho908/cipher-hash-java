package com.example.Tabs;

import javax.swing.JPanel;

import com.example.Header.VigenereHeader;
import com.example.KeyHandler.IKeyHandler;
import com.example.KeyHandler.KeyVirgenereHandler;
import com.example.body.VigenereCipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.Vigenere;
import com.example.keyPanel.KeyPanelVigenere;

public class VigenereConfig extends TabConfig{
	public static String key = "";

	@Override
	public JPanel getHeader() {
		return new VigenereHeader();
	}

	@Override
	public JPanel getBody() {
		return new VigenereCipherPanel();
	}

	@Override
	public JPanel getFooter() {
		return new KeyPanelVigenere();
	}

	@Override
	public IKeyHandler getKeyHandler() {
		return new KeyVirgenereHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		// TODO Auto-generated method stub
		return new Vigenere();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.Vigenere;
	}

}
