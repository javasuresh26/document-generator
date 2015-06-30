/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Suresh
 */
public class ImagePanel extends JPanel {
    /*the default image to use*/

    private Image image;

    public ImagePanel() {
        super();
    }

    public ImagePanel(LayoutManager layout, Image image) {
        super(layout);
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    
    public void paintComponent(Graphics g) {
        /*Draw image on the panel*/
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
