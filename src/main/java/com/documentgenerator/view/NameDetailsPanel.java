/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.form.Entity;
import com.documentgenerator.form.EntityManager;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
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
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.table.TableModel;

/**
 *
 * @author Suresh
 */
//@Component
public class NameDetailsPanel extends JPanel {

    private JFrame frame;

    private List<Entity> nameDetails = getNameDetails();
    private String nameString = "";

    private final MdlFunctions mdlFunctions = new MdlFunctions();
    public static JScrollPane jSPItem = new JScrollPane();
    public static JScrollPane jSPList = new JScrollPane();
    public static JScrollPane jSPMessgae = new JScrollPane();
    public static JTable jtblItem;

    //JButton Variables
    JButton btnAddNew = new JButton("Add New", WindowUtils.getImageIcon("images/add new.gif"));
    JButton btnEdit = new JButton("Edit", WindowUtils.getImageIcon("images/edit.gif"));
    JButton btnRemove = new JButton("Remove", WindowUtils.getImageIcon("images/remove.gif"));
    JButton btnReset = new JButton("Reset", WindowUtils.getImageIcon("images/reset.gif"));
    JButton btnGenerate = new JButton("Generate List", WindowUtils.getImageIcon("images/switch.gif"));
    JButton btnSubmit = new JButton("Submit", WindowUtils.getImageIcon("images/save.gif"));

    JTextArea taMessage = new JTextArea(getText());
    JList<String> listProp = new JList<>();

    public NameDetailsPanel(JFrame frame) {
        this.frame = frame;
        setBackground(Color.WHITE);
        setLayout(null);

        //
        //-- Add the Table
        loadTable();
        loadList();
        JPanel mainPanel = new JPanel();
        JPanel northPanel = new ImagePanel(new FlowLayout(), WindowUtils.getImageIcon("images/header.gif").getImage());
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel(new FlowLayout());
        //southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));        

        JLabel lblIcon = new JLabel(WindowUtils.getImageIcon("images/ListBarrowers.gif"));

        JLabel lblCaption = new JLabel("NOTE: This form contains all information about the Items.");
        mdlFunctions.setCaptionLabel(lblCaption);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

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

        mdlFunctions.setJButton(btnGenerate, "generate", "Generate List");
        btnGenerate.setMnemonic(KeyEvent.VK_G);
        btnGenerate.addActionListener(actionListener);

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
        southPanel.add(btnAddNew);
        southPanel.add(btnEdit);
        southPanel.add(btnRemove);
        southPanel.add(btnReset);
        southPanel.add(btnGenerate);
        southPanel.add(btnSubmit);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(northPanel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //  do something
        jSPMessgae.getViewport().add(taMessage);
        listProp.setDragEnabled(true);

        JPanel mainPanel1 = new JPanel(new BorderLayout());
        mainPanel1.add(jSPMessgae, BorderLayout.CENTER);
        mainPanel1.add(jSPList, BorderLayout.EAST);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        add(mainPanel);
        add(mainPanel1);
        add(southPanel);
    }

    private void loadTable() {

        try {
            List<Entity> list = nameDetails;
            TableModel tableModel = new CollectionResultTableModel<>(list, Entity.class);
            jtblItem = new JTable(null) {
                public boolean isCellEditable(int iRows, int iCols) {
                    return false;
                }
            };
            jtblItem.setAutoCreateRowSorter(true);
            jtblItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtblItem.setModel(tableModel);
            jSPItem.getViewport().add(jtblItem);
        } catch (Exception ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadList() {

        try {

            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Entity entity : nameDetails) {
                listModel.addElement(changeAsVariable(entity.getName()));
            }

            listProp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listProp.setModel(listModel);
            jSPItem.getViewport().add(jtblItem);
            jSPList.getViewport().add(listProp);
        } catch (Exception ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                case "generate":
                    generate();
                    break;
            }
        }
    };

    private void add() {
        try {
            Entity entity = new Entity();
            EntityDialog dialog = new EntityDialog(frame, false, entity);
            if (entity != null && entity.getName() != null || !entity.getName().equals("")) {
                nameDetails.add(entity);
            }
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
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

            Entity entity = nameDetails.get(selectedRow);
            EntityDialog dialog = new EntityDialog(frame, true, entity);
            if (entity.getName() != null || !entity.getName().equals("")) {
                nameDetails.set(selectedRow, entity);
            }
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reset() {
        nameDetails = getNameDetails();
        loadTable();
        loadList();
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
        int columnIndex = tableModel.getColumnIndex("Name");
        String name = (String) jtblItem.getValueAt(selectedRow, columnIndex);
        nameDetails = EntityManager.removeFromList(nameDetails, name);
        loadTable();
    }

    private List<Entity> getNameDetails() {
        List<Entity> entitys = null;
        try {
            entitys = EntityManager.getEntitys("names.csv");
        } catch (IOException ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entitys;
    }

    private String getText() {
        return EntityManager.getText("names.txt");
    }

    private String changeAsVariable(String s) {
        String sb = "#{[VARIABLE]}";
        String result = sb.replace("[VARIABLE]", s.toUpperCase());
        return result;
    }

    private void generate() {
        loadList();
    }

    private void submit() {
        EntityManager.writeEntitys(nameDetails, "names.csv");
        EntityManager.textToFile(taMessage.getText(), "names.txt");
    }
}
