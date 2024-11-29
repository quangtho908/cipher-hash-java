package com.example.seminar;

import com.example.IVSize.BaseIVSize;
import com.example.padding.BasePaddingPanel;

public class ModeConfig {
	public boolean isUseIV = true;
	public boolean isUsePadding = true;
	public BaseIVSize iVSizeOptions;
	public BasePaddingPanel basePaddingPanel;
	public boolean isSupportFile = false;
	
	public ModeConfig() {}
	
	public ModeConfig(boolean isUseIV) {
		this.isUseIV = isUseIV;
	}
	
	public ModeConfig(BaseIVSize iVSizeOptions) {
		this.iVSizeOptions = iVSizeOptions;
	}
}
