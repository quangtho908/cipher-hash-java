package com.example.Tabs;

import javax.swing.JPanel;

import com.example.KeyHandler.HillKeyHandler;
import com.example.KeyHandler.IKeyHandler;
import com.example.body.HillCipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.Hill;
import com.example.keyPanel.KeyPanelHill;

public class HillConfig extends TabConfig{
	public static int[][] key;
	
	public HillConfig() {
		super();
	}

	@Override
	public JPanel getHeader() {
		return new JPanel();
	}

	@Override
	public JPanel getBody() {
		return new HillCipherPanel();
	}

	@Override
	public JPanel getFooter() {
		return new KeyPanelHill();
	}

	@Override
	public IKeyHandler getKeyHandler() {
		return new HillKeyHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		return new Hill();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.Hill;
	}

}
