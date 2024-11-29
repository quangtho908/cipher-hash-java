package com.example.keySize;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.example.seminar.Store;

public class RSAKeySize extends JPanel {
	public int defaultValue;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public RSAKeySize() {
		super();
		JComboBox<Integer> keySizeSelection = new JComboBox<Integer>();
		keySizeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.keySize = (int) keySizeSelection.getSelectedItem();
			}
		});
		Integer[] values = {1024, 2048, 4096};
		keySizeSelection.setModel(new DefaultComboBoxModel<Integer>(values));
		keySizeSelection.setSelectedIndex(0);
		defaultValue = values[0];
		setLayout(new BorderLayout(0, 0));
		add(keySizeSelection);
		JLabel label = new JLabel("Key Size");
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		add(label, BorderLayout.NORTH);
	}
}
