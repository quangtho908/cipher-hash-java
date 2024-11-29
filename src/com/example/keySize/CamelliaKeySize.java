package com.example.keySize;

import com.example.seminar.Agorithm;
import com.example.seminar.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamelliaKeySize extends KeySize{
  private static final long serialVersionUID = 1L;

  public CamelliaKeySize() {
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
    // TODO Auto-generated method stub
    return Agorithm.Camellia;
  }
}
