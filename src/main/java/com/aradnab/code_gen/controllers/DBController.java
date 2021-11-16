/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.DB;
import com.aradnab.code_gen.models.Database;
import com.mysql.cj.MysqlType;
import com.mysql.cj.result.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 *
 * @author kanishka
 */
public class DBController {
    public static DBController defaultController = new DBController();
    
    public Database getDatabaseDetails() throws Exception{
      List<String> rs = DB.getTables();
      int count = 0;
        for (String tbl : rs) {
            System.out.println("-----------------------");
            System.out.println("Table - "+tbl);
            System.out.println("-----Columns----");
          ResultSet rsColumns = DB.getColumnDetails(tbl);
            ResultSetMetaData metaData = rsColumns.getMetaData();
//            Field[] columns = metaData.getFields();
//            for (int i = 0; i < columns.length; i++) {
//                String columnFullName = columns[i].getFullName();
//                String columnOriginalName = columns[i].getOriginalName();
//                String columnOriginalTableName = columns[i].getOriginalTableName();
//                String columnName = columns[i].getName();
//                String columnLabel = columns[i].getColumnLabel();
//                String columnTableName = columns[i].getTableName();
//                MysqlType columnMysqlDataType = columns[i].getMysqlType();
//                boolean columnAutoIncrement = columns[i].isAutoIncrement();
//                boolean columnNotNull = columns[i].isNotNull();
//                boolean columnIsPrimaryKey = columns[i].isPrimaryKey();
//                System.out.println("==========");
//                System.out.println("columnTableName = "+columnTableName);
//                System.out.println("Name = "+columnName);
//                System.out.println("columnFullName = "+columnFullName);
//                System.out.println("columnOriginalName = "+columnOriginalName);
//                System.out.println("columnOriginalTableName = "+columnOriginalTableName);
//                System.out.println("Label = "+columnLabel);
//                System.out.println("Type = "+columnMysqlDataType);
////                System.out.println("Default = "+columnDefaultValue);
//                System.out.println("Is Null = "+columnNotNull);
//                System.out.println("Is Auto Increment = "+columnAutoIncrement);
//                System.out.println("Is Primary = "+columnIsPrimaryKey);
//            }
    
          int x = 0;
            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                int columnDataType = rsColumns.getInt("DATA_TYPE");
                boolean columnIsNullable = rsColumns.getBoolean("IS_NULLABLE");
                String columnDefaultValue = rsColumns.getString("COLUMN_DEF");
                MysqlType mysqlDataType = MysqlType.getByJdbcType(columnDataType);
                
                
                System.out.println("Name = "+columnName);
                System.out.println("Type = "+columnDataType);
                System.out.println("Data Type ="+mysqlDataType);
                System.out.println("Default = "+columnDefaultValue);
                System.out.println("Is Null = "+columnIsNullable);
                System.out.println("-----------------------");
                x++;
            }
            System.out.println("-----------------------");
        }
      return new Database("",null);
    }
}
