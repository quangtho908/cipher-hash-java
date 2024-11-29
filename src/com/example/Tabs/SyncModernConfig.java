package com.example.Tabs;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.swing.JPanel;

import com.example.Header.SyncModernHeader;
import com.example.IVSize.CCMIVsize;
import com.example.IVSize.EBCIVSize;
import com.example.KeyHandler.IKeyHandler;
import com.example.KeyHandler.SecretHandler;
import com.example.body.SyncModernCipherPanel;
import com.example.cipher.BaseCipherHandler;
import com.example.cipher.SyncModern;
import com.example.keyPanel.KeyPanelSyncModern;
import com.example.keySize.AESKeySize;
import com.example.keySize.BlowfishKeySize;
import com.example.keySize.CamelliaKeySize;
import com.example.keySize.DESedeKeySize;
import com.example.keySize.KeySize;
import com.example.padding.CBCPaddingPanel;
import com.example.padding.CommonPaddingPanel;
import com.example.padding.ECBPaddingPanel;
import com.example.seminar.Agorithm;
import com.example.seminar.Mode;
import com.example.seminar.ModeConfig;
import com.example.seminar.Store;

public class SyncModernConfig extends TabConfig implements IBaseTabConfig{
	public static HashMap<Agorithm, SecretKey> secrets = new HashMap<Agorithm, SecretKey>();
	private Mode[] modeHaveNoPadding = {Mode.CCM, Mode.GCM};
	private Mode[] modeSupportFile = {Mode.CFB, Mode.OFB, Mode.CTR};
	public SyncModernConfig() {
		super();
		new SecretHandler();
		new SyncModern();
		new AESKeySize();
		new DESedeKeySize();
		new BlowfishKeySize();
    new CamelliaKeySize();
		new CCMIVsize();
		new EBCIVSize();
		new CBCPaddingPanel();
		new ECBPaddingPanel();
		new CommonPaddingPanel();
		_loadPanelModeConfig(header);
		_setModeNoPadding();
		_setModeSupportFile();
		_loadPanelKeySize(header);
	}
	
	@Override
	public JPanel getHeader() {
		// TODO Auto-generated method stub
		return new SyncModernHeader();
	}

	@Override
	public JPanel getBody() {
		// TODO Auto-generated method stub
		return new SyncModernCipherPanel();
	}

	@Override
	public JPanel getFooter() {
		// TODO Auto-generated method stub
		return new KeyPanelSyncModern();
	}

	@Override
	public Tabs getType() {
		// TODO Auto-generated method stub
		return Tabs.SyncModern;
	}

	@Override
	public IKeyHandler getKeyHandler() {
		return new SecretHandler();
	}

	@Override
	public BaseCipherHandler getBaseCipherHandler() {
		// TODO Auto-generated method stub
		return new SyncModern();
	}

	@Override
	public void setupConfig() {
		super.setupConfig();
		Store.agorithm = Agorithm.AES;
		Store.mode = Mode.CFB;
		if(Store.currentKeySizePanel != null) {
			return;
		}
		Store.currentKeySizePanel = KeySize.keyPanels.get(Store.agorithm);
		Store.keySize = ((KeySize) Store.currentKeySizePanel).defaultValue;
		Store.currentKeySizePanel.setVisible(true);
	}


	private void _loadPanelModeConfig(JPanel header) {
		header.add(Store.commonPaddingPanel);
		Store.currentPaddingPanel = Store.commonPaddingPanel;
		Store.currentPaddingPanel.setVisible(true);
		Store.paddding = Store.currentPaddingPanel.defaultValue;
		for(Map.Entry<Mode, ModeConfig> panel : Store.modeConfigs.entrySet()) {
			if(panel.getValue().iVSizeOptions != null) {
				header.add(panel.getValue().iVSizeOptions);
			}
			if(panel.getValue().basePaddingPanel != null) {
				header.add(panel.getValue().basePaddingPanel);
			}
		}
	}
	
	private void _loadPanelKeySize(JPanel header) {
		for(Map.Entry<Agorithm, JPanel> panel : KeySize.keyPanels.entrySet()) {
			header.add(panel.getValue());
		}
	}
	
	private void _setModeNoPadding() {
		for(Mode mode : modeHaveNoPadding) {
			ModeConfig modeConfig = Store.modeConfigs.get(mode);
			if(modeConfig == null) {
				modeConfig = new ModeConfig();
				Store.modeConfigs.put(mode, modeConfig);
			}
			modeConfig.isUsePadding = false;
		}
	}
	
	private void _setModeSupportFile() {
		for(Mode mode : modeSupportFile) {
			ModeConfig modeConfig = Store.modeConfigs.get(mode);
			if(modeConfig == null) {
				modeConfig = new ModeConfig();
				Store.modeConfigs.put(mode, modeConfig);
			}
			modeConfig.isSupportFile = true;
		}
	}

}
