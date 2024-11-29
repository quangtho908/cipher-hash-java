package com.example.KeyHandler;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import com.example.Tabs.RSAConfig;
import com.example.seminar.Store;

public class KeyPairHandler implements IKeyHandler, IKeyPairHandler{
	@Override
	public void loadKey(String publicKey, String privateKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
			PKCS8EncodedKeySpec privateSpec= new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
			PublicKey pubKey = keyFactory.generatePublic(publicSpec);
			PrivateKey privKey = keyFactory.generatePrivate(privateSpec);
			
			RSAConfig.keyPair = new KeyPair(pubKey, privKey);
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		} 
	}

	@Override
	public void genKey() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
			keyGen.initialize(Store.keySize);
			KeyPair keyPair = keyGen.generateKeyPair();
			RSAConfig.keyPair = keyPair;
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}

		
	}
}
