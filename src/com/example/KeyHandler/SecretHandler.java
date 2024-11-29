package com.example.KeyHandler;

import com.example.seminar.AgorithmConfig;
import com.example.seminar.Store;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.Tabs.SyncModernConfig;
import com.example.cipher.SyncModern;
public class SecretHandler implements IKeyHandler, ISecretHandler{
	
	@Override
	public void loadKey(String base64String) {
		try {
			byte[] bytes = Base64.getDecoder().decode(base64String);
			SecretKey secretKey = new SecretKeySpec(bytes, Store.agorithm.toString());
			SyncModernConfig.secrets.put(Store.agorithm, secretKey);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}

	}

	@Override
	public void genKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(Store.agorithm.toString(), "BC");
			keyGen.init(Store.keySize);
			SecretKey secretKey = keyGen.generateKey();
			SyncModernConfig.secrets.put(Store.agorithm, secretKey);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
	}

	@Override
	public void loadIV(String base64String) {
		try {
			byte[] bytes = Base64.getDecoder().decode(base64String);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);
			SyncModern.ivParameterSpec = ivParameterSpec;
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
	}

	@Override
	public IvParameterSpec genIV() {
		try {
			AgorithmConfig agorithmConfig = Store.agorithmConfigs.get(Store.agorithm);
			byte[] ivBytes = new byte[agorithmConfig.ivSize];
			if(agorithmConfig.ivSizeByMode.get(Store.mode) != null) {
				ivBytes = new byte[agorithmConfig.ivSizeByMode.get(Store.mode)];
			}

			SecureRandom secureRandom = new SecureRandom();
		    secureRandom.nextBytes(ivBytes);
		    IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
		    SyncModern.ivParameterSpec = ivParameterSpec;
		    return ivParameterSpec;
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
		return null;

	}
}
