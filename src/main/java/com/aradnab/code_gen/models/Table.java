/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kanishka
 */
public class Table {
    
    private String initName;
    private List<Column> columns = new ArrayList<>();

    public Table() {
    }

    public Table(String initName, List<Column> columns) {
        this.initName = initName;
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }
    
    public void addColumn(Column column){
        this.columns.add(column);
    }

    @Override
    public String toString() {
        return "Table{" + "initName=" + initName + ", columns=" + columns + '}';
    }
    
}
