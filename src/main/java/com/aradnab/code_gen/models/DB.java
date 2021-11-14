/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author kanishka
 */
public class DB {

    private static Connection c;
    private static String ip = "localhost";
    private static String port = "3306";
    private static String dbName = "grama";
    private static String username = "root";
    private static String password = "12345678";
    private static String mysqlDumpPath;

    //Getters=======================
    public static String getIp() {
        return ip;
    }

    public static String getPort() {
        return port;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getUsername() {
        return username;
    }
    //=========================

    //Setters===================
    public static String getPassword() {
        return password;
    }

    public static void setIp(String ip) {
        DB.ip = ip;
    }

    public static void setPort(String port) {
        DB.port = port;
    }

    public static void setDbName(String dbName) {
        DB.dbName = dbName;
    }

    public static void setUsername(String username) {
        DB.username = username;
    }

    public static void setPassword(String password) {
        DB.password = password;
    }

    //=================================
    public static void setNewConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8", "" + username + "", "" + password + "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int iud(String querry) throws Exception {
        if (c == null) {
            setNewConnection();
        }
        return c.createStatement().executeUpdate(querry);
    }

    public static ResultSet search(String querry) throws Exception {
        if (c == null) {
            setNewConnection();
        }
        return c.createStatement().executeQuery(querry);
    }

    public static Connection getConnection() throws Exception {
        if (c == null) {
            setNewConnection();
        }

        return c;
    }
    public static DatabaseMetaData getDatabaseMetaData() throws Exception {
        if (c == null) {
            setNewConnection();
        }
        return c.getMetaData();
    }
    public static List<String> getTables() throws Exception {
        if (c == null) {
            setNewConnection();
        }
        ResultSet rs = c.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        List<String> l = new ArrayList<>();
        while (rs.next()) {
         String tblName = rs.getString("TABLE_NAME");
         String tblCat = rs.getString("TABLE_CAT");
            if (tblCat.toLowerCase().equals(dbName.toLowerCase())) {
                l.add(tblName);
            }
      }
        return l;
    }
}