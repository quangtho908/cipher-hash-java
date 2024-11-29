package com.example.cipher;

import com.example.Tabs.HillConfig;
import com.example.seminar.Store;

public class Hill extends BaseCipherHandler{
	@Override
	protected void addAgorithm() {
		
	}

	@Override
	public void loadKey() {}

	@Override
	public String encryptToBase64(String plaintext) {
		
		try {
			if(plaintext.length() > 5) {
				throw new Exception("Plaintext must have lenght less than 5");
			}
			int[][] key = HillConfig.key;
			plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
			plaintext = addPadding(plaintext);

	        StringBuilder ciphertext = new StringBuilder();
	        for (int i = 0; i < plaintext.length(); i += 2) {
	            int[] vector = new int[2];
	            for (int j = 0; j < 2; j++) {
	                vector[j] = plaintext.charAt(i + j) - 'A';
	            }

	            for (int j = 0; j < 2; j++) {
	                int value = 0;
	                for (int k = 0; k < 2; k++) {
	                    value += key[j][k] * vector[k];
	                }
	                ciphertext.append((char) ((value % 26 + 26) % 26 + 'A'));
	            }
	        }
	        return ciphertext.toString();
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}
	
	@Override
	public String decrypt(String ciphertext) {
		try {
			int[][] key = HillConfig.key;
			int determinant = determinantMod26(key);
	        int determinantInverse = modInverse(determinant, 26);

	        int[][] adjugate = new int[2][2];
	        adjugate[0][0] = key[1][1];
	        adjugate[0][1] = -key[0][1];
	        adjugate[1][0] = -key[1][0];
	        adjugate[1][1] = key[0][0];

	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < 2; j++) {
	                adjugate[i][j] = ((adjugate[i][j] * determinantInverse % 26) + 26) % 26;
	            }
	        }

	        StringBuilder plaintext = new StringBuilder();
	        for (int i = 0; i < ciphertext.length(); i += 2) {
	            int[] vector = new int[2];
	            for (int j = 0; j < 2; j++) {
	                vector[j] = ciphertext.charAt(i + j) - 'A';
	            }

	            for (int j = 0; j < 2; j++) {
	                int value = 0;
	                for (int k = 0; k < 2; k++) {
	                    value += adjugate[j][k] * vector[k];
	                }
	                plaintext.append((char) ((value % 26 + 26) % 26 + 'A'));
	            }
	        }
	        return removePadding(plaintext.toString());
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
		return "";
	}
    
    private int modInverse(int a, int m) {
    	for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1) return x;
        }
        return -1;
    }
    
	private int determinantMod26(int[][] matrix) {
		int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        return ((det % 26) + 26) % 26;
    }
	
	private static String addPadding(String plaintext) {
        int padding = (2 - plaintext.length() % 2) % 2;
        if (padding > 0) {
            plaintext += "X".repeat(padding - 1);
            plaintext += (char) ('0' + padding);
        } else {
            plaintext += '0';
        }
        return plaintext;
    }
	
	private static String removePadding(String plaintext) {
        int padding = plaintext.charAt(plaintext.length() - 1) - '0';
        if (padding > 0 && padding < 2) {
            return plaintext.substring(0, plaintext.length() - padding);
        }
        return plaintext.substring(0, plaintext.length() - 1);
    }



}
