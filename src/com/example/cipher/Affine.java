package com.example.cipher;

import com.example.Tabs.AffineConfig;
import com.example.seminar.Store;

public class Affine extends BaseCipherHandler{
	private int a;
	private int b;

	@Override
	protected void addAgorithm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadKey() {
		a = AffineConfig.key[0];
		b = AffineConfig.key[1];
	}

	@Override
	public String encryptToBase64(String plainText) {
		try {
			loadKey();
			StringBuilder encryptedText = new StringBuilder();
	        for (char c : plainText.toCharArray()) {
	            if (Character.isLetter(c)) {
	                char base = Character.isLowerCase(c) ? 'a' : 'A';
	                int x = c - base;
	                int encryptedChar = ((a * x + b) % 26 + 26) % 26;
	                encryptedText.append((char) (encryptedChar + base));
	            } else {
	                encryptedText.append(c);
	            }
	        }
	        return encryptedText.toString();

		}catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}

	@Override
	public String decrypt(String cipher) {
		try {
			loadKey();
			int aInverse = modInverse(a, 26);
	        if (aInverse == -1) {
	            throw new IllegalArgumentException("a number no inverted Modulo");
	        }
	        StringBuilder decryptedText = new StringBuilder();
	        for (char c : cipher.toCharArray()) {
	            if (Character.isLetter(c)) {
	                char base = Character.isLowerCase(c) ? 'a' : 'A';
	                int y = c - base;
	                int decryptedChar = ((aInverse * (y - b)) % 26 + 26) % 26;
	                decryptedText.append((char) (decryptedChar + base));
	            } else {
	                decryptedText.append(c);
	            }
	        }
	        return decryptedText.toString();
		}catch(Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}

        return "";
	}
	

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }

}
