package com.example.cipher;

public abstract class BaseCipherHandler{
	
	public BaseCipherHandler() {
		addAgorithm();
	}
	
	protected abstract void addAgorithm();
	
	public abstract void loadKey();
	public abstract String encryptToBase64(String plainText);
	public abstract String decrypt(String cipher);
}
