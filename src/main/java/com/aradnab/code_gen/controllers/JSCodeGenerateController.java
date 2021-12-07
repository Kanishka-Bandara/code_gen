/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.Column;

/**
 *
 * @author kanishka
 */
public class JSCodeGenerateController {

    public static JSCodeGenerateController defaultController = new JSCodeGenerateController();

    public String generateJSVariableForSetters(Column c) {
//        String js = "$('#" + c.getColumnJsId() + "').val(data." + c.getSqlName() + ");";
        String js = "";
        switch (c.getColumnHtmlFieldType()) {
            case "img":
                js = "                let " + c.getColumnJsName() + " = imageFolderPath+data." + c.getSqlName() + ";\n"
                        + "                if (!$.trim(" + c.getColumnJsName() + ")){\n"
                        + "                    $(\"#" + c.getColumnJsImageSrcPreviewId() + "\").attr(\"src\", imageFolderPath+data." + c.getSqlName() + ");\n"
                        + "                }";
                break;
            case "cb":
                break;
            case "rb":
                js = "                $('#" + c.getColumnJsId() + "' + data." + c.getSqlName() + ").attr('checked', true);";
                break;
            default:
                js = "$('#" + c.getColumnJsId() + "').val(data." + c.getSqlName() + ");";
                break;
        }
        return js;
    }
    
    public String generateJSVariableForNullSetters(Column c) {
        String js = "";
        switch (c.getColumnHtmlFieldType()) {
            case "img":
                js = "                    $(\"#" + c.getColumnJsImageSrcPreviewId() + "\").attr(\"src\", '/grama/Views/assets/img/sign_here.jpg');";
                break;
            case "cb":
                break;
            case "rb":
//                js = "                $('#" + c.getColumnJsId() + "' + data." + c.getSqlName() + ").attr('checked', true);";
                break;
            default:
                js = "$('#" + c.getColumnJsId() + "').val(null);";
                break;
        }
        return js;
    }
}
