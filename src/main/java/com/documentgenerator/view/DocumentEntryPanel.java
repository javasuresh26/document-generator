/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.form.Entity;
import com.documentgenerator.form.EntityManager;
import com.documentgenerator.print.EntityTablePrint;
import com.documentgenerator.view.utils.CollectionResultTableModel;
import com.documentgenerator.view.utils.ImagePanel;
import com.documentgenerator.view.utils.MdlFunctions;
import com.documentgenerator.view.utils.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Suresh
 */
//@Component
public class DocumentEntryPanel extends JPanel {

    private JFrame frame;

    private List<EntityTablePrint> documentDetails = new ArrayList<>();

    private final MdlFunctions mdlFunctions = new MdlFunctions();
    public static JScrollPane jSPItem = new JScrollPane();
    public static JTable jtblItem;
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel(new FlowLayout());

    JLabel lblTitle = new JLabel("Enter the Title:");
    JTextField txtTitle = new JTextField("");

    //JButton Variables
    JButton btnAddNew = new JButton("Add New", WindowUtils.getImageIcon("images/add new.gif"));
    JButton btnEdit = new JButton("Edit", WindowUtils.getImageIcon("images/edit.gif"));
    JButton btnRemove = new JButton("Remove", WindowUtils.getImageIcon("images/remove.gif"));
    JButton btnReset = new JButton("Reset", WindowUtils.getImageIcon("images/reset.gif"));
    JButton btnSubmit = new JButton("Submit", WindowUtils.getImageIcon("images/save.gif"));

    public DocumentEntryPanel(JFrame frame) {
        this.frame = frame;   
        //
        //-- Add the Table
        loadTable();

        
        JLabel lblCaption = new JLabel("NOTE: This form contains all information about the Items.");
        mdlFunctions.setCaptionLabel(lblCaption);

        northPanel.setBackground(Color.WHITE);
        mdlFunctions.setJTextField(txtTitle);
        mdlFunctions.setJLabel(lblTitle);
        northPanel.add(lblTitle);
        northPanel.add(txtTitle);

        
       
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(BorderLayout.CENTER, jSPItem);
        
        mdlFunctions.setJButton(btnAddNew, "add", "Add New");
        btnAddNew.setMnemonic(KeyEvent.VK_A);
        btnAddNew.addActionListener(actionListener);

        mdlFunctions.setJButton(btnEdit, "edit", "Edit");
        btnEdit.setMnemonic(KeyEvent.VK_E);
        btnEdit.addActionListener(actionListener);

        mdlFunctions.setJButton(btnRemove, "remove", "Remove");
        btnRemove.setMnemonic(KeyEvent.VK_R);
        btnRemove.addActionListener(actionListener);

        mdlFunctions.setJButton(btnReset, "reset", "Reset");
        btnReset.setMnemonic(KeyEvent.VK_R);
        btnReset.addActionListener(actionListener);

        mdlFunctions.setJButton(btnSubmit, "submit", "Submit");
        btnSubmit.setMnemonic(KeyEvent.VK_C);
        btnSubmit.addActionListener(actionListener);
      
        

        southPanel.setBackground(Color.WHITE);
        southPanel.add(btnAddNew);
        southPanel.add(btnEdit);
        southPanel.add(btnRemove);
        southPanel.add(btnReset);
        //southPanel.add(btnSubmit);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(northPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void loadTable() {

        try {
            List<EntityTablePrint> list = documentDetails;
            TableModel tableModel = new CollectionResultTableModel<>(list, EntityTablePrint.class);
            jtblItem = new JTable();
            jtblItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtblItem.setModel(tableModel);
            jSPItem.getViewport().add(jtblItem);
            hideCloummn("Id");
            hideCloummn("Title");
        } catch (Exception ex) {
            Logger.getLogger(DocumentEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                case "edit":
                    edit();
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

    private void add() {
        try {
            EntityTablePrint entity = new EntityTablePrint();
            DocumentEntityDialog dialog = new DocumentEntityDialog(frame, false, entity);
            if (entity != null) {
                documentDetails.add(entity);
            }
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(DocumentEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void edit() {
        try {
            CollectionResultTableModel tableModel = (CollectionResultTableModel) jtblItem.getModel();

            int selectedRow = jtblItem.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null,
                        "Please Select any one record", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            EntityTablePrint entity = documentDetails.get(selectedRow);
            DocumentEntityDialog dialog = new DocumentEntityDialog(frame, true, entity);
            if (entity != null) {
                documentDetails.set(selectedRow, entity);
            }
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(DocumentEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reset() {
        documentDetails = new ArrayList<>();
        loadTable();
    }

    private void remove() {
        CollectionResultTableModel tableModel = (CollectionResultTableModel) jtblItem.getModel();

        int selectedRow = jtblItem.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null,
                    "Please Select any one record", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        documentDetails.remove(selectedRow);
        loadTable();
    }

    private void submit() {

    }

    private void hideCloummn(String property) {
        jtblItem.getColumn(property).setWidth(0);
        jtblItem.getColumn(property).setMinWidth(0);
        jtblItem.getColumn(property).setMaxWidth(0);
    }

    List<EntityTablePrint> getEntityTablePrints() {
        for(int i=0;i<documentDetails.size();i++){
            documentDetails.get(i).setTitle(txtTitle.getText());
            documentDetails.set(i,documentDetails.get(i));
        }
        return documentDetails;
    }
}
