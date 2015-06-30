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
public class EntityPrint {

    private int id;
    private String title;
    private String value;

    public EntityPrint() {
    }

    public EntityPrint(String value) {
        this.value = value;
    }

    public int getId() {
        id += 1;
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EntityPrint{" + "id=" + id + ", title=" + title + ", value=" + value + '}';
    }

}
