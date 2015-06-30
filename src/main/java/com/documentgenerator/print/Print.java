/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.print;

import com.documentgenerator.form.EntityManager;
import com.documentgenerator.view.utils.WindowUtils;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Suresh
 */
public class Print {

    public static void generate(String path) throws Exception {
//    public static void main(String path[]) throws Exception {
//        String text = EntityManager.getText("names.txt");
//        EntityPrint entityPrint = new EntityPrint(text);
//        entityPrint.setTitle("Item No.1:");
//        ArrayList<EntityPrint> entityPrints = new ArrayList<>();
//        entityPrints.add(entityPrint);
//        entityPrints.add(entityPrint);
//        DocumentCollector.setNameDetails(entityPrints);
//        DocumentCollector.setScheduleDetails(entityPrints);
        
        String file = WindowUtils.getFile("jasper/main.jrxml");
        
        
        System.out.println(file);
        System.out.println(DocumentCollector.getNameDetails());
        System.out.println(DocumentCollector.getScheduleDetails());
        System.out.println(DocumentCollector.getTabelDetails());
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(DocumentCollector.getNameDetails());
        JasperDesign jasperDesign = JRXmlLoader.load(file);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        //subreport
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name_details_ds", new JRBeanCollectionDataSource(DocumentCollector.getNameDetails()));
        parameters.put("schedule_details_ds", new JRBeanCollectionDataSource(DocumentCollector.getScheduleDetails()));
        parameters.put("table_details_ds", new JRBeanCollectionDataSource(DocumentCollector.getTabelDetails()));
        
       
        
        
        parameters.put("SUBREPORT_DIR", WindowUtils.getFile("jasper/"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        //JasperExportManager.exportReportToPdfFile(jasperPrint, "c:/temp/dg/test_jasper.pdf");
        try (FileOutputStream outputStream = new FileOutputStream(WindowUtils.getDocumentPapth()+"\\dg.rtf")) {
            final JRRtfExporter rtfExporter = new JRRtfExporter();
            rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            rtfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            rtfExporter.exportReport();
        }
    }

}
