package com.example.cipher;

public interface ICipherFile {
	public void encryptFile(String inputFile, String outputFile);
	public void decryptFile(String inputFile, String outputFile);
}
