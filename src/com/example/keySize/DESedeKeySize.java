package com.example.keySize;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.example.seminar.Agorithm;
import com.example.seminar.Store;

public class DESedeKeySize extends KeySize {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public DESedeKeySize() {
		super();
		JComboBox<Integer> keySizeSelection = new JComboBox<Integer>();
		keySizeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.keySize = (int) keySizeSelection.getSelectedItem();
			}
		});
		Integer[] values = {112, 168};
		defaultValue = values[0];
		setLayout(new BorderLayout(0, 0));
		keySizeSelection.setModel(new DefaultComboBoxModel<Integer>(values));
		add(keySizeSelection);
		add(label, BorderLayout.NORTH);
		setVisible(false);
	}

	@Override
	protected Agorithm getAgorithm() {
		// TODO Auto-generated method stub
		return Agorithm.DESede;
	}

}
