package com.example.cipher;

import java.io.FileInputStream;
import javax.crypto.Mac;
import com.example.Tabs.HmacConfig;
import com.example.seminar.Store;

public class Hmac extends BaseCipherHandler implements IHashFile{

	@Override
	public String hash(String inputFile) {
		try (FileInputStream fis = new FileInputStream(inputFile)) {
			Mac mac = Mac.getInstance(HmacConfig.algorithm, "BC");
			 mac.init(HmacConfig.key);
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                mac.update(buffer, 0, bytesRead);
            }
            
            byte[] hashBytes = mac.doFinal();
            
            return bytesToHex(hashBytes);
            
        } catch (Exception e) {
        	Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
        }
		return "";
	}

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
			byte[] dataBytes = plainText.getBytes("UTF-8");
			 Mac mac = Mac.getInstance(HmacConfig.algorithm, "BC");
			 mac.init(HmacConfig.key);
			 
			 byte[] hmacResult = mac.doFinal(dataBytes);
			 
			 return bytesToHex(hmacResult);
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
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
