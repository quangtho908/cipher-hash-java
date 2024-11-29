package com.example.cipher;

import com.example.Tabs.CaesarConfig;
import com.example.seminar.Store;

public class Caesar extends BaseCipherHandler {

	@Override
	public void loadKey() {}

	@Override
	public String encryptToBase64(String plainText) {
		try {
			StringBuilder encryptedText = new StringBuilder();
	        for (char c : plainText.toCharArray()) {
	            if (Character.isLetter(c)) {
	                char base = Character.isLowerCase(c) ? 'a' : 'A';
	                encryptedText.append((char) ((c - base + CaesarConfig.key) % 26 + base));
	            } else {
	                encryptedText.append(c);
	            }
	        }
	        
			return encryptedText.toString();
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
		return "";
		
	}

	@Override
	public String decrypt(String cipher) {
		try {
			StringBuilder decryptedText = new StringBuilder();
			int key = 26 - (CaesarConfig.key % 26);
	        for (char c : cipher.toCharArray()) {
	            if (Character.isLetter(c)) {
	                char base = Character.isLowerCase(c) ? 'a' : 'A';
	                decryptedText.append((char) ((c - base + key) % 26 + base));
	            } else {
	                decryptedText.append(c);
	            }
	        }
	        
	        return decryptedText.toString();
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
		return "";
		
	}

	@Override
	protected void addAgorithm() {}

}
