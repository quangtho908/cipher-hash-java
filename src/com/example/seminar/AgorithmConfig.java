package com.example.seminar;

import java.util.HashMap;

public class AgorithmConfig {
	public int ivSize;
	public int keySize;
	public boolean isOnlyMode = false;
	public Mode mode;
	public HashMap<Mode, Integer> ivSizeByMode = new HashMap<Mode, Integer>(); 
	public AgorithmConfig(int ivSize) {
		this.ivSize = ivSize;
	}
	
	public AgorithmConfig(int ivSize, int keySize) {
		this.ivSize = ivSize;
		this.keySize = keySize;
	}
}
