package com.example.Header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.example.body.SyncModernCipherPanel;
import com.example.cipher.SyncModern;
import com.example.keyPanel.KeyPanelSyncModern;
import com.example.keySize.KeySize;
import com.example.seminar.Agorithm;
import com.example.seminar.Mode;
import com.example.seminar.ModeConfig;
import com.example.seminar.Store;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;

public class SyncModernHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SyncModernHeader() {
		setVisible(false);
		JPanel agorithmPanel = new JPanel();
		add(agorithmPanel);
		agorithmPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel agorithmLabel = new JLabel("Agorithm");
		agorithmLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		agorithmPanel.add(agorithmLabel, BorderLayout.NORTH);
		JComboBox<Agorithm> agorithm = new JComboBox<Agorithm>();
		agorithmPanel.add(agorithm, BorderLayout.WEST);
		
		JPanel modePanel = new JPanel();
		add(modePanel);
		modePanel.setLayout(new BorderLayout(0, 0));
		
		agorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store.agorithm = (Agorithm) agorithm.getSelectedItem();
				Store.currentKeySizePanel.setVisible(false);
				SyncModern.ivParameterSpec = null;
				((KeyPanelSyncModern) Store.currentTab.footer).getIvPanel().clear();
				if(KeySize.keyPanels.get(Store.agorithm) != null) {
					Store.currentKeySizePanel = KeySize.keyPanels.get(Store.agorithm);
					Store.keySize = ((KeySize) Store.currentKeySizePanel).defaultValue;
					Store.currentKeySizePanel.setVisible(true);
				}else Store.keySize = Store.agorihmConfigs.get(Store.agorithm).keySize;
			}
		});
		agorithm.setModel(new DefaultComboBoxModel<Agorithm>(Agorithm.values()));
		
		JLabel modeLabel = new JLabel("Mode");
		modeLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		modePanel.add(modeLabel, BorderLayout.NORTH);
		
		JComboBox<Mode> mode = new JComboBox<Mode>();
		mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SyncModern.ivParameterSpec = null;
				((KeyPanelSyncModern) Store.currentTab.footer).getIvPanel().clear();
				Store.mode = (Mode) mode.getSelectedItem();
				if(Store.currentKeyIVSizePanel != null) {
					Store.currentKeyIVSizePanel.setVisible(false);
				}
				_loadPaddingPanel();
				_loadSupportFile();
				ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);

				if(modeConfig != null && modeConfig.iVSizeOptions != null) {
					Store.currentKeyIVSizePanel = modeConfig.iVSizeOptions;
					Store.agorihmConfigs.get(Store.agorithm).ivSizeByMode.put(Store.mode, Store.currentKeyIVSizePanel.defaultValue);
					Store.currentKeyIVSizePanel.setVisible(true);
				}
				((KeyPanelSyncModern) Store.currentTab.footer).setVisibleIV(true);
				if(modeConfig != null && !modeConfig.isUseIV) {
					((KeyPanelSyncModern) Store.currentTab.footer).setVisibleIV(false);
				}
			}
		});
		modePanel.add(mode);
		mode.setModel(new DefaultComboBoxModel<Mode>(Mode.values()));
	}
	
	private void _loadPaddingPanel() {
		Store.currentPaddingPanel.setVisible(false);
		ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);
		if(modeConfig != null && !modeConfig.isUsePadding) {
			Store.paddding = "NoPadding";
			return;
		}
		if(modeConfig != null && modeConfig.basePaddingPanel != null) {
			Store.currentPaddingPanel = modeConfig.basePaddingPanel;
			Store.paddding = Store.currentPaddingPanel.defaultValue;
			Store.currentPaddingPanel.setVisible(true);
			Store.currentPaddingPanel.resetValue();
			return;
		}
		
		Store.currentPaddingPanel = Store.commonPaddingPanel;
		Store.paddding = Store.currentPaddingPanel.defaultValue;
		Store.currentPaddingPanel.setVisible(true);
		Store.currentPaddingPanel.resetValue();
	}
	
	private void _loadSupportFile() {
		ModeConfig modeConfig = Store.modeConfigs.get(Store.mode);
		SyncModernCipherPanel currentBody = (SyncModernCipherPanel) Store.currentTab.body;
		
		if(modeConfig.isSupportFile) {
			currentBody.fileCipherPanel.setVisible(true);
			return;
		}
		currentBody.fileCipherPanel.setVisible(false);
	}

}
