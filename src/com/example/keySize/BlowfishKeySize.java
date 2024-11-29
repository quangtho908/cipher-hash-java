package com.example.keySize;

import com.example.seminar.Agorithm;
import com.example.seminar.Store;
import javax.swing.JFormattedTextField;
import java.text.NumberFormat;
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BlowfishKeySize extends KeySize {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BlowfishKeySize() {
		super();
		JFormattedTextField keySize = new JFormattedTextField(NumberFormat.getNumberInstance());
		keySize.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				int value = Integer.parseInt(keySize.getText());
				if((value >= 32 && value <= 448) && (value % 8 == 0)) {
					Store.keySize = Integer.parseInt(keySize.getText());	
				}else {
					keySize.setValue(defaultValue);
					Store.keySize = defaultValue;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		keySize.setValue(128);
		defaultValue = 128;
		keySize.setColumns(5);
		setLayout(new BorderLayout(0, 0));
		add(keySize);
		add(label, BorderLayout.NORTH);
		setVisible(false);

	}

	@Override
	protected Agorithm getAgorithm() {
		// TODO Auto-generated method stub
		return Agorithm.Blowfish;
	}

}
