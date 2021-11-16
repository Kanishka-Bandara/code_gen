/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen;

import com.aradnab.code_gen.controllers.DBController;
import com.aradnab.code_gen.controllers.GramaCodeGenerateController;
import com.aradnab.code_gen.models.DB;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kanishka
 */
public class Main {
    public static void main(String[] args) {
        try {
            GramaCodeGenerateController.defaultController.generate();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
