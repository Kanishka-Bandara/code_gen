/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.Column;
import com.aradnab.code_gen.models.Database;
import com.aradnab.code_gen.models.Table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author kanishka
 */
public class GramaCodeGenerateController {

    public static GramaCodeGenerateController defaultController = new GramaCodeGenerateController();
    boolean deleteExistingFiles = true;

    public void generate() throws Exception {
        String projectName = "grama_1";
        String folderPath = "/opt/lampp/htdocs";
        String mainFolderPath = folderPath + "/" + projectName;
        String formListsHtmlFolderPath = mainFolderPath + "/Views";
        String formsHtmlFolderPath = formListsHtmlFolderPath + "/forms";
        String controllerFolderPath = mainFolderPath + "/Controllers";
        File mainFolder = new File(mainFolderPath);
        File formFolder = new File(formsHtmlFolderPath);
        File formListFolder = new File(formListsHtmlFolderPath);
        File controllersFolder = new File(controllerFolderPath);

        Database database = DBController.defaultController.getDatabaseDetails();

        //Creating project folder
        createFolder(mainFolder, "Main");
        //Creating html forms list folder folder
        createFolder(formListFolder, "formList");
        //Creating html forms folder folder
        createFolder(formFolder, "forms");
        //Creating controllers folder folder
        createFolder(controllersFolder, "Controllers");

        List<Table> tables = database.getTables();
//      Looping tables
        for (Table table : tables) {
            if (!(table.getInitName().equals("formnic") || table.getInitName().equals("users"))) {
                this.generateRegisterAndUpdateFile(table, controllerFolderPath);
                this.generateDeleteControllerFile(table, controllerFolderPath);
                this.generateFormIdGenerateControllerFile(table, controllerFolderPath);
                this.generateGetFormDetailsControllerFile(table, controllerFolderPath);
            }
        }
    }

    public void createFolder(File folder, String name) {
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println(name + " " + "Folder has been created.");
            } else {
                System.out.println(name + " " + "Folder has not been created.");
            }
        } else {
            if (!deleteExistingFiles) {
                System.out.println(name + " " + "Folder is existing.About to delete.");
                if (folder.delete()) {
                    System.out.println(name + " " + "Folder has been deleted.");
                    if (folder.mkdirs()) {
                        System.out.println(name + " " + "Folder has been newly created.");
                    } else {
                        System.out.println(name + " " + "Folder has not been created.");
                    }
                } else {
                    System.out.println(name + " " + "Folder has not been deleted.");
                }
            }
        }
    }

    public void createFile(File file, String name) throws IOException {
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println(name + " " + "file has been created.");
            } else {
                System.out.println(name + " " + "file has not been created.");
            }
        } else {
            if (deleteExistingFiles) {
                System.out.println(name + " " + "file is existing.About to delete.");
                if (file.delete()) {
                    System.out.println(name + " " + "file has been deleted.");
                    if (file.createNewFile()) {
                        System.out.println(name + " " + "file has been newly created.");
                    } else {
                        System.out.println(name + " " + "file has not been created.");
                    }
                } else {
                    System.out.println(name + " " + "file has not been deleted.");
                }
            }
        }
    }

    public void writeToFile(File file, Vector<String> text) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (String string : text) {
            bw.write(string);
            bw.newLine();
        }
        bw.close();
    }

    public void generateRegisterAndUpdateFile(Table table, String controllerFolderPath) throws IOException {
        System.out.println(table.getInitName());
        String controllerFolderTablePath = controllerFolderPath + "/" + table.getNameInCamelCase();
        File controllerTableFolder = new File(controllerFolderTablePath);
        String controllerCreateFileTablePath = controllerFolderTablePath + "/" + table.getCreateControllerFileName();
        File controllerTableCreateFile = new File(controllerCreateFileTablePath);
        //BEGIN::String holders
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        //END::String holders

        controllerRegister_1_Importers.add("<?php");
        controllerRegister_1_Importers.add("require '../../config/db.php';");
        controllerRegister_1_Importers.add("require '../../config/ImageHelper.php';");
        controllerRegister_1_Importers.add("require '../../config/helpers.php';");
        controllerRegister_1_Importers.add("require '../../config/status.php';");
        controllerRegister_1_Importers.add("require '../../config/userType.php';");
        controllerRegister_1_Importers.add("require '../../config/formType.php';");
        controllerRegister_1_Importers.add("require '../../config/form_status.php';");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("//BEGIN::Getting from variables");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$formId = $_POST['formid'];");
        controllerRegister_1_Importers.add("$formType = $_POST['form_type'];");
        controllerRegister_1_Importers.add("$userType = $_POST['user_type'];");
        controllerRegister_1_Importers.add("$userId = $_POST['user_id'];");
        controllerRegister_1_Importers.add("");
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            if (column.getSqlName().equals("created_at")) {
//                        controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = date('Y-m-d H:i:s');");
            } else if (column.getSqlName().equals("updated_at")) {
//                        controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = date('Y-m-d H:i:s');");
            } else if (column.getSqlName().equals("status")) {
//                        controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = $_STATUS_LIVE;");
            } else if (column.getSqlName().equals("form_status")) {

            } else if (column.getSqlName().equals("id")) {

            } else {
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_GS) {");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_DS) {");
                }
                if (column.getColumnHtmlFieldType().equals("img")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = $_POST['" + column.getColumnNameInCamelCase() + "'];");
                    controllerRegister_1_Importers.add("if (isset(" + column.getColumnNameAsPHPVariable() + ")) {");
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = ImageHelper::saveImage(" + column.getColumnNameAsPHPVariable() + ");");
                    controllerRegister_1_Importers.add("}");
                } else {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = $_POST['" + column.getColumnNameInCamelCase() + "'];");
                }

                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add("}");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add("}");
                }
            }
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("//END::Getting from variables");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("//BEGIN::Save and edit function");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$sql = null;");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("if ($formType == $_FORM_TYPE_SAVE) {");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("//Create Statement");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$sql = '" + table.getCreateStatement() + "';");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("} else {");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("//Update Statement");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$sql = '" + table.getUpdateStatement() + "';");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("}");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("//END::Save and edit function");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$statement = $pdo->prepare($sql);");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$processData = [];");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        columns.forEach(column -> {
            if (column.getSqlName().equals("created_at")) {
                controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = date('Y-m-d H:i:s');");
            } else if (column.getSqlName().equals("updated_at")) {
                controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = date('Y-m-d H:i:s');");
            } else if (column.getSqlName().equals("status")) {
                controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = $_STATUS_LIVE;");
            } else if (column.getSqlName().equals("form_status")) {
                controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_CITIZEN) {");
                controllerRegister_1_Importers.add("\t$processData['" + column.getSqlName() + "'] = $_FORM_TYPE_SUBMITTED;");
                controllerRegister_1_Importers.add("}");

                controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_GS) {");
                controllerRegister_1_Importers.add("\t$processData['" + column.getSqlName() + "'] = $_FORM_TYPE_APPROVED;");
                controllerRegister_1_Importers.add("}");

                controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_DS) {");
                controllerRegister_1_Importers.add("\t$processData['" + column.getSqlName() + "'] = $_FORM_TYPE_FINALIZED;");
                controllerRegister_1_Importers.add("}");

            } else if (column.getSqlName().equals("id")) {
                controllerRegister_1_Importers.add("if ($formType != $_FORM_TYPE_SAVE) {");
                controllerRegister_1_Importers.add("\t$processData['" + column.getSqlName() + "'] = $formId;");
                controllerRegister_1_Importers.add("}");
            } else {
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_GS) {");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_DS) {");
                }

                controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = " + column.getColumnNameAsPHPVariable() + ";");

                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add("}");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add("}");
                }
            }
        });
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$statement->execute($processData);");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("return redirect('../../Views/" + table.getListFileName() + "?success-msg=NIC Registration Success');");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");

        //BEGIN::Writing
        this.createFolder(controllerTableFolder, table.getNameInCamelCase());
        this.createFile(controllerTableCreateFile, table.getCreateControllerFileName());
        this.writeToFile(controllerTableCreateFile, controllerRegister_1_Importers);
        //END::Writing
    }

    public void generateDeleteControllerFile(Table table, String controllerFolderPath) throws IOException {
        System.out.println(table.getInitName());
        String controllerFolderTablePath = controllerFolderPath + "/" + table.getNameInCamelCase();
        File controllerTableFolder = new File(controllerFolderTablePath);
        String controllerDeleteFileTablePath = controllerFolderTablePath + "/" + table.getDeleteControllerFileName();
        File controllerTableDeleteFile = new File(controllerDeleteFileTablePath);
        //BEGIN::String holders
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        //END::String holders

        controllerRegister_1_Importers.add("<?php");
        controllerRegister_1_Importers.add("require '../../config/db.php';");
        controllerRegister_1_Importers.add("require '../../config/helpers.php';");
        controllerRegister_1_Importers.add("require '../../config/status.php';");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$id=$_GET['id'];");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$step=$pdo->prepare('UPDATE `" + table.getInitName() + "`");
        controllerRegister_1_Importers.add("SET");
        controllerRegister_1_Importers.add("`status` = :status");
        controllerRegister_1_Importers.add("WHERE `id` = :id;');");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$step->bindParam(\":id\",$id,PDO::PARAM_INT);");
        controllerRegister_1_Importers.add("$step->bindParam(\":status\",$_STATUS_DELETE,PDO::PARAM_INT);");
        controllerRegister_1_Importers.add("$step->execute();");
        controllerRegister_1_Importers.add("return redirect('../../Views/" + table.getListFileName() + "?success-msg=NIC Form Deleted');");
        controllerRegister_1_Importers.add("");

        //BEGIN::Writing
        this.createFolder(controllerTableFolder, table.getNameInCamelCase());
        this.createFile(controllerTableDeleteFile, table.getDeleteControllerFileName());
        this.writeToFile(controllerTableDeleteFile, controllerRegister_1_Importers);
        //END::Writing
    }

    public void generateFormIdGenerateControllerFile(Table table, String controllerFolderPath) throws IOException {
        System.out.println(table.getInitName());
        String controllerFolderTablePath = controllerFolderPath + "/" + table.getNameInCamelCase();
        File controllerTableFolder = new File(controllerFolderTablePath);
        String controllerDeleteFileTablePath = controllerFolderTablePath + "/" + table.getFormIdGenerateControllerFileName();
        File controllerTableGenerateIdFile = new File(controllerDeleteFileTablePath);
        //BEGIN::String holders
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        //END::String holders

        controllerRegister_1_Importers.add("<?php");
        controllerRegister_1_Importers.add("require '../../config/db.php';");
        controllerRegister_1_Importers.add("require '../../config/helpers.php';");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$query = 'SELECT COUNT(id) as `x` FROM "+table.getInitName()+"';");
        controllerRegister_1_Importers.add("$findStatement = $pdo->prepare($query);");
        controllerRegister_1_Importers.add("$findStatement->execute();");
        controllerRegister_1_Importers.add("$dbResp = $findStatement->fetchAll(PDO::FETCH_ASSOC);");
        controllerRegister_1_Importers.add("echo str_pad(++$dbResp[0]['x'], 8, '0', STR_PAD_LEFT);");
        controllerRegister_1_Importers.add("");

        //BEGIN::Writing
        this.createFolder(controllerTableFolder, table.getNameInCamelCase());
        this.createFile(controllerTableGenerateIdFile, table.getDeleteControllerFileName());
        this.writeToFile(controllerTableGenerateIdFile, controllerRegister_1_Importers);
        //END::Writing
    }
    
    public void generateGetFormDetailsControllerFile(Table table, String controllerFolderPath) throws IOException {
        System.out.println(table.getInitName());
        String controllerFolderTablePath = controllerFolderPath + "/" + table.getNameInCamelCase();
        File controllerTableFolder = new File(controllerFolderTablePath);
        String controllerGetTablePath = controllerFolderTablePath + "/" + table.getGetFormControllerFileName();
        File controllerGetTableDetailsFile = new File(controllerGetTablePath);
        //BEGIN::String holders
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        //END::String holders

        controllerRegister_1_Importers.add("<?php");
        controllerRegister_1_Importers.add("require '../../config/db.php';");
        controllerRegister_1_Importers.add("require '../../config/helpers.php';");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$id = $_GET['id'];");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("$findStatement = $pdo->prepare('SELECT * FROM `"+table.getInitName()+"` WHERE `id`=:id');");
        controllerRegister_1_Importers.add("$findStatement->bindParam(':id', $id, PDO::PARAM_INT);");
        controllerRegister_1_Importers.add("$findStatement->execute();");
        controllerRegister_1_Importers.add("$getRecord=$findStatement->fetchAll(PDO::FETCH_ASSOC);");
        controllerRegister_1_Importers.add("echo json_encode($getRecord[0]);");
        controllerRegister_1_Importers.add("");

        //BEGIN::Writing
        this.createFolder(controllerTableFolder, table.getNameInCamelCase());
        this.createFile(controllerGetTableDetailsFile, table.getGetFormControllerFileName());
        this.writeToFile(controllerGetTableDetailsFile, controllerRegister_1_Importers);
        //END::Writing
    }

}
