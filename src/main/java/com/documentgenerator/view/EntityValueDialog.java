/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.view.utils.ImagePanel;
import com.documentgenerator.view.utils.MdlFunctions;
import com.documentgenerator.view.utils.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Suresh
 */
//@Component
public class EntityValueDialog extends JDialog {

    private JFrame frame;
    private final MdlFunctions mdlFunctions = new MdlFunctions();
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public List<String> values;
    private List<String> defaultValues;
    public static JScrollPane jSPItem = new JScrollPane();
    public static JList<String> jtblItem;

    //JButton Variables
    JLabel lblName = new JLabel("Enter the String:");
    JTextField txtName = new JTextField("");
    JButton btnAddNew = new JButton("Add New", WindowUtils.getImageIcon("images/add new.gif"));
    JButton btnRemove = new JButton("Remove", WindowUtils.getImageIcon("images/remove.gif"));
    JButton btnReset = new JButton("Reset", WindowUtils.getImageIcon("images/reset.gif"));
    JButton btnSubmit = new JButton("Submit", WindowUtils.getImageIcon("images/save.gif"));

    public EntityValueDialog(JFrame frame, List<String> values) {
        super(frame, true);
        this.values = values;
        //-- Add the Table
        loadTable();
        //BrrwrsTblJSP.setBounds(5,55,708,323);	

        JPanel northPanel = new ImagePanel(new FlowLayout(), WindowUtils.getImageIcon("images/header.gif").getImage());
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel(new FlowLayout());

        JLabel lblIcon = new JLabel(WindowUtils.getImageIcon("images/ListBarrowers.gif"));

        JLabel lblCaption = new JLabel("NOTE: This form contains all information about the Items.");
        mdlFunctions.setCaptionLabel(lblCaption);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        mdlFunctions.setJButton(btnAddNew, "add", "Add New");
        btnAddNew.setMnemonic(KeyEvent.VK_A);
        btnAddNew.addActionListener(actionListener);

        mdlFunctions.setJTextField(txtName);

        mdlFunctions.setJLabel(lblName);

        mdlFunctions.setJButton(btnRemove, "remove", "Remove");
        btnRemove.setMnemonic(KeyEvent.VK_R);
        btnRemove.addActionListener(actionListener);

        mdlFunctions.setJButton(btnReset, "reset", "Reset");
        btnReset.setMnemonic(KeyEvent.VK_R);
        btnReset.addActionListener(actionListener);

        mdlFunctions.setJButton(btnSubmit, "submit", "Submit");
        btnSubmit.setMnemonic(KeyEvent.VK_C);
        btnSubmit.addActionListener(actionListener);
        //Add Icon
        //add(lblIcon);
        //Add Label
        northPanel.add(lblIcon);
        northPanel.add(lblCaption);

        centerPanel.add(jSPItem);

        southPanel.setBackground(Color.WHITE);

        JPanel south1 = new JPanel();
        south1.setBackground(Color.WHITE);
        JPanel south2 = new JPanel();
        south2.setBackground(Color.WHITE);

        south1.add(lblName);
        south1.add(txtName);
        south1.add(btnAddNew);

        south2.add(btnRemove);
        south2.add(btnReset);
        south2.add(btnSubmit);
        southPanel.setLayout(new BorderLayout());
        southPanel.add(south1, BorderLayout.NORTH);
        southPanel.add(south2, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel jpnlMain = new JPanel();
        jpnlMain.setLayout(new BorderLayout());
        jpnlMain.setBackground(Color.WHITE);
        jpnlMain.add(northPanel, BorderLayout.PAGE_START);
        jpnlMain.add(centerPanel, BorderLayout.CENTER);
        jpnlMain.add(southPanel, BorderLayout.SOUTH);

        getContentPane().add(jpnlMain);

        pack();
        setLocation((screen.width - 325) / 2, ((screen.height - 335) / 2));

        setResizable(false);
        setVisible(true);
    }

    private void loadTable() {

        try {
            if (values == null) {
                values = new ArrayList<>();
            } else {
                defaultValues = new ArrayList<>(values);
            }

            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String s : values) {
                listModel.addElement(s);
            }
            jtblItem = new JList<>();
            jtblItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtblItem.setModel(listModel);

            jSPItem.getViewport().add(jtblItem);
        } catch (Exception ex) {
            Logger.getLogger(EntityValueDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch (command) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case "reset":
                    reset();
                    break;
                case "submit":
                    submit();
                    break;
            }
        }

    };

    private void submit() {
        dispose();
    }

    private void add() {
        try {
            values.add(txtName.getText());
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(EntityValueDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reset() {
        values = defaultValues;
        loadTable();
    }

    private void remove() {
        String selectedRow = jtblItem.getSelectedValue();
        if (selectedRow == null) {
            JOptionPane.showMessageDialog(null,
                    "Please Select any one record", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        values.remove(selectedRow);
        loadTable();
    }

}
