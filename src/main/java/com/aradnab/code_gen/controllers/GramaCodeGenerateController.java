/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.Database;
import java.io.File;

/**
 *
 * @author kanishka
 */
public class GramaCodeGenerateController {
  public static GramaCodeGenerateController defaultController=  new GramaCodeGenerateController();
  
  public void generate() throws Exception{
      String projectName = "grama_1";
      String folderPath = "/opt/lampp/htdocs";
      String mainFolderPath = folderPath+"/"+projectName;
      String formsHtmlFolderPath = mainFolderPath+"/Views/forms";
      File mainFolder = new File(mainFolderPath);
      File formFolder = new File(formsHtmlFolderPath);
      
      Database database = DBController.defaultController.getDatabaseDetails();
      
      //Creating project folder
      createFolder(mainFolder,"Main");
      
      
//      System.out.println("##### [Database = "+database.getSqlName()+"] #####");
//      List<Table> tables = database.getTables();
//      for (Table table : tables) {
//          System.out.println("--[Table = "+table.getInitName()+"]--");
//          List<Column> columns = table.getColumns();
//          for (Column column : columns) {
//              System.out.println(column.toString());
//          }
//          System.out.println("------");
//      }
  }
  
  public void createFolder(File folder,String name){
    if (!folder.exists()) {
          if (folder.mkdir()) {
              System.out.println(name+" "+"Folder has been created.");
          }else{
              System.out.println(name+" "+"Folder has not been created.");
          }
      }else{
          System.out.println(name+" "+"Folder is existing.About to delete.");
          if (folder.delete()) {
                System.out.println(name+" "+"Folder has been deleted.");
                if (folder.mkdir()) {
                    System.out.println(name+" "+"Folder has been newly created.");
                }else{
                    System.out.println(name+" "+"Folder has not been created.");
                }
          }else{
              System.out.println(name+" "+"Folder has not been deleted.");
          }
      }
  }
  
}
