/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.models;

import com.aradnab.code_gen.Comman.Helper;
import com.mysql.cj.MysqlType;

/**
 *
 * @author kanishka
 */
public class Column {
    private String sqlName;
    private MysqlType sqlDataType;
    private String defaultValue;
    private boolean isPrimaryKey;
    private boolean isNull;
    private boolean isAutoIncrement;

    public Column() {
    }
    
    public Column(String sqlName, MysqlType sqlDataType, String defaultValue, boolean isNull, boolean isAutoIncrement) {
        this.sqlName = sqlName;
        this.sqlDataType = sqlDataType;
        this.defaultValue = defaultValue;
        this.isNull = isNull;
        this.isAutoIncrement = isAutoIncrement;
    }
    
    public Column(String sqlName, MysqlType sqlDataType, String defaultValue, boolean isPrimaryKey, boolean isNull, boolean isAutoIncrement) {
        this.sqlName = sqlName;
        this.sqlDataType = sqlDataType;
        this.defaultValue = defaultValue;
        this.isPrimaryKey = isPrimaryKey;
        this.isNull = isNull;
        this.isAutoIncrement = isAutoIncrement;
    }

    public boolean isIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public MysqlType getSqlDataType() {
        return sqlDataType;
    }

    public void setSqlDataType(MysqlType sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public boolean isIsNull() {
        return isNull;
    }

    public void setIsNull(boolean isNull) {
        this.isNull = isNull;
    }

    @Override
    public String toString() {
        return "Column{" + "sqlName=" + sqlName + ", sqlDataType=" + sqlDataType + ", defaultValue=" + defaultValue + ", isPrimaryKey=" + isPrimaryKey + ", isNull=" + isNull + ", isAutoIncrement=" + isAutoIncrement + '}';
    }
    
    public String getColumnNameInCamelCase(){
       return Helper.defaultHelper.snakeCaseToCamelCase(this.sqlName);
    }
    
    public String getColumnNameAsPHPVariable(){
       return "$"+this.getColumnNameInCamelCase();
    }
    public String getColumnHtmlFieldType(){
        return this.sqlName.split("_")[2];
    }
    
    public String getColumnHtmlSection(){
        return this.sqlName.split("_")[0];
    }
    
}
