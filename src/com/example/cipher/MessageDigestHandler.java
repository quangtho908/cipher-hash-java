package com.example.cipher;

import com.example.Tabs.HashConfig;
import com.example.seminar.Store;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class MessageDigestHandler extends BaseCipherHandler implements IHashFile{

	@Override
	protected void addAgorithm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadKey() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encryptToBase64(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance(HashConfig.algorithm, "BC");
			byte[] hash = md.digest(plainText.getBytes());
			
			return bytesToHex(hash);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}


	@Override
	public String decrypt(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hash(String inputFile) {
        try (FileInputStream fis = new FileInputStream(inputFile)) {
        	MessageDigest digest = MessageDigest.getInstance(HashConfig.algorithm, "BC");
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            
            byte[] hashBytes = digest.digest();
            
            return bytesToHex(hashBytes);
            
        } catch (Exception e) {
        	Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
        }
		return "";
	}
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
