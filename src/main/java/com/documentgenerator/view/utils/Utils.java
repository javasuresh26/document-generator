/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.KeyStroke;

/**
 *
 * @author Suresh
 */
public class Utils {

    String actionKey;
    String keyValue;
//    Class entityClass;
//
//    public void setEntityClass(Class entityClass) {
//        this.entityClass = entityClass;
//    }

    public <T> T createInstance(Class<T> entityClass) throws Exception {
        T instance = null;
        instance = entityClass.newInstance();
        return instance;
    }
    
    
    public Object createInstanceAs(String entity) {
        try {
            Class entityClass = Class.forName(entity);
            return entityClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex);
        }
        return new Object();
    }

    public KeyStroke getKeyStroke(String key) {
        KeyStroke keyStroke = null;
        String[] keys = key.split("-");
        if (keys.length == 1) {
            return KeyStroke.getKeyStroke(keys[0].toUpperCase());
        } else if (keys.length == 2) {
            actionKey = keys[0].toUpperCase();
            keyValue = keys[1].toUpperCase();
            switch (actionKey) {
                case "CTRL":
                    keyStroke = KeyStroke.getKeyStroke(getASCIIValue(keyValue), ActionEvent.CTRL_MASK);
                    break;
                case "ALT":
                    keyStroke = KeyStroke.getKeyStroke(getASCIIValue(keyValue), ActionEvent.ALT_MASK);
                    break;
                case "SHIFT":
                    keyStroke = KeyStroke.getKeyStroke(getASCIIValue(keyValue), ActionEvent.SHIFT_MASK);
                    break;
            }
        }
        return keyStroke;
    }

    public int getASCIIValue(String str) {
        //System.out.println((int) Character.toUpperCase(str.charAt(0)));
        return (int) Character.toUpperCase(str.charAt(0));
    }
    
    public static String getDoubleValueAsMoney(double value){
        DecimalFormat format= new DecimalFormat("######.##");
        format.setMinimumFractionDigits(2);
        return format.format(value);
    } 
    public String getFieldString(String name) {
        String[] r = name.split("(?=\\p{Lu})");
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < r.length; i++) {
            if (i == 0) {
                result.append(Character.toUpperCase(r[i].charAt(0)));
                result.append(r[i].substring(1));
            } else {
                result.append(" ").append(r[i]);
            }
        }
        return result.toString();
    }
}
