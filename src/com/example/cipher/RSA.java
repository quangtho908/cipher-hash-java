package com.example.cipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.CipherInputStream;

import com.example.Tabs.RSAConfig;
import com.example.seminar.Store;

public class RSA extends BaseCipherHandler implements ICipherFile{
	private PublicKey publicKey;
	private PrivateKey privateKey;

	@Override
	public void loadKey() {
		KeyPair keyPair = RSAConfig.keyPair;
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}

	@Override
	public String encryptToBase64(String plainText) {
		byte[] cipherBytes = {};
		try {
			loadKey();
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
			cipherBytes = cipher.doFinal(plainText.getBytes());
			return Base64.getEncoder().encodeToString(cipherBytes);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";

	}
	
	@Override
	protected void addAgorithm() {
		
	}

	@Override
	public String decrypt(String cipherText) {
		byte[] cipherBytes = {};
		try {
			loadKey();
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
			cipherBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(cipherBytes);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}

	@Override
	public void encryptFile(String inputFile, String outputFile) {
		try {
			loadKey();
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			int aesKeySize = Store.keySize / 8;
	        keyGenerator.init((aesKeySize > 256)? 256 : aesKeySize);
	        SecretKey aesKey = keyGenerator.generateKey();
	        
	        byte[] iv = new byte[16];
	        SecureRandom secureRandom = new SecureRandom();
	        secureRandom.nextBytes(iv);
	        
	        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
	        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
	        byte[] encryptedAesKey = cipher.doFinal(aesKey.getEncoded());
	        
	        Cipher aesCipher = Cipher.getInstance("AES/" + Store.mode + "/NoPadding");
	        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));
	        FileOutputStream fos = new FileOutputStream(outputFile);
	        FileInputStream fis = new FileInputStream(inputFile);
	        CipherOutputStream cos = new CipherOutputStream(fos, aesCipher);
	        
	        int read;
	        fos.write(iv);
	        fos.write(encryptedAesKey);
	        byte[] bytes = new byte[1024];
	        while ((read = fis.read(bytes)) != -1) {
                cos.write(bytes, 0, read);
            }
	        cos.flush();
	        fis.close();
	        cos.close();
	        Store.notice.setVisible(true);
			Store.notice.setContent("Encrypt file successfully");
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}

	}

	@Override
	public void decryptFile(String inputFile, String outputFile) {
		try {
			loadKey();
	
	        FileInputStream fis = new FileInputStream(inputFile);
	        byte[] iv = new byte[16];  
	        byte[] encryptedAesKey = new byte[Store.keySize / 8];
	        fis.read(iv);
	        fis.read(encryptedAesKey);
	        
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
	        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
	        
	        byte[] aesKeyBytes = cipher.doFinal(encryptedAesKey);
            SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");
	        Cipher aesCipher = Cipher.getInstance("AES/" + Store.mode + "/NoPadding");
	        
	        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
	        FileOutputStream fos = new FileOutputStream(outputFile);
	        CipherInputStream cis = new CipherInputStream(fis, aesCipher);
	        
	        int read;
	        byte[] bytes = new byte[1024];
	        while ((read = cis.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
	        fos.flush();
	        cis.close();
	        fos.close();
		    Store.notice.setVisible(true);
			Store.notice.setContent("Decrypt file successfully");
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
	}

}
