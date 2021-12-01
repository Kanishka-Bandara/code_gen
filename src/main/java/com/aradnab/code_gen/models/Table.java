/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.models;

import com.aradnab.code_gen.Comman.Helper;
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
        columns = new ArrayList<>();
    }

    public Table(String initName) {
        this();
        this.initName = initName;
    }
    
    public Table(String initName, List<Column> columns) {
        this(initName);
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
    
    public String getCreateStatement(){
        String query = "INSERT INTO `"+this.initName+"` (";
        String queryFields = "";
        for (Column column : columns) {
            if (!column.getSqlName().equals("id")) {
                query+=column.getSqlName()+", ";
                queryFields+="";
            }
        }
        //Remove last ,
        query = query.trim();
        query = query.substring(0, query.length()-1);
        query+=") VALUES (";
        for (Column column : columns) {
            if (!column.getSqlName().equals("id")) {
                query+=":"+column.getSqlName()+", ";
            }
        }
        //Remove last ,
        query = query.trim();
        query = query.substring(0, query.length()-1);
        query+=");";
        return query;
    }
     public String getUpdateStatement(){
        String query = "UPDATE `"+this.initName+"` SET ";
        for (Column column : columns) {
            if (!(column.getSqlName().equals("id") || column.getSqlName().equals("created_at"))) {
                query+="`"+column.getSqlName()+"` = :"+column.getSqlName()+",";
            }
        }
        //Remove last ,
        query = query.trim();
        query = query.substring(0, query.length()-1);
        query+=" WHERE `id` = :id;";
        return query;
    }
    
    public String getNameInCamelCase(){
        return Helper.defaultHelper.snakeCaseToCamelCase(this.initName);
    }
    
    public String getCreateControllerFileName(){
        return "register"+Helper.defaultHelper.snakeCaseToCamelCase(this.initName)+".php";
    }
    
    public String getListFileName(){
        return Helper.defaultHelper.snakeCaseToCamelCase(this.initName)+"-list.php";
    }
    
    public String getDeleteControllerFileName(){
        return "delete"+Helper.defaultHelper.snakeCaseToCamelCase(this.initName)+".php";
    }
    
}
