/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.print;

import java.util.List;

/**
 *
 * @author Suresh
 */
public class DocumentCollector {
    private static List<EntityPrint> nameDetails;
    private static List<EntityPrint> scheduleDetails;
    private static List<EntityTablePrint> tabelDetails;

    public static List<EntityPrint> getNameDetails() {
        return nameDetails;
    }

    public static void setNameDetails(List<EntityPrint> nameDetails) {
        DocumentCollector.nameDetails = nameDetails;
    }

    public static List<EntityPrint> getScheduleDetails() {
        return scheduleDetails;
    }

    public static void setScheduleDetails(List<EntityPrint> scheduleDetails) {
        DocumentCollector.scheduleDetails = scheduleDetails;
    }

    public static List<EntityTablePrint> getTabelDetails() {
        return tabelDetails;
    }

    public static void setTabelDetails(List<EntityTablePrint> tabelDetails) {
        DocumentCollector.tabelDetails = tabelDetails;
    }
    
    
    
}
