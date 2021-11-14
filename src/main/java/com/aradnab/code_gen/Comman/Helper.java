/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.Comman;


/**
 *
 * @author kanishka
 */
public class Helper {

    public static Helper defaultHelper = new Helper();
    
    public String snakeCaseToCamelCase(String word){
        String s = "";
        String[] split = word.split("_");
        if (split.length>1) {
            for (int i = 0;i<split.length;i++) {
                if (i==0) {
                    s+=split[i].toLowerCase();
                } else {
                    s+=split[i].substring(0, 1).toUpperCase()+split[i].substring(1, split.length).toLowerCase();
                }
            }
        } else {
            return word.toLowerCase();
        }
        return s;
    }
    
//    private String convertCaseStyle(String word,CaseStyle from,CaseStyle to){
//        String s = "";
//        String[] split = word.split("_");
//        if (from==CaseStyle.SNAKE_CASE) {
//            
//        }else if (from==CaseStyle.KEBAB_CASE) {
//            
//        }
//        if (split.length>1) {
//            for (int i = 0;i<split.length;i++) {
//                if (i==0) {
//                    s+=split[i];
//                } else {
//                    s+=split[i].substring(0, 1).toUpperCase()+split[i].substring(1, split.length).toLowerCase();
//                }
//            }
//        } else {
//            return word.toLowerCase();
//        }
//        return s;
//    }
    
}
