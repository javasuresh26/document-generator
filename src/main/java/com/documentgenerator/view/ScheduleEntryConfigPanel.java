/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.print.DocumentCollector;
import com.documentgenerator.print.EntityPrint;
import com.documentgenerator.view.utils.MdlFunctions;
import com.documentgenerator.view.utils.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Suresh
 */
public class ScheduleEntryConfigPanel extends JPanel {

    private JFrame frame;
    private final MdlFunctions mdlFunctions = new MdlFunctions();
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
    
    ArrayList<SchdeuleEntryPanel> entryPanels = new ArrayList<>();
    JButton btnAddNew = new JButton("Add New", WindowUtils.getImageIcon("images/add new.gif"));
    JButton btnRemove = new JButton("Remove", WindowUtils.getImageIcon("images/remove.gif"));
    JButton btnSubmit = new JButton("Sumbit", WindowUtils.getImageIcon("images/save.gif"));
    SchdeuleEntryPanel entryPanel;
    public ScheduleEntryConfigPanel(JFrame frame) {
        this.frame = frame;

        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel(new FlowLayout());

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        //adding components
        mdlFunctions.setJTabbedPane(tabbedPane);
        entryPanel= new SchdeuleEntryPanel(frame);
        entryPanels.add(entryPanel);
        tabbedPane.addTab("Schedule Details", WindowUtils.getImageIcon("images/ListBarrowers.gif"), entryPanel, "Name Details");

        mdlFunctions.setJButton(btnAddNew, "add", "Add New");
        btnAddNew.setMnemonic(KeyEvent.VK_A);
        btnAddNew.addActionListener(actionListener);

        mdlFunctions.setJButton(btnRemove, "remove", "Remove");
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
        entryPanel= new SchdeuleEntryPanel(frame);
        entryPanels.add(entryPanel);
        tabbedPane.addTab("Schedule Details", WindowUtils.getImageIcon("images/ListBarrowers.gif"), entryPanel, "Schedule Details");
    }

    private void remove() {        
        tabbedPane.remove(tabbedPane.getSelectedIndex());
        entryPanels.remove(tabbedPane.getSelectedIndex());
    }

    private void submit() {
        int count = entryPanels.size();
        ArrayList<EntityPrint> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SchdeuleEntryPanel panel = entryPanels.get(i);
            EntityPrint entity = panel.getEntityPrint();
            entity.setId(i+1);            
            list.add(entity);
        }
        DocumentCollector.setScheduleDetails(list);
        System.out.println(DocumentCollector.getScheduleDetails());
    }
}
