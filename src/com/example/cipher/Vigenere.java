package com.example.cipher;

import com.example.KeyHandler.IKeyHandler;
import com.example.Tabs.VigenereConfig;
import com.example.body.VigenereCipherPanel;
import com.example.keyPanel.KeyPanelVigenere;
import com.example.seminar.Store;

public class Vigenere extends BaseCipherHandler{
	
	private String fullKey;


	@Override
	protected void addAgorithm() {}

	@Override
	public void loadKey() {}

	@Override
	public String encryptToBase64(String plainText) {
		try {
			autoGenKey();
			StringBuilder encryptedText = new StringBuilder();
			genFullKey(plainText);
	        for (int i = 0; i < plainText.length(); i++) {
	            char c = plainText.charAt(i);
	            if (Character.isLetter(c)) {
	                char base = Character.isLowerCase(c) ? 'a' : 'A';
	                char keyChar = Character.isLowerCase(fullKey.charAt(i)) ? 'a' : 'A';
	                encryptedText.append((char) ((c - base + (fullKey.charAt(i) - keyChar)) % 26 + base));
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
			if(VigenereConfig.key.isEmpty()) {
				throw new Exception("Key does not exists");
			}
			
			StringBuilder decryptedText = new StringBuilder();
			genFullKey(cipher);
	        for (int i = 0; i < cipher.length(); i++) {
	            char c = cipher.charAt(i);
	            if (Character.isLetter(c)) {
	                char base = Character.isLowerCase(c) ? 'a' : 'A';
	                char keyChar = Character.isLowerCase(fullKey.charAt(i)) ? 'a' : 'A';
	                decryptedText.append((char) ((c - base - (fullKey.charAt(i) - keyChar) + 26) % 26 + base));
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
	
	private void genFullKey(String text) {
		StringBuilder fullKey = new StringBuilder(VigenereConfig.key);
        int keyLength = VigenereConfig.key.length();
        for (int i = keyLength; i < text.length(); i++) {
            fullKey.append(VigenereConfig.key.charAt(i % keyLength));
        }
        this.fullKey = fullKey.toString();
	}
	
	private void autoGenKey() {
		IKeyHandler currentFactory = Store.currentTab.keyHandler;
		VigenereCipherPanel currentBody = (VigenereCipherPanel) Store.currentTab.body;
		KeyPanelVigenere currPanelVigenere = (KeyPanelVigenere) Store.currentTab.footer;
		if(VigenereConfig.key.isEmpty()) {
			Store.keySize = currentBody.getPlainText().length();
			currentFactory.genKey();
			currPanelVigenere.setKeyValue(VigenereConfig.key);
		}
	}

}
