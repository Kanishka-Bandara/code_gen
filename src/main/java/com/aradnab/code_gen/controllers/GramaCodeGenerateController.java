/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.Database;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author kanishka
 */
public class GramaCodeGenerateController {
  public static GramaCodeGenerateController defaultController=  new GramaCodeGenerateController();
      boolean deleteExistingFiles = true;
  
  public void generate() throws Exception{
      String projectName = "grama_1";
      String folderPath = "/opt/lampp/htdocs";
      String mainFolderPath = folderPath+"/"+projectName;
      String formListsHtmlFolderPath = mainFolderPath+"/Views";
      String formsHtmlFolderPath = formListsHtmlFolderPath+"/forms";
      String controllerFolderPath = mainFolderPath+"/Controllers";
      File mainFolder = new File(mainFolderPath);
      File formFolder = new File(formsHtmlFolderPath);
      File formListFolder = new File(formListsHtmlFolderPath);
      File controllersFolder = new File(controllerFolderPath);
      
      Database database = DBController.defaultController.getDatabaseDetails();
      
      //Creating project folder
      createFolder(mainFolder,"Main");
      //Creating html forms list folder folder
      createFolder(formListFolder,"formList");
      //Creating html forms folder folder
      createFolder(formFolder,"forms");
      //Creating controllers folder folder
      createFolder(controllersFolder,"Controllers");
      
      
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
          if (folder.mkdirs()) {
              System.out.println(name+" "+"Folder has been created.");
          }else{
              System.out.println(name+" "+"Folder has not been created.");
          }
      }else{
          if (!deleteExistingFiles) {
            System.out.println(name+" "+"Folder is existing.About to delete.");
          if (folder.delete()) {
                System.out.println(name+" "+"Folder has been deleted.");
                if (folder.mkdirs()) {
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
  
    public void createFile(File file,String name) throws IOException{
    if (!file.exists()) {
          if (file.createNewFile()) {
              System.out.println(name+" "+"file has been created.");
          }else{
              System.out.println(name+" "+"file has not been created.");
          }
      }else{
          if (deleteExistingFiles) {
            System.out.println(name+" "+"file is existing.About to delete.");
          if (file.delete()) {
                System.out.println(name+" "+"file has been deleted.");
                if (file.mkdirs()) {
                    System.out.println(name+" "+"file has been newly created.");
                }else{
                    System.out.println(name+" "+"file has not been created.");
                }
          }else{
              System.out.println(name+" "+"file has not been deleted.");
           }
        }
      }
  }
  
}
