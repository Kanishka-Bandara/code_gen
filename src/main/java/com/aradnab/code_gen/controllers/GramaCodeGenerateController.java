/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.Column;
import com.aradnab.code_gen.models.Database;
import com.aradnab.code_gen.models.Table;
import java.util.List;

/**
 *
 * @author kanishka
 */
public class GramaCodeGenerateController {
  public static GramaCodeGenerateController defaultController=  new GramaCodeGenerateController();
  
  public void generate() throws Exception{
      Database database = DBController.defaultController.getDatabaseDetails();
      System.out.println("##### [Database = "+database.getSqlName()+"] #####");
      List<Table> tables = database.getTables();
      for (Table table : tables) {
          System.out.println("--[Table = "+table.getInitName()+"]--");
          List<Column> columns = table.getColumns();
          for (Column column : columns) {
              System.out.println(column.toString());
          }
          System.out.println("------");
      }
  }
}
