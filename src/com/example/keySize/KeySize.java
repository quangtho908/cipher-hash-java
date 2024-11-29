package com.example.keySize;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.seminar.Agorithm;

public abstract class KeySize extends JPanel {

	private static final long serialVersionUID = 1L;
	public static HashMap<Agorithm, JPanel> keyPanels = new HashMap<Agorithm, JPanel>();
	public int defaultValue;
	protected JLabel label;

	public KeySize() {
		keyPanels.put(getAgorithm(), this);
		this.label = new JLabel("Key Size");
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
	}
	
	protected abstract Agorithm getAgorithm();

}
