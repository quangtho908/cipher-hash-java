package com.example.IVSize;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.example.seminar.Mode;
import com.example.seminar.ModeConfig;
import com.example.seminar.Store;

public abstract class BaseIVSize extends JPanel{
	private static final long serialVersionUID = 1L;
	public int defaultValue;
	protected JLabel label;
	
	
	public BaseIVSize() {
		ModeConfig modeConfig = getModeConfig();
		modeConfig.iVSizeOptions = this;
		Store.modeConfigs.put(getMode(), modeConfig);
		this.label = new JLabel("IV Size");
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
	}
	
	protected ModeConfig getModeConfig() {
		return new ModeConfig();
	};
	
	protected abstract Mode getMode();
}
