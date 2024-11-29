package com.example.IVSize;

import com.example.seminar.Mode;
import com.example.seminar.ModeConfig;

public class EBCIVSize extends BaseIVSize {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public EBCIVSize() {

	}
	
	@Override
	protected ModeConfig getModeConfig() {
		ModeConfig modeConfig = new ModeConfig();
		modeConfig.isUseIV = false;
		return modeConfig;
	}

	@Override
	protected Mode getMode() {
		// TODO Auto-generated method stub
		return Mode.ECB;
	}

}
