/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.DB;
import com.aradnab.code_gen.models.Database;
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
            System.out.println(tbl);
        }
      return new Database();
    }
}
