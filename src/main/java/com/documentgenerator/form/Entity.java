package com.documentgenerator.form;


import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suresh
 */
public class Entity {
    private String name;
    private EntityType type;
    private List<String> enityValues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public List<String> getEnityValues() {
        return enityValues;
    }

    public void setEnityValues(List<String> enityValues) {
        this.enityValues = enityValues;
    }

    @Override
    public String toString() {
        return "Entity{" + "name=" + name + ", type=" + type + ", enityValues=" + enityValues + '}';
    }
    
    
}
