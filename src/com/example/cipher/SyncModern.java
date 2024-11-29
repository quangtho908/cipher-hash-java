package com.example.cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.example.Tabs.SyncModernConfig;
import com.example.seminar.Agorithm;
import com.example.seminar.AgorithmConfig;
import com.example.seminar.ModeConfig;
import com.example.seminar.Store;

public class SyncModern extends BaseCipherHandler implements ICipherFile{
	private SecretKey secretKey;
	public static IvParameterSpec ivParameterSpec;
	
	@Override
	public void loadKey() {
		this.secretKey = SyncModernConfig.secrets.get(Store.agorithm);
	}
	
	@Override
	public String encryptToBase64(String plainText) {
		byte[] cipherBytes = {};
		try {
			loadKey();
			Cipher cipherIn = Cipher.getInstance(Store.agorithm + "/" + Store.mode + "/" + Store.paddding, "BC");
			ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);
			if(modeConfig != null && !modeConfig.isUseIV) {
				cipherIn.init(Cipher.ENCRYPT_MODE, secretKey);
			}else {
				cipherIn.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			}
			
			
			cipherBytes = cipherIn.doFinal(plainText.getBytes());
			return Base64.getEncoder().encodeToString(cipherBytes);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		return "";
	}

	@Override
	protected void addAgorithm() {
		Store.agorithmConfigs.put(Agorithm.AES, new AgorithmConfig(16));
		Store.agorithmConfigs.put(Agorithm.DES, new AgorithmConfig(8, 56));
		Store.agorithmConfigs.put(Agorithm.Blowfish, new AgorithmConfig(8));
		Store.agorithmConfigs.put(Agorithm.DESede, new AgorithmConfig(8));
    Store.agorithmConfigs.put(Agorithm.Camellia, new AgorithmConfig(16));
	}

	@Override
	public String decrypt(String cipher) {
		byte[] cipherBytes = {};
		try {
			loadKey();
			Cipher cipherIn = Cipher.getInstance(Store.agorithm + "/" + Store.mode + "/" + Store.paddding, "BC");
			ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);
			if(modeConfig != null && !modeConfig.isUseIV) {
				cipherIn.init(Cipher.DECRYPT_MODE, secretKey);
				cipherBytes = cipherIn.doFinal(Base64.getDecoder().decode(cipher));
				return new String(cipherBytes);
			}
			
			if(ivParameterSpec == null) {
				throw new Exception("IV is invalid");
			}
			cipherIn.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			
			cipherBytes = cipherIn.doFinal(Base64.getDecoder().decode(cipher));
			
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
		    Cipher cipherOut = Cipher.getInstance(Store.agorithm + "/" + Store.mode + "/" + Store.paddding, "BC");
		    ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);
		    if(modeConfig != null && !modeConfig.isUseIV) {
				cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);
			}else {
			    cipherOut.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			}
		    
		    FileInputStream fis = new FileInputStream(inputFile);
		    FileOutputStream fos = new FileOutputStream(outputFile);
		    CipherOutputStream cos = new CipherOutputStream(fos, cipherOut);
		    
		    byte[] buffer = new byte[1024];
		    int read;
		    while ((read = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, read);
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
		    Cipher cipherIn = Cipher.getInstance(Store.agorithm + "/" + Store.mode + "/" + Store.paddding, "BC");
		    ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);
		    if(modeConfig != null && !modeConfig.isUseIV) {
				cipherIn.init(Cipher.DECRYPT_MODE, secretKey);
			}else {
				if(ivParameterSpec == null) {
					throw new Exception("IV is invalid");
				}
			    cipherIn.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			}
		    
		    FileInputStream fis = new FileInputStream(inputFile);
		    FileOutputStream fos = new FileOutputStream(outputFile);
		    CipherInputStream cis = new CipherInputStream(fis, cipherIn);
		    
		    byte[] buffer = new byte[1024];
		    int read;
		    while ((read = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
		    
		    fos.flush();
		    fos.close();
		    cis.close();
		    Store.notice.setVisible(true);
			Store.notice.setContent("Decrypt file successfully");
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
		
	}

}
