/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.view.utils.MdlFunctions;
import com.documentgenerator.view.utils.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Suresh
 */
public class ConfigPanel extends JPanel{
    private JFrame frame;
    private final MdlFunctions mdlFunctions = new MdlFunctions();
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    

    public ConfigPanel(JFrame frame) {
        this.frame = frame;
        
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel(new FlowLayout());

       
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        
        //adding components
        mdlFunctions.setJTabbedPane(tabbedPane);
        tabbedPane.addTab("Name Details", WindowUtils.getImageIcon("images/ListBarrowers.gif"), new NameDetailsPanel(frame), "Name Details");        
        tabbedPane.addTab("Schedule Properties", WindowUtils.getImageIcon("images/ListBarrowers.gif"), new ScheduleDetailsPanel(frame), "Schedule Properties");        
        
        centerPanel.add(tabbedPane);

        southPanel.setBackground(Color.WHITE);
     

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);       
        add(centerPanel, BorderLayout.CENTER);
    }

    
}
