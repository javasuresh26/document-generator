/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Suresh
 */
public class MdlFunctions {
    
    public JLabel setJLabel(JLabel lbl) {
        lbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        lbl.setBackground(new Color(255, 255, 255));
        return lbl;
    }
    public JLabel setCaptionLabel(JLabel lblCaption) {
        lblCaption.setFont(new Font("Dialog", Font.BOLD, 12));
        lblCaption.setForeground(new Color(255, 255, 255));
        return lblCaption;
    }
    
     public JTextField setJTextField(JTextField txtfield) {
        txtfield.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtfield.setColumns(20);
        txtfield.setBackground(new Color(255, 255, 255));
        return txtfield;
    }
    
    public JButton setJButton(JButton bttn,String setActionCmd, String srcToolTipText) {
        bttn.setFont(new Font("Dialog", Font.PLAIN, 12));
        bttn.setBackground(new Color(255, 255, 255));
        bttn.setToolTipText(srcToolTipText);
        bttn.setActionCommand(setActionCmd);
        return bttn;
    }
    public JTextArea setJTextArea(JTextArea txtArea) {
        txtArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtArea.setBackground(new Color(255, 255, 255));
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        return txtArea;
    }

    public JComboBox setJComboBox(JComboBox cbField) {
        cbField.setFont(new Font("Dialog", Font.PLAIN, 12));
        cbField.setBackground(new Color(255, 255, 255));
        return cbField;
    }
    
    public JTabbedPane setJTabbedPane(JTabbedPane jTabbedPane) {
        jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 15));
        jTabbedPane.setBackground(new Color(255, 255, 255));
        return jTabbedPane;
    }
    
    public JButton setJButton(JButton bttn, int sLeft, int sTop, int sWidth, int sHeight, String setActionCmd, String srcToolTipText) {
        bttn.setBounds(sLeft, sTop, sWidth, sHeight);
        bttn.setFont(new Font("Dialog", Font.PLAIN, 12));
        bttn.setBackground(new Color(255, 255, 255));
        bttn.setToolTipText(srcToolTipText);
        bttn.setActionCommand(setActionCmd);
        return bttn;
    }

    public JLabel setJLabel(JLabel lbl, int sLeft, int sTop, int sWidth, int sHeight) {
        lbl.setBounds(sLeft, sTop, sWidth, sHeight);
        lbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        lbl.setBackground(new Color(255, 255, 255));
        return lbl;
    }

    public JTextField setJTextField(JTextField txtfield, int sLeft, int sTop, int sWidth, int sHeight) {
        txtfield.setBounds(sLeft, sTop, sWidth, sHeight);
        txtfield.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtfield.setBackground(new Color(255, 255, 255));
        return txtfield;
    }

    public JTextArea setJTextArea(JTextArea txtArea, int sLeft, int sTop, int sWidth, int sHeight) {
        txtArea.setBounds(sLeft, sTop, sWidth, sHeight);
        txtArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtArea.setBackground(new Color(255, 255, 255));
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        return txtArea;
    }

    public JComboBox setJComboBox(JComboBox cbField, int sLeft, int sTop, int sWidth, int sHeight) {
        cbField.setBounds(sLeft, sTop, sWidth, sHeight);
        cbField.setFont(new Font("Dialog", Font.PLAIN, 12));
        cbField.setBackground(new Color(255, 255, 255));
        return cbField;
    }
}
