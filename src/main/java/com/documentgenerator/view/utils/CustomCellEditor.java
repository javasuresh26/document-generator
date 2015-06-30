/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view.utils;

import com.documentgenerator.form.Entity;
import com.documentgenerator.form.EntityType;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Suresh
 */
public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

    List<Entity> entitys = new ArrayList<>();
    JComboBox component = new JComboBox();
    JTextField component1 = new JTextField("");
    EntityType entityType;

    public CustomCellEditor(List<Entity> entitys) {
        this.entitys.addAll(entitys);
    }

    @Override
    public Object getCellEditorValue() {
        if (entityType != EntityType.DROPDOWN_FILED) {
            return component1.getText();
        }
        return component.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        Entity entity = entitys.get(row);
        entityType = entity.getType();
        if (entity.getType() != EntityType.DROPDOWN_FILED) {
            component1.setText(value.toString());
            return component1;
        }
        return component = getEntityComboBox(entity.getEnityValues(), value.toString());
    }

    private JComboBox<String> getEntityComboBox(List<String> list, String value) {
        list = list == null ? (new ArrayList<String>()) : list;
        JComboBox<String> comboBox = new JComboBox<>();
        for (String s : list) {
            comboBox.addItem(s);
        }
        comboBox.setSelectedItem(value);
        return comboBox;
    }

}
