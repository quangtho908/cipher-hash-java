package com.example.KeyHandler;

import java.util.Random;

import com.example.Tabs.HillConfig;
import com.example.seminar.Store;

public class HillKeyHandler implements IKeyHandler{

	@Override
	public void genKey() {
		try {
			Random random = new Random();
	        int[][] key = new int[2][2];
	        do {
	            for (int i = 0; i < 2; i++) {
	                for (int j = 0; j < 2; j++) {
	                    key[i][j] = random.nextInt(26);
	                }
	            }
	        } while (!isInvertible(key));

	        HillConfig.key = key;
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
		
	}
	
	private boolean isInvertible(int[][] matrix) {
        int determinant = determinantMod26(matrix);
        return gcd(determinant, 26) == 1;
    }
	
	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	
	private int determinantMod26(int[][] matrix) {
		int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        return ((det % 26) + 26) % 26;
    }

}
