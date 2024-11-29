package com.example.KeyHandler;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.Tabs.HmacConfig;
import com.example.seminar.Store;

public class HmacKeyHandler implements IKeyHandler, ISecretHandler{

	@Override
	public void loadKey(String base64) {
		try {
			byte[] bytes = Base64.getDecoder().decode(base64);
			SecretKey secretKey = new SecretKeySpec(bytes, Store.agorithm.toString());
			HmacConfig.key = secretKey;
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}

	}

	@Override
	public void loadIV(String base64) {
		
	}

	@Override
	public IvParameterSpec genIV() {
		return null;
	}

	@Override
	public void genKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(HmacConfig.algorithm, "BC");
			keyGen.init(256);
			SecretKey secretKey = keyGen.generateKey();
			HmacConfig.key = secretKey;
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
	}

}
