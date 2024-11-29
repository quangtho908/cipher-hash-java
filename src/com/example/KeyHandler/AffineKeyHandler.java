package com.example.KeyHandler;

import java.util.Random;

import com.example.Tabs.AffineConfig;
import com.example.seminar.Store;

public class AffineKeyHandler implements IKeyHandler{

	@Override
	public void genKey() {
		try {
			Random random = new Random();
	        int a, b;
	        do {
	            a = random.nextInt(25) + 1; 
	        } while (!isCoprime(a)); 
	        b = random.nextInt(26);
	        AffineConfig.key = new int[]{a, b};
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
		
	}
	
	public void loadKey(int a, int b) {
		try {
			if(b < 0 || b > 25) {
				throw new Exception("b must between 0 and 25");
			}
			
			if(!isCoprime(a)) {
				throw new Exception("a must is coprime with 26");
			}
			AffineConfig.key = new int[]{a, b};
		} catch (Exception e) {
			Store.notice.setVisible(true);
			Store.notice.setContent(e.getMessage());
		}
	}
	
	public static boolean isCoprime(int a) {
        return gcd(a, 26) == 1;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


}
