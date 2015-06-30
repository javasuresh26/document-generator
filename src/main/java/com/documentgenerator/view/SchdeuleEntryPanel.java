/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.form.Entity;
import com.documentgenerator.form.EntityManager;
import com.documentgenerator.form.EntityType;
import com.documentgenerator.print.EntityPrint;
import com.documentgenerator.view.utils.CustomCellEditor;
import com.documentgenerator.view.utils.MdlFunctions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Suresh
 */
public class SchdeuleEntryPanel extends JPanel {
    
    private final MdlFunctions mdlFunctions = new MdlFunctions();
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel(new FlowLayout());
    
    JLabel lblTitle = new JLabel("Enter the Title:");
    JTextField txtTitle = new JTextField("");
    
    public JScrollPane jSPEntry = new JScrollPane();
    public JTable tbleEntry;
    
    public SchdeuleEntryPanel(JFrame frame) {
        
        loadTable();
        northPanel.setBackground(Color.WHITE);
        
        mdlFunctions.setJTextField(txtTitle);
        mdlFunctions.setJLabel(lblTitle);
        northPanel.add(lblTitle);
        northPanel.add(txtTitle);
        
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(BorderLayout.CENTER, jSPEntry);
        tbleEntry.setRowHeight(25);
        //tbleEntry.addMouseListener(mouseListener);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
    }
    
    private void loadTable() {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{},
                new Object[]{
                    "Label", "Value"
                });
        List<Entity> entitys = getNameDetails();
        String value = "";
        for (Entity entity : entitys) {
            
            if (entity.getType() == EntityType.TEXT_FILED) {
                value = "";
            } else {
                List<String> enityValues = entity.getEnityValues();
                if (enityValues == null || enityValues.isEmpty()) {
                    value = "";
                } else {
                    value = enityValues.get(0);
                }
                
            }
            
            tableModel.addRow(new Object[]{
                entity.getName(), value
            });
        }
        
        tbleEntry = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int iRows, int iCols) {
                if (iCols > 0) {
                    return true;
                }
                return false;
            }
        };
        TableColumn col = tbleEntry.getColumnModel().getColumn(1);
        col.setCellEditor(new CustomCellEditor(entitys));
        //tbleEntry.addMouseListener(new CustomCellEditor(entitys));
        tbleEntry.setAutoCreateRowSorter(true);
        jSPEntry.getViewport().add(tbleEntry);
    }
    
    private List<Entity> getNameDetails() {
        List<Entity> entitys = null;
        try {
            entitys = EntityManager.getEntitys("schedule.csv");
        } catch (IOException ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entitys;
    }
    
    private Map<String, String> getList() {
        tbleEntry.getSelectionModel().clearSelection();
        Map<String, String> map = new HashMap<>();
        TableModel model = tbleEntry.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            map.put(model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString());
        }
        return map;
    }

    public EntityPrint getEntityPrint() {
        String text = EntityManager.getText("schedule.txt");
        EntityPrint entity = new EntityPrint();
        entity.setTitle(txtTitle.getText());
        entity.setValue(generateText(getList(), text));
        return entity;
    }
    
    private String generateText(Map<String, String> list, String text) {
        Set<Map.Entry<String, String>> ss = list.entrySet();
        for (Map.Entry<String, String> entry : ss) {
            text = text.replace(changeAsVariable(entry.getKey()), entry.getValue());
        }
        return text;
    }

    private String changeAsVariable(String s) {
        String sb = "#{[VARIABLE]}";
        String result = sb.replace("[VARIABLE]", s.toUpperCase());
        return result;
    }
    
    MouseListener mouseListener = new MouseListener() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("mouseexit");
        }
    };
}
