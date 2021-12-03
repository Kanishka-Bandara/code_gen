/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

/**
 *
 * @author kanishka
 */
public class HtmlCodeGenerateController {

    public static HtmlCodeGenerateController defaultController = new HtmlCodeGenerateController();

    public String getSection01ForApplicant() {
        return this.generateSectionHeader(1);
    }

    public String getSection02ForGS() {
        return this.generateSectionHeader(2);
    }

    public String getSection03ForDS() {
        return this.generateSectionHeader(3);
    }

    private String generateSectionHeader(int section) {
        String html = "                    <div class=\"col-md-12\">\n"
                + "                        <hr>\n"
                + "                    </div>\n"
                + "                    <div class=\"col-md-12 mt-3\">\n";
        if (section == 1) {
            html += "                        <h6 for=\"\">Section 01: For Applicant</h6>\n";
        } else if (section == 2) {
            html += "                        <h6 for=\"\">Section 02: For Grama Niladari</h6>\n";
        } else {
            html += "                        <h6 for=\"\">Section 03: For official frank in Divisional Secretariat Office</h6>\n";
        }

        html += "                    </div>\n"
                + "                    <div class=\"col-md-12\">\n"
                + "                        <hr>\n"
                + "                    </div>";

        return html;
    }

}
