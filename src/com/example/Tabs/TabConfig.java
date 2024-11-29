package com.example.Tabs;

import java.util.HashMap;
import javax.swing.JPanel;

import com.example.KeyHandler.IKeyHandler;
import com.example.cipher.BaseCipherHandler;

public abstract class TabConfig implements IBaseTabConfig{
	public static HashMap<Tabs, TabConfig> configs = new HashMap<Tabs, TabConfig>();
	public JPanel header;
	public JPanel body;
	public JPanel footer;
	public IKeyHandler keyHandler;
	public BaseCipherHandler cipherHandler;
	
	public TabConfig() {
		this.header = getHeader();
		this.body = getBody();
		this.footer = getFooter();
		this.keyHandler = getKeyHandler();
		this.cipherHandler = getBaseCipherHandler();
		configs.put(getType(), this);
		
	}
	
	@Override
	public void setupConfig() {
		header.setVisible(true);
		body.setVisible(true);
		footer.setVisible(true);
	}
	
	@Override
	public void downConfig() {
		header.setVisible(false);
		body.setVisible(false);
		footer.setVisible(false);
		
	}
	
	public abstract JPanel getHeader();
	
	public abstract JPanel getBody();
	
	public abstract JPanel getFooter();
	
	public abstract IKeyHandler getKeyHandler();
	
	public abstract BaseCipherHandler getBaseCipherHandler();
	
	public abstract Tabs getType();
}
