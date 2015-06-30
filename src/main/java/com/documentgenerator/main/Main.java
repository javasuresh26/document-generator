/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.main;

import com.documentgenerator.view.MainWindow;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suresh
 */
public class Main {

    public static void main(String args[]) throws Exception {

//         try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow mainWindow = new MainWindow();
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

}
