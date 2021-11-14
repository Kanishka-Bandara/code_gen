/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.DB;
import com.aradnab.code_gen.models.Database;
import java.sql.ResultSet;
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
            while (rsColumns.next()) {                
                String columnName = rsColumns.getString("COLUMN_NAME");
                String columnDataType = rsColumns.getString("DATA_TYPE");
                boolean columnIsNullable = rsColumns.getBoolean("IS_NULLABLE");
                String columnDefaultValue = rsColumns.getString("COLUMN_DEF");
                System.out.println(columnName+"\t|\t"+columnDataType+"\t|\t"+columnIsNullable+"\t|\t"+columnDefaultValue);
            }
            System.out.println("-----------------------");
        }
      return new Database("",null);
    }
}
