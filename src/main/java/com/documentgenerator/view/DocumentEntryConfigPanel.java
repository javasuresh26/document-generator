/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.print.DocumentCollector;
import com.documentgenerator.print.EntityPrint;
import com.documentgenerator.print.EntityTablePrint;
import com.documentgenerator.view.utils.MdlFunctions;
import com.documentgenerator.view.utils.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Suresh
 */
public class DocumentEntryConfigPanel extends JPanel {

    private JFrame frame;
    private final MdlFunctions mdlFunctions = new MdlFunctions();
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
    ArrayList<EntityTablePrint> list = new ArrayList<>();
    ArrayList<DocumentEntryPanel> entryPanels = new ArrayList<>();
    JButton btnAddNew = new JButton("Add New Window", WindowUtils.getImageIcon("images/add new.gif"));
    JButton btnRemove = new JButton("Remove Window", WindowUtils.getImageIcon("images/remove.gif"));
    JButton btnSubmit = new JButton("Sumbit", WindowUtils.getImageIcon("images/save.gif"));
    public DocumentEntryConfigPanel(JFrame frame) {
        this.frame = frame;

        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel(new FlowLayout());

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        //adding components
        mdlFunctions.setJTabbedPane(tabbedPane);
        DocumentEntryPanel entryPanel = new DocumentEntryPanel(frame);
        entryPanels.add(entryPanel);
        tabbedPane.addTab("Document Details", WindowUtils.getImageIcon("images/ListBarrowers.gif"), entryPanel, "Document Details");

        mdlFunctions.setJButton(btnAddNew, "add", "Add New Window");
        btnAddNew.setMnemonic(KeyEvent.VK_A);
        btnAddNew.addActionListener(actionListener);

        mdlFunctions.setJButton(btnRemove, "remove", "Remove Window");
        btnRemove.setMnemonic(KeyEvent.VK_R);
        btnRemove.addActionListener(actionListener);

        mdlFunctions.setJButton(btnSubmit, "submit", "Submit");
        btnSubmit.setMnemonic(KeyEvent.VK_S);
        btnSubmit.addActionListener(actionListener);

        centerPanel.add(tabbedPane);

        southPanel.setBackground(Color.WHITE);
        southPanel.add(btnAddNew);
        southPanel.add(btnRemove);
        southPanel.add(btnSubmit);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

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
                case "submit":
                    submit();
                    break;

            }
        }

    };

    private void add() {
        DocumentEntryPanel entryPanel= new DocumentEntryPanel(frame);
        entryPanels.add(entryPanel);
        tabbedPane.addTab("Document Details", WindowUtils.getImageIcon("images/ListBarrowers.gif"), entryPanel, "Document Details");
}

    private void remove() {        
        tabbedPane.remove(tabbedPane.getSelectedIndex());
        entryPanels.remove(tabbedPane.getSelectedIndex());
    }


    
    private void submit() {
        int count = entryPanels.size();
        
        for (int i = 0; i < count; i++) {
            DocumentEntryPanel entryPanel = entryPanels.get(i);
            List<EntityTablePrint> entitys = entryPanel.getEntityTablePrints();
            for(int j=0;j<entitys.size();j++){
                EntityTablePrint print = entitys.get(j);
                print.setId(i+1);
                entitys.set(j, print);
            }            
            list.addAll(entitys);
        }
        DocumentCollector.setTabelDetails(list);
        System.out.println(DocumentCollector.getTabelDetails());
    }
}
