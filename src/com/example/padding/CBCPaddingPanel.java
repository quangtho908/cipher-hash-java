package com.example.padding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import com.example.seminar.Mode;
import com.example.seminar.Store;

public class CBCPaddingPanel extends BasePaddingPanel {

	private static final long serialVersionUID = 1L;

	public CBCPaddingPanel() {
		super();
		paddingSelection = new JComboBox<String>();
		paddingSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.paddding = (String) paddingSelection.getSelectedItem();
			}
		});
		String[] values = {"PKCS5Padding", "PKCS7Padding"};
		defaultValue = values[0];
		setLayout(new BorderLayout(0, 0));
		paddingSelection.setModel(new DefaultComboBoxModel<String>(values));
		add(paddingSelection);
		add(label, BorderLayout.NORTH);
		setVisible(false);
	}

	@Override
	protected Mode getMode() {
		return Mode.CBC;
	}

}