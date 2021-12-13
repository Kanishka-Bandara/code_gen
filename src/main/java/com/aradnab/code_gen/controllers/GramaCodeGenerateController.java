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
        String projectName = "grama";
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
        Vector<Table> sideBarList = new Vector<>();
//      Looping tables
        for (Table table : tables) {
            if (!(table.getSQLName().equals("formnic") || table.getSQLName().equals("users"))) {
                this.generateRegisterAndUpdateFile(table, controllerFolderPath);
                this.generateDeleteControllerFile(table, controllerFolderPath);
                this.generateFormIdGenerateControllerFile(table, controllerFolderPath);
                this.generateGetFormDetailsControllerFile(table, controllerFolderPath);
                this.generateFormFile(table, formsHtmlFolderPath);
                this.generateGetFormListFile(table, formListsHtmlFolderPath);
                sideBarList.add(table);
            }
        }
        this.generateDashboardSidebarFile(sideBarList, formListsHtmlFolderPath);
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
        System.out.println(table.getSQLName());
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
        controllerRegister_1_Importers.add("$formId = $_POST['form_id'];");
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

            } else if (column.getSqlName().equals("application_no")) {
                controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = $_POST['" + column.getColumnNameInCamelCase() + "'];");
            } else {
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_GS) {");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_DS) {");
                }
                if (column.getColumnHtmlFieldType().equals("img")) {
                    controllerRegister_1_Importers.add(column.getColumnNameAsPHPVariable() + " = $_FILES['" + column.getColumnNameInCamelCase() + "'];");
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
                controllerRegister_1_Importers.add("");
                controllerRegister_1_Importers.add("if ($formType == $_FORM_TYPE_SAVE) {");
                controllerRegister_1_Importers.add("");
                controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = date('Y-m-d H:i:s');");
                controllerRegister_1_Importers.add("");
                controllerRegister_1_Importers.add("}");
                controllerRegister_1_Importers.add("");
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
//BEGIN::123456

                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_GS) {");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = null;");
                    controllerRegister_1_Importers.add("if ($userType == $_USER_TYPE_DS) {");
                }
                controllerRegister_1_Importers.add("$processData['" + column.getSqlName() + "'] = " + column.getColumnNameAsPHPVariable() + ";");
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    controllerRegister_1_Importers.add("}");
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    controllerRegister_1_Importers.add("}");
                }

                //------------
//ENDsss::123456
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
        System.out.println(table.getSQLName());
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
        controllerRegister_1_Importers.add("$step=$pdo->prepare('UPDATE `" + table.getSQLName() + "`");
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
        System.out.println(table.getSQLName());
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
        controllerRegister_1_Importers.add("$query = 'SELECT COUNT(id) as `x` FROM " + table.getSQLName() + "';");
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
        System.out.println(table.getSQLName());
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
        controllerRegister_1_Importers.add("$findStatement = $pdo->prepare('SELECT * FROM `" + table.getSQLName() + "` WHERE `id`=:id');");
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

    public void generateFormFile(Table table, String formsHtmlFolderPath) throws IOException {
        System.out.println(table.getSQLName());
        String formsHtmlPath = formsHtmlFolderPath + "/" + table.getFormFileName();
        File formsHtmlTableDetailsFile = new File(formsHtmlPath);
        //BEGIN::String holders
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        //END::String holders

        controllerRegister_1_Importers.add("<div class=\"modal fade\" id=\"newform\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"newformLabel\"\n"
                + "     aria-hidden=\"true\">");
        controllerRegister_1_Importers.add("    <form enctype=\"multipart/form-data\" method=\"POST\" action=\"../Controllers/" + table.getNameInCamelCase() + "/" + table.getCreateControllerFileName() + "\"\n"
                + "          onsubmit=\"generateApplicationNumber();return true;\">");
        controllerRegister_1_Importers.add("        <input type=\"hidden\" id=\"form_id\" name=\"form_id\" value=\"\">");
        controllerRegister_1_Importers.add("        <input type=\"hidden\" id=\"form_type\" name=\"form_type\" value=\"1\">");
        controllerRegister_1_Importers.add("        <input type=\"hidden\" id=\"user_id\" name=\"user_id\" value=\"<?php print_r($_SESSION[\"auth\"]['id']) ?>\">");
        controllerRegister_1_Importers.add("        <input type=\"hidden\" id=\"user_type\" name=\"user_type\" value=\"<?php print_r($_SESSION[\"auth\"]['type']) ?>\">");
        controllerRegister_1_Importers.add("        <div class=\"modal-dialog modal-lg\" role=\"document\">");
        controllerRegister_1_Importers.add("            <div class=\"modal-content\">");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <div class=\"modal-header\">");
        controllerRegister_1_Importers.add("                    <h5 class=\"modal-title\" id=\"newformLabel\"><strong>" + table.getTableNameToDisplay() + "</strong></h5>");
        controllerRegister_1_Importers.add("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">");
        controllerRegister_1_Importers.add("                        <span aria-hidden=\"true\">&times;</span>");
        controllerRegister_1_Importers.add("                    </button>");
        controllerRegister_1_Importers.add("                </div>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("<div class=\"modal-body\">");//Begin::Model Body
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("<div class=\"row\">\n"
                + "                        <div class=\"col-md-12 text-center\">\n"
                + "                            <h6><strong>%Main Header%</strong></h6>\n"
                + "                            <p><small>%Sub Header or desc%</small></p>\n"
                + "                        </div>\n"
                + "                    </div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");

        Vector<String> s01 = new Vector<String>();
        Vector<String> s02 = new Vector<String>();
        Vector<String> s03 = new Vector<String>();

        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            if (column.getSqlName().equals("created_at")) {

            } else if (column.getSqlName().equals("updated_at")) {
//              
            } else if (column.getSqlName().equals("status")) {
//              
            } else if (column.getSqlName().equals("form_status")) {

            } else if (column.getSqlName().equals("application_no")) {

            } else if (column.getSqlName().equals("id")) {

            } else {
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    s02.add(HtmlCodeGenerateController.defaultController.generateHtmlField(column));
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    s03.add(HtmlCodeGenerateController.defaultController.generateHtmlField(column));
                } else {
                    s01.add(HtmlCodeGenerateController.defaultController.generateHtmlField(column));
                }
            }
        }

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add(HtmlCodeGenerateController.defaultController.generateApplicationNoFormFieldSection());
        controllerRegister_1_Importers.add("                        <!--                    BEGIN::Applicant Section-->");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add(HtmlCodeGenerateController.defaultController.getSection01ForApplicant());
        for (String html : s01) {
            controllerRegister_1_Importers.add(html);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                        <!--                    END::Applicant Section-->");
        controllerRegister_1_Importers.add(" <?php\n"
                + "                    if ($_SESSION[\"auth\"]['type'] == 3 || $_SESSION[\"auth\"]['type'] == 2 || $_SESSION[\"auth\"]['type'] == 1) {\n"
                + "                        ?>");
        controllerRegister_1_Importers.add("                        <!--                    BEGIN::Grama Niladari Section-->");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add(HtmlCodeGenerateController.defaultController.getSection02ForGS());
        for (String html : s02) {
            controllerRegister_1_Importers.add(html);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                        <!--                    END::Grama Niladari Section-->");
        controllerRegister_1_Importers.add(" <?PHP\n"
                + "                    }\n"
                + "                    ?>\n"
                + "                    <?php\n"
                + "                    if ($_SESSION[\"auth\"]['type'] == 2 || $_SESSION[\"auth\"]['type'] == 1) {\n"
                + "                        ?>");
        controllerRegister_1_Importers.add("                        <!--                    BEGIN::Divisional secretariat Section-->");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add(HtmlCodeGenerateController.defaultController.getSection03ForDS());
        for (String html : s03) {
            controllerRegister_1_Importers.add(html);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                        <!--                    END::Divisional secretariat Section-->");
        controllerRegister_1_Importers.add("                        <?PHP\n"
                + "                    }\n"
                + "                    ?>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                </div>");//End Model body

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <div class=\"modal-footer\">");
        controllerRegister_1_Importers.add("                    <button type=\"button\" class=\"btn btn-sm btn-secondary\" data-dismiss=\"modal\">Close</button>");
        controllerRegister_1_Importers.add("                    <button type=\"submit\" class=\"btn btn-sm btn-primary\">Submit</button>");
        controllerRegister_1_Importers.add("                </div>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("            </div>");
        controllerRegister_1_Importers.add("        </div>");
        controllerRegister_1_Importers.add("    </form>");
        controllerRegister_1_Importers.add("</div>");
        controllerRegister_1_Importers.add("");

        //BEGIN::Writing
//        this.createFolder(formsHtmlFolder, table.getNameInCamelCase());
        this.createFile(formsHtmlTableDetailsFile, table.getFormFileName());
        this.writeToFile(formsHtmlTableDetailsFile, controllerRegister_1_Importers);
        //END::Writing
    }

    public void generateGetFormListFile(Table table, String formListsHtmlFolderPath) throws IOException {
        System.out.println(table.getSQLName());
        String formsHtmlPath = formListsHtmlFolderPath + "/" + table.getListFileName();
        File formsListFile = new File(formsHtmlPath);
        List<Column> columns = table.getColumns();
        //BEGIN::String holders
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        //END::String holders

        controllerRegister_1_Importers.add("<?php");
        controllerRegister_1_Importers.add("require '../Models/auth.php';");
        controllerRegister_1_Importers.add("require '../config/db.php';");
        controllerRegister_1_Importers.add("require '../config/form_status.php';");
        controllerRegister_1_Importers.add("require '../config/status.php';");
        controllerRegister_1_Importers.add("require '../config/ImageHelper.php';");
        controllerRegister_1_Importers.add("?>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("<!DOCTYPE html>");
        controllerRegister_1_Importers.add("<html lang=\"en\">");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("<head>\n"
                + "\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                + "    <meta name=\"description\" content=\"\">\n"
                + "    <meta name=\"author\" content=\"\">\n"
                + "    <link rel=\"shortcut icon\" type=\"image/jpg\" href=\"assets/favicon.ico\"/>\n"
                + "    <title>Dashboard</title>\n"
                + "    <link href=\"assets/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "    <link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\"\n"
                + "          rel=\"stylesheet\">\n"
                + "    <link href=\"assets/css/sb-admin-2.min.css\" rel=\"stylesheet\">\n"
                + "    <link href=\"assets/vendor/datatables/dataTables.bootstrap4.min.css\" rel=\"stylesheet\">\n"
                + "    <script src=\"assets/js/init.js\"></script>\n"
                + "</head>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("<body id=\"page-top\">");

        controllerRegister_1_Importers.add("<div id=\"wrapper\">");
        controllerRegister_1_Importers.add("    <?php include 'dashboard-sidebar.php'; ?>");
        controllerRegister_1_Importers.add("    <div id=\"content-wrapper\" class=\"d-flex flex-column\">");
        controllerRegister_1_Importers.add("        <div id=\"content\">");
        controllerRegister_1_Importers.add("            <nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">\n"
                + "                <form class=\"form-inline\">\n"
                + "                    <button id=\"sidebarToggleTop\" class=\"btn btn-link d-md-none rounded-circle mr-3\">\n"
                + "                        <i class=\"fa fa-bars\"></i>\n"
                + "                    </button>\n"
                + "                </form>\n"
                + "                <ul class=\"navbar-nav ml-auto\">\n"
                + "                    <div class=\"topbar-divider d-none d-sm-block\"></div>\n"
                + "                    <!-- Nav Item - User Information -->\n"
                + "                    <li class=\"nav-item dropdown no-arrow\">\n"
                + "                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\"\n"
                + "                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                + "                            <span class=\"mr-2 d-none d-lg-inline text-gray-600 small\"><?php print_r($_SESSION[\"auth\"]['name']) ?></span>\n"
                + "                            <img class=\"img-profile rounded-circle\"\n"
                + "                                 src=\"https://www.pngfind.com/pngs/m/470-4703547_icon-user-icon-hd-png-download.png\">\n"
                + "                        </a>\n"
                + "                        <!-- Dropdown - User Information -->\n"
                + "                        <div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n"
                + "                             aria-labelledby=\"userDropdown\">\n"
                + "                            <a class=\"dropdown-item\" href=\"../Controllers/logout.php\">\n"
                + "                                <i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>\n"
                + "                                Logout\n"
                + "                            </a>\n"
                + "                        </div>\n"
                + "                    </li>\n"
                + "                </ul>\n"
                + "            </nav>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("            <div class=\"container-fluid\">");
        controllerRegister_1_Importers.add("");
        //TODO::Can change the add new butto
        controllerRegister_1_Importers.add("                <div class=\"row\">\n");
        controllerRegister_1_Importers.add("                    <div class=\"col-2\">\n"
                + "                        <select name=\"filter_tag\" class=\"form-control\" id=\"filter_tag\" onchange=\"filterForm(this);\">\n"
                + "                            <option disabled selected>--Select filter--</option>\n"
                + "                            <option value=\"-5\">All</option>\n"
                + "                            <option value=\"0\">Submitted</option>\n"
                + "                            <option value=\"1\">Approved</option>\n"
                + "                            <option value=\"2\">Finalized</option>\n"
                + "                        </select>\n"
                + "                    </div>");
        controllerRegister_1_Importers.add("                    <div class=\"col-4\">\n"
                + "                        <button type=\"button\" class=\"btn btn-primary \" onclick=\"clearForm()\"\n"
                + "                                data-toggle=\"modal\"\n"
                + "                                data-target=\"#newform\">\n"
                + "                            New Registration\n"
                + "                        </button>\n"
                + "                    </div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                </div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <div class=\"row mt-3\">");
        controllerRegister_1_Importers.add("                    <div class=\"col-md-12\">");
        controllerRegister_1_Importers.add("                        <div class=\"table-responsive\">");
        controllerRegister_1_Importers.add("                            <table class=\"table table-light\">");
        controllerRegister_1_Importers.add("                                <thead>\n"
                + "                                <tr>\n"
                + "                                    <th>No #</th>\n"
                + "                                    <th>Person Name</th>\n"
                + "                                    <th>Application Number</th>\n"
                + "                                    <th>Status</th>\n"
                + "                                    <th>Actions</th>\n"
                + "                                </tr>\n"
                + "                                </thead>");
        controllerRegister_1_Importers.add("                                <tbody>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("                                <?php\n");
        controllerRegister_1_Importers.add("                                $query = \"SELECT * FROM " + table.getSQLName() + " WHERE status <> \" . $_STATUS_DELETE . \";\";\n");
        controllerRegister_1_Importers.add("                                $filterStatus = null;\n"
                + "                                if (isset($_GET['filter'])){\n"
                + "                                    $filterStatus = $_GET['filter'];\n"
                + "                                    if (($filterStatus==0)||($filterStatus==1)||($filterStatus==2)){\n"
                + "                                        $query = \"SELECT * FROM " + table.getSQLName() + " WHERE status <> \" . $_STATUS_DELETE . \" AND form_status = \".$filterStatus.\";\";\n"
                + "                                    }\n"
                + "                                }");
        controllerRegister_1_Importers.add(""
                + ""
                + "                                $findStatement = $pdo->prepare($query);\n"
                + "                                $findStatement->execute();\n"
                + "                                if ($records = $findStatement->fetchAll(PDO::FETCH_ASSOC)) {\n"
                + "                                    $index = 1;\n"
                + "                                    foreach ($records as $key => $value) {\n"
                + "                                        ?>\n"
                + "                                        <tr>\n"
                + "                                            <td><?= $index ?></td>\n"
                + "                                            <td><?= " + HtmlCodeGenerateController.defaultController.generateDataVariableForListTableData(table) + " ?></td>\n"
                + "                                            <td><?= $value['application_no'] ?></td>\n"
                + "                                            <td>\n"
                + "                                                <span class=\"badge <?= getFormStatusColorClass($value['form_status']) ?>\"><?= getFormStatus($value['form_status']) ?></span>\n"
                + "                                            </td>\n"
                + "                                            <td>\n"
                + "                                                <a onclick=\"getRecords(<?= $value['id'] ?>)\"><i\n"
                + "                                                            class=\"fas fa-edit mx-2 text-warning\"></i></a>\n"
                + "                                                <a href=\"../Controllers/" + table.getNameInCamelCase() + "/" + table.getDeleteControllerFileName() + "?id=<?= $value['id'] ?>\"><i\n"
                + "                                                            class=\"fas fa-trash mx-2 text-danger\"></i></a>\n"
                + "                                            </td>\n"
                + "                                        </tr>\n"
                + "                                        <?php\n"
                + "                                        $index++;\n"
                + "                                    }\n"
                + "                                }\n"
                + "                                ?>");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                                </tbody>");
        controllerRegister_1_Importers.add("                            </table>");
        controllerRegister_1_Importers.add("                        </div>");
        controllerRegister_1_Importers.add("                    </div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                </div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("            </div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("        </div>");
        controllerRegister_1_Importers.add("        <?php include 'dashboard-footer.php'; ?>");
        controllerRegister_1_Importers.add("    </div>");
        controllerRegister_1_Importers.add("</div>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("<a class=\"scroll-to-top rounded\" href=\"#page-top\">\n"
                + "    <i class=\"fas fa-angle-up\"></i>\n"
                + "</a>\n"
                + "\n"
                + "<?php include 'forms/" + table.getFormFileName() + "'; ?>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("<script src=\"assets/vendor/jquery/jquery.min.js\"></script>\n"
                + "<script src=\"assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n"
                + "<script src=\"assets/vendor/jquery-easing/jquery.easing.min.js\"></script>\n"
                + "<script src=\"assets/js/sb-admin-2.min.js\"></script>\n"
                + "<script src=\"assets/vendor/datatables/jquery.dataTables.min.js\"></script>\n"
                + "<script src=\"assets/vendor/datatables/dataTables.bootstrap4.min.js\"></script>\n"
                + "<script src=\"assets/js/demo/datatables-demo.js\"></script>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("<script>");

        controllerRegister_1_Importers.add("    generateApplicationNumber();");
        controllerRegister_1_Importers.add("    function generateApplicationNumber() {\n"
                + "        $.ajax({\n"
                + "            type: \"GET\",\n"
                + "            url: \"../Controllers/" + table.getNameInCamelCase() + "/" + table.getFormIdGenerateControllerFileName() + "\",\n"
                + "            success: function (response) {\n"
                + "                $('#applicationNo').val(response);\n"
                + "            }\n"
                + "        });\n"
                + "    }");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("    function getRecords(id) {\n"
                + "        $.ajax({\n"
                + "            type: \"GET\",\n"
                + "            url: \"../Controllers/" + table.getNameInCamelCase() + "/" + table.getGetFormControllerFileName() + "?id=\" + id,\n"
                + "            success: function (response) {\n"
                + "                var data = JSON.parse(response);");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");

        Vector<String> s01 = new Vector<String>();
        Vector<String> s02 = new Vector<String>();
        Vector<String> s03 = new Vector<String>();
        s01.add("$('#form_type').val(2);");
        for (Column column : columns) {
            if (column.getSqlName().equals("created_at")) {

            } else if (column.getSqlName().equals("updated_at")) {
//              
            } else if (column.getSqlName().equals("status")) {
//              
            } else if (column.getSqlName().equals("form_status")) {

            } else if (column.getSqlName().equals("application_no")) {
                s01.add("$('#applicationNo').val(data." + column.getSqlName() + ");");
            } else if (column.getSqlName().equals("id")) {
                s01.add("$('#form_id').val(data." + column.getSqlName() + ");");
            } else {
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    s02.add(JSCodeGenerateController.defaultController.generateJSVariableForSetters(column));
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    s03.add(JSCodeGenerateController.defaultController.generateJSVariableForSetters(column));
                } else {
                    s01.add(JSCodeGenerateController.defaultController.generateJSVariableForSetters(column));
                }
            }
        }

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //BEGIN::Citizen Section");
        controllerRegister_1_Importers.add("");
        for (String s : s01) {
            controllerRegister_1_Importers.add(s);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //END::Citizen Section");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add(" <?php\n"
                + "                if ($_SESSION[\"auth\"]['type'] == 3 || $_SESSION[\"auth\"]['type'] == 2 || $_SESSION[\"auth\"]['type'] == 1) {\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //BEGIN::GS Section");
        controllerRegister_1_Importers.add("");
        for (String s : s02) {
            controllerRegister_1_Importers.add(s);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //END::GS Section");
        controllerRegister_1_Importers.add("                <?php\n"
                + "                }\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <?php\n"
                + "                if ($_SESSION[\"auth\"]['type'] == 2 || $_SESSION[\"auth\"]['type'] == 1) {\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //BEGIN::DS Section");
        controllerRegister_1_Importers.add("");
        for (String s : s03) {
            controllerRegister_1_Importers.add(s);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //END::DS Section");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <?php\n"
                + "                }\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("                $('#newform').modal('show');");
        controllerRegister_1_Importers.add("            }\n"
                + "        });\n"
                + "    }");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("    function clearForm() {");
        controllerRegister_1_Importers.add("        generateApplicationNumber();");
        controllerRegister_1_Importers.add("");

        s01 = new Vector<String>();
        s02 = new Vector<String>();
        s03 = new Vector<String>();
        for (Column column : columns) {
            if (column.getSqlName().equals("created_at")) {

            } else if (column.getSqlName().equals("updated_at")) {
//              
            } else if (column.getSqlName().equals("status")) {
//              
            } else if (column.getSqlName().equals("form_status")) {

            } else if (column.getSqlName().equals("application_no")) {

            } else if (column.getSqlName().equals("id")) {

            } else {
                if (column.getColumnHtmlSection().toLowerCase().equals("s2")) {
                    s02.add(JSCodeGenerateController.defaultController.generateJSVariableForNullSetters(column));
                } else if (column.getColumnHtmlSection().toLowerCase().equals("s3")) {
                    s03.add(JSCodeGenerateController.defaultController.generateJSVariableForNullSetters(column));
                } else {
                    s01.add(JSCodeGenerateController.defaultController.generateJSVariableForNullSetters(column));
                }
            }
        }

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //BEGIN::Citizen Section");
        controllerRegister_1_Importers.add("");
        for (String s : s01) {
            controllerRegister_1_Importers.add(s);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //END::Citizen Section");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add(" <?php\n"
                + "                if ($_SESSION[\"auth\"]['type'] == 3 || $_SESSION[\"auth\"]['type'] == 2 || $_SESSION[\"auth\"]['type'] == 1) {\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //BEGIN::GS Section");
        controllerRegister_1_Importers.add("");
        for (String s : s02) {
            controllerRegister_1_Importers.add(s);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //END::GS Section");
        controllerRegister_1_Importers.add("                <?php\n"
                + "                }\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <?php\n"
                + "                if ($_SESSION[\"auth\"]['type'] == 2 || $_SESSION[\"auth\"]['type'] == 1) {\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //BEGIN::DS Section");
        controllerRegister_1_Importers.add("");
        for (String s : s03) {
            controllerRegister_1_Importers.add(s);
        }
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                //END::DS Section");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("                <?php\n"
                + "                }\n"
                + "                ?>");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("    }");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("    function filterForm(obj) {\n"
                + "        let status = obj.value;\n"
                + "        location.replace('" + table.getListFileName() + "?filter='+status);\n"
                + "    }");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("");

        controllerRegister_1_Importers.add("</script>");

        controllerRegister_1_Importers.add("</body>");
        controllerRegister_1_Importers.add("");
        controllerRegister_1_Importers.add("</html>");

        //BEGIN::Writing
        this.createFile(formsListFile, table.getListFileName());
        this.writeToFile(formsListFile, controllerRegister_1_Importers);
        //END::Writing
    }

    public void generateDashboardSidebarFile(Vector<Table> tables, String formListsHtmlFolderPath) throws IOException {
        String formsHtmlPath = formListsHtmlFolderPath + "/dashboard-sidebar_1.php";
        System.out.println(formsHtmlPath);
        File formsListFile = new File(formsHtmlPath);
        Vector<String> controllerRegister_1_Importers = new Vector<>();
        controllerRegister_1_Importers.add("<ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">\n"
                + "    <a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"index.html\">\n"
                + "\n"
                + "        <div class=\"sidebar-brand-text mx-3\">Welcome</div>\n"
                + "    </a>\n"
                + "    <hr class=\"sidebar-divider my-0\">\n"
                + "    <li class=\"nav-item\">\n"
                + "        <a class=\"nav-link\" href=\"dashboard.php\">\n"
                + "            <i class=\"fas fa-fw fa-tachometer-alt\"></i>\n"
                + "            <span>Dashboard</span></a>\n"
                + "    </li>\n"
                + "    <hr class=\"sidebar-divider\">\n"
                + "    <div class=\"sidebar-heading\">\n"
                + "        Pages\n"
                + "    </div>");
        controllerRegister_1_Importers.add("    <li class=\"nav-item\">\n"
                + "        <a class=\"nav-link collapsed\" href=\"nic-form-list.php\">\n"
                + "            <i class=\"fa fa-file-text-o\"></i>\n"
                + "            <span>NIC</span>\n"
                + "        </a>\n"
                + "    </li>");

        for (Table table : tables) {
            controllerRegister_1_Importers.add("    <li class=\"nav-item\">\n"
                    + "        <a class=\"nav-link collapsed\" href=\"" + table.getListFileName() + "\">\n"
                    + "            <i class=\"fa fa-file-text-o\"></i>\n"
                    + "            <span>" + table.getTableNameToDisplay() + "</span>\n"
                    + "        </a>\n"
                    + "    </li>");
        }
        controllerRegister_1_Importers.add("    <hr class=\"sidebar-divider d-none d-md-block\">\n"
                + "    <div class=\"text-center d-none d-md-inline\">\n"
                + "        <button class=\"rounded-circle border-0\" id=\"sidebarToggle\"></button>\n"
                + "    </div>\n"
                + "\n"
                + "</ul>");
        //BEGIN::Writing
        this.createFile(formsListFile, "dashboard-sidebar_1.php");
        this.writeToFile(formsListFile, controllerRegister_1_Importers);
        //END::Writing
    }

}
