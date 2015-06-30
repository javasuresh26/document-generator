/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.form.Entity;
import com.documentgenerator.form.EntityType;
import com.documentgenerator.print.EntityTablePrint;
import static com.documentgenerator.view.NameDetailsPanel.jSPMessgae;
import com.documentgenerator.view.utils.ImagePanel;
import com.documentgenerator.view.utils.MdlFunctions;
import com.documentgenerator.view.utils.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Suresh
 */
public class DocumentEntityDialog extends JDialog {

    private final MdlFunctions mdlFunctions = new MdlFunctions();

    EntityTablePrint entity;

    private final int componentCount = 3;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public static JScrollPane jSPDocument = new JScrollPane();
    JPanel jpnlMain = new JPanel();
    JPanel northPanel = new ImagePanel(new FlowLayout(), WindowUtils.getImageIcon("images/header.gif").getImage());
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel(new FlowLayout());

    JLabel lblIcon = new JLabel(WindowUtils.getImageIcon("images/sections list.gif"));
    JLabel lblCaption = new JLabel("IMPORTANT: Text Fields must not empty.");

    JButton btnUpdate = new JButton("Update", WindowUtils.getImageIcon("images/save.gif"));
    JButton btnReset = new JButton("Reset", WindowUtils.getImageIcon("images/reset.gif"));
    JButton btnCancel = new JButton("Cancel", WindowUtils.getImageIcon("images/cancel.gif"));

    JLabel lblDate = new JLabel("Date:*");
    JLabel lblDocument = new JLabel("Document:*");
    JLabel lblSurvey = new JLabel("Survey:*");

    JTextField txtDate = new JTextField("");
    JTextArea taDocument = new JTextArea("");
    JTextField txtSurvey = new JTextField("");

    JFrame frame;
    boolean isUpdate;

    List<String> entityValues;

    public DocumentEntityDialog(JFrame frame, boolean isUpdate, EntityTablePrint entity) throws Exception {

        super(frame, true);
        this.frame = frame;
        setIconImage(WindowUtils.getImageIcon("images/sections.gif").getImage());

        this.entity = entity;
        this.isUpdate = isUpdate;

        if (isUpdate == true) {
            btnUpdate.setText("Save");
            loadItem();
            setTitle("Edit Document Deatils");
        } else {
            setTitle("New Document Deatils");
            btnUpdate.setText("Add");
        }

        mdlFunctions.setCaptionLabel(lblCaption);
        northPanel.add(lblIcon);
        northPanel.add(lblCaption);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        JPanel pnlLabel = new JPanel(new GridLayout(componentCount, 1, 1, 1));
        JPanel pnlfield = new JPanel(new GridLayout(componentCount, 1, 1, 1));
        pnlLabel.setBackground(Color.WHITE);
        pnlfield.setBackground(Color.WHITE);

        pnlLabel.add(mdlFunctions.setJLabel(lblDate));
        pnlfield.add(mdlFunctions.setJTextField(txtDate));
        
        pnlLabel.add(mdlFunctions.setJLabel(lblDocument));
        //mdlFunctions.setJTextArea(taDocument);
        jSPDocument.getViewport().add(taDocument);
        pnlfield.add(jSPDocument);

        pnlLabel.add(mdlFunctions.setJLabel(lblSurvey));
        pnlfield.add(mdlFunctions.setJTextField(txtSurvey));

        centerPanel.add(BorderLayout.WEST, pnlLabel);
        centerPanel.add(BorderLayout.EAST, pnlfield);

        //-- Add the Update Button
        mdlFunctions.setJButton(btnUpdate, "update", "UPDATE");
        btnUpdate.setMnemonic(KeyEvent.VK_A);
        btnUpdate.addActionListener(JBActionListener);

        //-- Add the Reset Button
        mdlFunctions.setJButton(btnReset, "reset", "RESET");
        btnReset.setMnemonic(KeyEvent.VK_R);
        btnReset.addActionListener(JBActionListener);

        //-- Add the Cancel Button
        mdlFunctions.setJButton(btnCancel, "cancel", "CANCEL");
        btnCancel.setMnemonic(KeyEvent.VK_C);
        btnCancel.addActionListener(JBActionListener);

        southPanel.setBackground(Color.WHITE);
        southPanel.add(btnUpdate);
        southPanel.add(btnReset);
        southPanel.add(btnCancel);

        jpnlMain.setLayout(new BorderLayout());
        jpnlMain.setBackground(Color.WHITE);
        jpnlMain.add(northPanel, BorderLayout.PAGE_START);
        jpnlMain.add(centerPanel, BorderLayout.CENTER);
        jpnlMain.add(southPanel, BorderLayout.SOUTH);

        getContentPane().add(jpnlMain);

        pack();
        setLocation((screen.width - 325) / 2, ((screen.height - 335) / 2));

        //setResizable(false);
        setVisible(true);
    }

    ActionListener JBActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch (command) {
                case "update":
                    update();
                    break;
                case "reset":
                    reset();
                    break;
                case "cancel":
                    dispose();
                    break;
            }
        }
    };

    private void update() {
        entity.setDate(txtDate.getText());
        entity.setDocument(taDocument.getText());
        entity.setSurvey(txtSurvey.getText());
        System.out.println(entity);
        try {

            dispose();
        } catch (Exception ex) {
            Logger.getLogger(DocumentEntityDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reset() {
        if (isUpdate) {
            loadItem();
        } else {
            txtDate.setText("");
            taDocument.setText("");
            txtSurvey.setText("");
        }
    }

    private void loadItem() {
        txtDate.setText(entity.getDate());
        taDocument.setText(entity.getDocument());
        txtSurvey.setText(entity.getSurvey());
    }    
}
