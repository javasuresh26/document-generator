/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suresh
 */
public class CollectionResultTableModel<T> extends AbstractTableModel {

    private Class<T> entityClass;
    private List<T> collections;
    private Map<String, Class> columnNamesWithClass;
    private ArrayList<String> columnNames;
    int noOfColmn;

    public CollectionResultTableModel(List<T> collection, Class<T> entityClass) {
        this.entityClass = entityClass;
        this.collections = collection;
        loadDatas();
    }

    @Override
    public int getRowCount() {
        return collections.size();
    }

    @Override
    public int getColumnCount() {
        return noOfColmn;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        Class noparams[] = {};
        T obj = collections.get(rowIndex);
        try {
            Method method = entityClass.getDeclaredMethod(
                    getMethodName(columnNames.get(columnIndex), getColumnClass(columnIndex)),
                    null);
            result = method.invoke(obj,null);
        } catch (Exception ex) {
            Logger.getLogger(CollectionResultTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnNamesWithClass.get(columnNames.get(columnIndex));
    }

    private void loadDatas() {
        loadCoulmnName();
        fireTableStructureChanged();
    }

    private void loadCoulmnName() {
        columnNamesWithClass = new LinkedHashMap<>();
        columnNames = new ArrayList<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field f : fields) {
            columnNames.add(getFieldString(f.getName()));
            columnNamesWithClass.put(getFieldString(f.getName()), f.getType());
        }
        noOfColmn = columnNames.size();
    }

    private String getFieldString(String name) {
        String[] r = name.split("(?=\\p{Lu})");
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < r.length; i++) {
            if (i == 0) {
                result.append(Character.toUpperCase(r[i].charAt(0)));
                result.append(r[i].substring(1));
            } else {
                result.append(r[i]);
            }
        }
        return result.toString();
    }

    private String getMethodName(String postMethodName, Class classType) {
        StringBuilder methodName = new StringBuilder();
        if (classType.equals(Boolean.TYPE)) {
            methodName.append("is");
        } else {
            methodName.append("get");
        }
        methodName.append(postMethodName);
        return methodName.toString();
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public int getColumnIndex(String columnName) {
       return columnName.indexOf(columnName);
    }
    
}
