package com.example.KeyHandler;

import java.util.Random;
import com.example.Tabs.CaesarConfig;

public class CaesarKeyHandler implements IKeyHandler {

	@Override
	public void genKey() {
		Random random = new Random();
        CaesarConfig.key = random.nextInt(26);
	}

}
