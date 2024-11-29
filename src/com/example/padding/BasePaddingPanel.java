package com.example.padding;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.seminar.Mode;
import com.example.seminar.ModeConfig;
import com.example.seminar.Store;

public abstract class BasePaddingPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public String defaultValue;
	protected JLabel label;
	protected JComboBox<String> paddingSelection;
	
	public BasePaddingPanel() {
		if(getMode() != null) {
			_setModeConfig();
		}else Store.commonPaddingPanel = this;
		label = new JLabel("Padding");
		paddingSelection = new JComboBox<String>();
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
	}
	
	public void resetValue() {
		paddingSelection.setSelectedIndex(0);
	}
	
	protected abstract Mode getMode();
	
	private void _setModeConfig() {
		ModeConfig modeConfig = Store.modeConfigs.get(getMode());
		if(modeConfig == null) {
			modeConfig = new ModeConfig();
			Store.modeConfigs.put(getMode(), modeConfig);
		}
		modeConfig.basePaddingPanel = this;
	}
}
