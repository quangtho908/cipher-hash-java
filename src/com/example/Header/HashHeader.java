package com.example.Header;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.example.Tabs.HashConfig;

public class HashHeader extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public HashHeader() {
		super();
		JPanel algotihmPanel = new JPanel();
		algotihmPanel.setLayout(new BorderLayout(0, 0));
		JLabel algoLabel = new JLabel("Algorithm");
		algoLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
		JComboBox<String> algoritmSelection = new JComboBox<String>();
		algoritmSelection.setModel(new DefaultComboBoxModel<String>(HashConfig.hash));
		
		algoritmSelection.addActionListener((ActionEvent e) -> {
			HashConfig.algorithm = (String) algoritmSelection.getSelectedItem();
		});
		
		algotihmPanel.add(algoLabel, BorderLayout.NORTH);
		algotihmPanel.add(algoritmSelection);
		add(algotihmPanel);
		setVisible(false);
	}

}
