package com.documentgenerator.form;

import com.documentgenerator.view.NameDetailsPanel;
import com.documentgenerator.view.utils.WindowUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Suresh
 */
public class EntityManager {

    public static List<Entity> getEntitys(String fileLocation) throws IOException {
        //System.out.println(WindowUtils.DG_PATH + "\\" + fileLocation);
        File file = new File(WindowUtils.DG_PATH + "\\" + fileLocation);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            file.createNewFile();
        }
        List<Entity> entitys = new ArrayList<>();
        Entity entity = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] entityString = line.split(",");
                entity = new Entity();
                entity.setName(entityString[0]);
                entity.setType(EntityType.valueOf(entityString[1]));
                if (EntityType.TEXT_FILED != entity.getType()) {
                    String[] entityValues = entityString[2].split(";");
                    ArrayList<String> list = new ArrayList<>();
                    list.addAll(Arrays.asList(entityValues));
                    entity.setEnityValues(list);
                }
                entitys.add(entity);
            }
        }
        return entitys;
    }

    public static void writeEntitys(List<Entity> entitys, String fileName) {
        try {
            File file = new File(WindowUtils.DG_PATH + "\\" + fileName);
            // if file doesnt exists, then create it 
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (Entity entity : entitys) {
                    StringBuilder entityValueString = new StringBuilder("");
                    String line = entity.getName() + "," + entity.getType();
                    List<String> enityValues = entity.getEnityValues();
                    if (enityValues != null && enityValues.size() > 0) {
                        for (int i = 0; i < enityValues.size(); i++) {
                            if (i == (enityValues.size() - 1)) {
                                entityValueString.append(enityValues.get(i));
                            } else {
                                entityValueString.append(enityValues.get(i)).append(";");
                            }

                        }
                    }
                    bw.write(line + "," + entityValueString + "\n");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Entity> removeFromList(List<Entity> nameDetails, String name) {
        int i = 0, count = nameDetails.size();
        for (; i < count; i++) {
            if (nameDetails.get(i).getName().equals(name)) {
                nameDetails.remove(i);
                return nameDetails;
            }
        }
        return nameDetails;
    }

    public static boolean checkEntityExist(List<Entity> nameDetails, String name) {
        int i = 0, count = nameDetails.size();
        for (; i < count; i++) {
            if (nameDetails.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String getText(String fileName) {
        StringBuilder text = new StringBuilder("");
        BufferedReader br = null;
        String sCurrentLine;
        File file = new File(WindowUtils.DG_PATH + "\\" + fileName);

        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.mkdirs();
                }
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file));
            while ((sCurrentLine = br.readLine()) != null) {
                text.append(sCurrentLine);
                text.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(NameDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return text.toString();
    }

    public static void textToFile(String text, String fileName) {
        try {
            File file = new File(WindowUtils.DG_PATH + "\\" + fileName);
            // if file doesnt exists, then create it 
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(text);
            }
        } catch (IOException ex) {
            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
