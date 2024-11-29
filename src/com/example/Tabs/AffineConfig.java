package com.example.Tabs;

import javax.swing.JPanel;

import com.example.KeyHandler.AffineKeyHandler;
import com.example.KeyHandler.IKeyHandler;
import com.example.body.AffineCipherPanel;
import com.example.cipher.Affine;
import com.example.cipher.BaseCipherHandler;
import com.example.keyPanel.KeyPanelAffine;

public class AffineConfig extends TabConfig{
	
	public static int[] key;

	@Override
	public JPanel getHeader() {
		return new JPanel();
	}

	@Override
	public JPanel getBody() {
		return new AffineCipherPanel();
	}

	@Override
	public JPanel getFooter() {
		return new KeyPanelAffine();
	}

	@Override
	public IKeyHandler getKeyHandler() {
		return new AffineKeyHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		return new Affine();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.Affine;
	}

}
