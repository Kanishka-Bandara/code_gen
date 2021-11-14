/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.models;

import java.util.List;

/**
 *
 * @author kanishka
 */
public class Database {
    private String sqlName;
    private List<Table> tables;

    public Database(String dbNameInit, List<Table> tables) {
        this.sqlName = dbNameInit;
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    @Override
    public String toString() {
        return "Database{" + "dbNameInit=" + sqlName + ", tables=" + tables + '}';
    }    
    
}
