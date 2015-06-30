/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documentgenerator.print;

/**
 *
 * @author Suresh
 */
public class EntityTablePrint {

    private int id;
    private String title;
    private String date;
    private String document;
    private String survey ;

    public EntityTablePrint() {
    }

    public EntityTablePrint(int id, String title, String date, String document, String survey) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.document = document;
        this.survey = survey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EntityTablePrint{" + "id=" + id + ", title=" + title + ", date=" + date + ", document=" + document + ", survey=" + survey + '}';
    }

}
