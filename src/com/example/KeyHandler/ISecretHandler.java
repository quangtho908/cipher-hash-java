package com.example.KeyHandler;

import javax.crypto.spec.IvParameterSpec;

public interface ISecretHandler {
	public void loadKey(String base64);
	public void loadIV(String base64);
	public IvParameterSpec genIV();
}
