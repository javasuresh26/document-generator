/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author Suresh
 */
public class WindowUtils {

    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static final String DOCUMENT_PATH =  getDocumentPapth();
    public static final String DG_PATH =  getDocumentPapth().concat("\\DocumentGenerator");
    public Dimension getScreen() {
        return screen;
    }

    public static ImageIcon getImageIcon(String image) {
        return new ImageIcon(ClassLoader.getSystemResource(image));
    }

    public static String getFile(String fileName) {
        return ClassLoader.getSystemResource(fileName).getFile();
    }

    public static String getDocumentPapth() {
        String myDocuments = null;

        try {
            Process p = Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            p.waitFor();

            byte[] b;
            try (InputStream in = p.getInputStream()) {
                b = new byte[in.available()];
                in.read(b);
            }
            
            myDocuments = new String(b);
            myDocuments = myDocuments.split("\\s\\s+")[4];

        } catch (IOException | InterruptedException t) {
            t.printStackTrace();
            myDocuments = "/";
        }

       
        return myDocuments;
    }
}
