package com.example.keySize;

import com.example.seminar.Agorithm;
import com.example.seminar.Store;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class AESKeySize extends KeySize {

	private static final long serialVersionUID = 1L;
	
	public AESKeySize() {
		super();
		JComboBox<Integer> keySizeSelection = new JComboBox<Integer>();
		keySizeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.keySize = (int) keySizeSelection.getSelectedItem();
			}
		});
		Integer[] values = {128, 192, 256};
		defaultValue = values[0];
		setLayout(new BorderLayout(0, 0));
		keySizeSelection.setModel(new DefaultComboBoxModel<Integer>(values));
		add(keySizeSelection);
		add(label, BorderLayout.NORTH);
		setVisible(false);
		
	}

	@Override
	protected Agorithm getAgorithm() {
		return Agorithm.AES;
	}

}
