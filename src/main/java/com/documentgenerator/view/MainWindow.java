/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.view;

import com.documentgenerator.print.DocumentCollector;
import com.documentgenerator.print.Print;
import com.google.common.base.Strings;
import com.documentgenerator.view.utils.MdlFunctions;

import com.documentgenerator.view.utils.WindowUtils;

import com.documentgenerator.view.utils.MenuItemActionListener;
import com.documentgenerator.view.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Suresh
 */
//@Qualifier(value = "mainWindow")
public class MainWindow extends JFrame {

    //Frame Components
    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
    private Container container;
    private JDesktopPane desktop = new JDesktopPane();
    private final WindowUtils windowUtils = new WindowUtils();
    private final Dimension dimension = windowUtils.getScreen();
    private MdlFunctions mdlFunctions = new MdlFunctions();
    private StatusBarPanel statusBarPanel;
    private JSONParser parser = new JSONParser();
    private JSONArray jsonObj;
    private JMenuBar menuBar;
    private Utils utils = new Utils();
    private JToolBar toolBar = new JToolBar();

    private JMenu menu;
    private JMenuItem menuItem;
    private ArrayList<JMenuItem> menuItems;
    private JSONObject jsonMenuItem;

    public MainWindow() {
        initComponents();
        setTitle("PDMS");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setMinimumSize(screen);
        //setMaximumSize(screen);
        //container = getContentPane();
        //container.add("Center", desktop);
        loadJson();
        loadMenuComponents();

        pack();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private void initComponents() {

        statusBarPanel = new StatusBarPanel();
        //JPanel northPanel = new ImagePanel(new FlowLayout(), windowUtils.getImageIcon("images/header.gif").getImage());

        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel(new FlowLayout());

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        //adding components
        mdlFunctions.setJTabbedPane(tabbedPane);
        tabbedPane.addTab("Configuration", WindowUtils.getImageIcon("images/ListBarrowers.gif"), new ConfigPanel(this), "Configuration");
        tabbedPane.addTab("Name Entry", WindowUtils.getImageIcon("images/ListBarrowers.gif"), new NameEntryConfigPanel(this), "Name Entry");
        tabbedPane.addTab("Schedule Entry", WindowUtils.getImageIcon("images/ListBarrowers.gif"), new ScheduleEntryConfigPanel(this), "Schedule Entry");
        tabbedPane.addTab("Document Details", WindowUtils.getImageIcon("images/ListBarrowers.gif"), new DocumentEntryConfigPanel(this), "Document Details");

        //northPanel.add(lblIcon);
        //northPanel.add(lblCaption);
        centerPanel.add(tabbedPane);

        southPanel.setBackground(Color.WHITE);
        southPanel.add(statusBarPanel);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        //add(northPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        menuBar = new JMenuBar();
        container = getContentPane();
        setJMenuBar(menuBar);
    }

    private void loadJson() {

        URL path = ClassLoader.getSystemResource("window.json");
        //System.out.println(path.getAbsolutePath());
        Object obj;
        try {
            obj = parser.parse(new FileReader(path.getFile()));
            jsonObj = (JSONArray) obj;
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        //System.out.println(jsonObj);
    }

    private void loadMenuComponents() {
        int count = jsonObj.size(), i;
        JSONObject jsonMenu;
        JSONObject menuItems;
        for (i = 0; i < count; i++) {
            jsonMenu = (JSONObject) jsonObj.get(i);
            addMenu(jsonMenu);
        }
    }

    private void addMenu(JSONObject jsonMenu) {
        menu = new JMenu((String) jsonMenu.get("menuTitle"));
        menu.setMnemonic(((String) jsonMenu.get("mnemonic")).charAt(0));
        List<JMenuItem> menuItems = getMenuItem((JSONArray) jsonMenu.get("menuItem"));
        for (JMenuItem menuItem : menuItems) {
            menu.add(menuItem);
        }
        menuBar.add(menu);
    }

    private ArrayList<JMenuItem> getMenuItem(JSONArray jsonMenuItems) {
        int count = jsonMenuItems.size(), i;
        menuItems = new ArrayList<>();
        for (i = 0; i < count; i++) {
            jsonMenuItem = (JSONObject) jsonMenuItems.get(i);
            String menuTitle = (String) jsonMenuItem.get("menuTitle");
            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource((String) jsonMenuItem.get("imageIcon")));

            menuItem = new JMenuItem(menuTitle, imageIcon);
            String className = (String) jsonMenuItem.get("className");
            String shortcut = (String) jsonMenuItem.get("shortcut");
            Boolean hasSeparator = (Boolean) jsonMenuItem.get("separator");
            Boolean hasToolbar = (Boolean) jsonMenuItem.get("hasToolbar");

            if (!Strings.isNullOrEmpty(shortcut)) {
                menuItem.setAccelerator(utils.getKeyStroke(shortcut));
            }

            if (className.equals("string")) {
                menuItem.setActionCommand(menuTitle);
                menuItem.addActionListener(actionListener);
            } else {
                menuItem.setActionCommand(className);
                menuItem.addActionListener(new MenuItemActionListener(this, className));
            }
            menu.add(menuItem);

            if (hasToolbar) {
                JButton button = new JButton(imageIcon);
                button.setToolTipText(menuTitle);
                button.addActionListener(new MenuItemActionListener(this, className));
                toolBar.add(button);
            }
        }
        return menuItems;
    }

    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch (command) {
                case "Generate Document":
                    generateReport();
                    break;
                case "Exit":
                    System.exit(0);
                    break;

            }
        }
    };

    public void generateReport() {
        if (DocumentCollector.getNameDetails() == null) {
            JOptionPane.showMessageDialog(null,
                    "Please Submit the Name Details", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (DocumentCollector.getScheduleDetails() == null) {
            JOptionPane.showMessageDialog(null,
                    "Please Submit the Schedule Details", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (DocumentCollector.getTabelDetails() == null) {
            JOptionPane.showMessageDialog(null,
                    "Please Submit the Document Table Details", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String path = WindowUtils.getDocumentPapth() +"\\dg.rtf";
        try {
            Print.generate(path);
        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    ex, "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
