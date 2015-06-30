/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;
import com.documentgenerator.view.utils.MdlFunctions;
import javax.swing.*;
import java.awt.*;

public class StatusBarPanel extends JPanel {	
	private static JLabel statusBar = new JLabel("  ");
        private MdlFunctions mdlFunctions = new MdlFunctions();
	
	public StatusBarPanel() {
                setLayout(new BorderLayout());
                setBackground(Color.WHITE);
		this.add(mdlFunctions.setJLabel(statusBar),BorderLayout.WEST);
		//this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
	}
        public static void updateStatus(String status){
            statusBar.setText(status);
        }
}