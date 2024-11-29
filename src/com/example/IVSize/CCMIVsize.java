package com.example.IVSize;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import com.example.seminar.AgorithmConfig;
import com.example.seminar.Mode;
import com.example.seminar.Store;

public class CCMIVsize extends BaseIVSize {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CCMIVsize() {
		super();
		JFormattedTextField keySize = new JFormattedTextField(NumberFormat.getNumberInstance());
		keySize.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				int value = Integer.parseInt(keySize.getText());
				AgorithmConfig agorithmConfig = Store.agorihmConfigs.get(Store.agorithm);
				if(value >= 7 && value <= 13) {
					agorithmConfig.ivSizeByMode.put(Store.mode, Integer.parseInt(keySize.getText()));
				}else {
					keySize.setValue(defaultValue);
					agorithmConfig.ivSizeByMode.put(Store.mode, defaultValue);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		keySize.setValue(7);
		defaultValue = 7;
		keySize.setColumns(5);
		setLayout(new BorderLayout(0, 0));
		add(keySize);
		add(label, BorderLayout.NORTH);
		setVisible(false);
	}

	@Override
	protected Mode getMode() {
		// TODO Auto-generated method stub
		return Mode.CCM;
	}

}
