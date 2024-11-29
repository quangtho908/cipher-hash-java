package com.example.KeyHandler;

import java.util.Random;

import com.example.Tabs.VigenereConfig;
import com.example.body.VigenereCipherPanel;
import com.example.seminar.Store;

public class KeyVirgenereHandler implements IKeyHandler{

	@Override
	public void genKey() {
		try {
			Random random = new Random();
	        StringBuilder key = new StringBuilder();
			VigenereCipherPanel currentFactory = (VigenereCipherPanel) Store.currentTab.body;

	        for (int i = 0; i < currentFactory.getPlainText().length(); i++) {
	            char randomChar = (char) ('A' + random.nextInt(26));
	            key.append(randomChar);
	        }
	        VigenereConfig.key = key.toString();
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}

	}

}
