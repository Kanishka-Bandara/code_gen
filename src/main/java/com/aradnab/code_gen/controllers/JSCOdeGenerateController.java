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
public class JSCOdeGenerateController {

    public static JSCOdeGenerateController defaultController = new JSCOdeGenerateController();

    public String generateJSVariableForSetters(Column c) {
        String js = "$('#" + c.getColumnJsId() + "').val(data." + c.getSqlName() + ");";
        switch (c.getColumnHtmlFieldType()) {
            case "img":
                js = "                let " + c.getColumnJsName() + " = imageFolderPath+data." + c.getSqlName() + ";\n"
                        + "                if (" + c.getColumnJsName() + "!=null){\n"
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
}
