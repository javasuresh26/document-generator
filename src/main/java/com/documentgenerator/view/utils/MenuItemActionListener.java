/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Suresh
 */
public class MenuItemActionListener implements ActionListener {

    private JFrame frame;
    private final String className;
    private Utils utils = new Utils();

    public MenuItemActionListener(JFrame frame, String className) {
        this.frame = frame;
        this.className = className;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            Thread runner;
            runner = new Thread() {
                public void run() {
                    System.out.println("hello"+ className);
                    JDialog dialog = (JDialog) utils.createInstanceAs(className);
                    //JInternalFrame frame = new BaseJInternalFrame();
                    
                   
                        System.out.println("hello"+ className);
                        
                   
                }
            };
            runner.start();
        
    }
}
