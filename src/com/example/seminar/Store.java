package com.example.seminar;

import java.util.HashMap;
import com.example.dialog.*;
import com.example.padding.BasePaddingPanel;
import javax.swing.JPanel;
import com.example.IVSize.BaseIVSize;
import com.example.Tabs.TabConfig;

public class Store {
	public static Agorithm agorithm;
	public static Mode mode;
	public static int keySize = 32;
	public static JPanel currentKeySizePanel;
	public static String paddding;
	public static BasePaddingPanel currentPaddingPanel;
	public static BasePaddingPanel commonPaddingPanel;
	public static BaseIVSize currentKeyIVSizePanel;
	public static TabConfig currentTab;
	public static HashMap<Agorithm, AgorithmConfig> agorihmConfigs = new HashMap<Agorithm, AgorithmConfig>();
	public static HashMap<Mode, ModeConfig> modeConfigs = new HashMap<Mode, ModeConfig>();
	public static Notice notice = new Notice();
	public static FileChoosen fileChooser = new FileChoosen();
}
