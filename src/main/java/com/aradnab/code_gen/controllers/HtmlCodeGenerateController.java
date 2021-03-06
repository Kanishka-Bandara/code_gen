/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aradnab.code_gen.controllers;

import com.aradnab.code_gen.models.Column;
import com.aradnab.code_gen.models.Table;

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
        switch (section) {
            case 1:
                html += "                        <h6 for=\"\">Section 01: For Applicant</h6>\n";
                break;
            case 2:
                html += "                        <h6 for=\"\">Section 02: For Grama Niladari</h6>\n";
                break;
            default:
                html += "                        <h6 for=\"\">Section 03: For official frank in Divisional Secretariat Office</h6>\n";
                break;
        }

        html += "                    </div>\n"
                + "                    <div class=\"col-md-12\">\n"
                + "                        <hr>\n"
                + "                    </div>";

        return html;
    }

    public String generateHtmlField(Column c) {

        if (c.getSqlName().toLowerCase().contains("gender")) {
            return "                    <div class=\"col-md-12 mt-3\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                        <label for=\"\"><strong>" + (c.getColumnHtmlNumber() == 0 ? "" : c.getColumnHtmlNumber() + ".") + "Gender</strong></label>\n"
                    + "                        <br>\n"
                    + "                        <div class=\"form-check form-check-inline\">\n"
                    + "                            <input required class=\"form-check-input\" type=\"radio\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "1\" value=\"1\">\n"
                    + "                            <label class=\"form-check-label\" for=\"" + c.getColumnJsId() + "1\">Male</label>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"form-check form-check-inline\">\n"
                    + "                            <input required class=\"form-check-input\" type=\"radio\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "2\" value=\"2\">\n"
                    + "                            <label class=\"form-check-label\" for=\"" + c.getColumnJsId() + "2\">Female</label>\n"
                    + "                        </div>\n"
                    + "                    </div>";
        } else if (c.getSqlName().toLowerCase().contains("civil_status")) {
            return "<div class=\"col-md-12 mt-3\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                        <label for=\"\"><strong>" + (c.getColumnHtmlNumber() == 0 ? "" : c.getColumnHtmlNumber() + ".") + "Civil Status</strong></label>\n"
                    + "                        <br>\n"
                    + "                        <div class=\"form-check form-check-inline\">\n"
                    + "                            <input required class=\"form-check-input\" type=\"radio\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "1\"\n"
                    + "                                   value=\"1\">\n"
                    + "                            <label class=\"form-check-label\" for=\"" + c.getColumnJsId() + "1\">Married</label>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"form-check form-check-inline\">\n"
                    + "                            <input required class=\"form-check-input\" type=\"radio\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "2\"\n"
                    + "                                   value=\"2\">\n"
                    + "                            <label class=\"form-check-label\" for=\"" + c.getColumnJsId() + "2\">Single</label>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"form-check form-check-inline\">\n"
                    + "                            <input required class=\"form-check-input\" type=\"radio\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "3\"\n"
                    + "                                   value=\"3\">\n"
                    + "                            <label class=\"form-check-label\" for=\"" + c.getColumnJsId() + "3\">Widowed</label>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"form-check form-check-inline\">\n"
                    + "                            <input required class=\"form-check-input\" type=\"radio\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "4\"\n"
                    + "                                   value=\"4\">\n"
                    + "                            <label class=\"form-check-label\" for=\"" + c.getColumnJsId() + "4\">Divorced</label>\n"
                    + "                        </div>\n"
                    + "                    </div>";
        } else if (c.getSqlName().toLowerCase().contains("district")) {
            return "<div class=\"col-md-12 mt-3\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                        <div class=\"form-group row\">\n"
                    + "                            <label class=\"col-md-4 col-form-label\">" + c.getColumnHtmlNameToDisplay() + "</label>\n"
                    + "                            <div class=\"col-md-4\">\n"
                    + "                                <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                    <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                    <?php\n"
                    + "                                    foreach (getDistrictDivitions() as $keyDD => $valueDD) {\n"
                    + "                                        ?>\n"
                    + "                                        <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                        <?php\n"
                    + "                                    }\n"
                    + "                                    ?>\n"
                    + "                                </select>\n"
                    + "                            </div>\n"
                    + "                        </div>"
                    + "                    </div>";
        } else if (c.getSqlName().toLowerCase().contains("gn_division")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small>G.N Number & G.N Division</small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getGD() as $keyGD => $valueGD1) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyGD ?>\"><?= $valueGD1[0] ?> - <?= $valueGD1[1] ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("election_division")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small>Election Division</small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getElectionDivisions() as $keyGD => $valueGD1) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyGD ?>\"><?= $valueGD1[0] ?> - <?= $valueGD1[1] ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("ds_division")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small>D.S. Division</small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getDistrictDivitions() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_purpose_of_second_copy_of_identity_card")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small><strong>1. Purpose Of Second Copy Of Identity Card</strong></small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getPurposesOfSecondCopyOfIdentityCard() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_living_details")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small><strong>10. Living Details/strong></small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getLivingDetails() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_married_status")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small><strong>5. Married Status</strong></small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getMarriedStatus() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_another_subsidies")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small><strong>14. Another Subsidies</strong></small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getAnotherSubsidies() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_condition_of_place_get_soil")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small><strong>1. Condition Of Place Get Soil (Get Soil)</strong></small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getConditionOfPlaceForSoilTransport() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_condition_of_transport_place")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small><strong>6. Condition Of Transport Place</strong></small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getConditionOfPlaceForSoilTransport() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        } else if (c.getSqlName().toLowerCase().contains("drp_reason_for_need_the_land")) {
            return "                        <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                    + "                            <label><small>Reason For Need The Land</small></label>\n"
                    + "                            <select required class=\"form-control\" name=\"" + c.getColumnJsName() + "\" id=\"" + c.getColumnJsId() + "\">\n"
                    + "                                <option selected disabled value=\"\">-Select-</option>\n"
                    + "                                <?php\n"
                    + "                                foreach (getReasonsForNeedTheLand() as $keyDD => $valueDD) {\n"
                    + "                                    ?>\n"
                    + "                                    <option value=\"<?= $keyDD ?>\"><?= $valueDD ?></option>\n"
                    + "                                    <?php\n"
                    + "                                }\n"
                    + "                                ?>\n"
                    + "                            </select>\n"
                    + "                        </div>";
        }

        String html = "                    <div class=\"col-md-12\" id = \"" + c.getColumnJsSectionId() + "\">\n"
                + "                        <div class=\"form-group row\">";
        if (c.getColumnHtmlNumber() == 0) {
            html += "                            <label class=\"col-md-4 col-form-label\" for=\"" + c.getColumnNameInCamelCase() + "\" id = \"" + c.getColumnHtmlLabelId() + "\">" + c.getColumnHtmlNameToDisplay() + "</label>";
            html += "                            <div class=\"col-md-8\">";
        } else {
            html += "                            <label class=\"col-md-4 col-form-label\" for=\"" + c.getColumnNameInCamelCase() + "\" id = \"" + c.getColumnHtmlLabelId() + "\"><strong>" + c.getColumnHtmlNumber() + ". " + c.getColumnHtmlNameToDisplay() + "</strong></label>";
            html += "                            <div class=\"col-md-8\">";
        }
        if (c.getColumnHtmlFieldType().equals("email")) {
            html += "                                <input required id=\"" + c.getColumnJsId() + "\" name=\"" + c.getColumnJsName() + "\" type=\"email\" class=\"form-control\">";
        } else if (c.getColumnHtmlFieldType().equals("img")) {
            html += "                                    <label for=\"" + c.getColumnJsId() + "\" id=\"label_signature\" class=\"btn btn-primary w-100\">Upload Image</label>";
            html += "                                    <input id=\"" + c.getColumnJsId() + "\" name=\"" + c.getColumnJsName() + "\" class=\"d-none\" type=\"file\" onchange='setImage(this,\"" + c.getColumnJsImageSrcPreviewId() + "\")'>";
        } else if (c.getColumnHtmlFieldType().equals("drp")) {

        } else if (c.getColumnHtmlFieldType().equals("cb")) {

        } else if (c.getColumnHtmlFieldType().equals("rb")) {

        } else if (c.getColumnHtmlFieldType().equals("dte")) {
            html += "                                <input required id=\"" + c.getColumnJsId() + "\" name=\"" + c.getColumnJsName() + "\" type=\"date\" class=\"form-control\">";
        } else if (c.getColumnHtmlFieldType().equals("tel")) {
            html += "                                <input required id=\"" + c.getColumnJsId() + "\" name=\"" + c.getColumnJsName() + "\" type=\"tel\" class=\"form-control\">";
        } else {
            html += "                                <input required id=\"" + c.getColumnJsId() + "\" name=\"" + c.getColumnJsName() + "\" type=\"text\" class=\"form-control\">";
        }

        html += "";
        html += "";
        html += "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>";

        if (c.getColumnHtmlFieldType().equals("img")) {
            if (c.getSqlName().toLowerCase().trim().contains("signature")) {
                html += "<div class=\"col-md-12 mb-3\" id=\"" + c.getColumnJsImagePreviewSectionId() + "\">\n"
                    + "                        <label for=\"" + c.getColumnJsImageSrcPreviewId() + "\">Attached " + (c.getSqlName().toLowerCase().contains("signature") ? "Signature" : "Image") + "</label>\n"
                    + "                        <br>\n"
                    + "                        <img id=\"" + c.getColumnJsImageSrcPreviewId() + "\" name=\"" + c.getColumnJsImageSrcPreviewName() + "\" src=\"/grama/Views/assets/img/sign_here.jpg\" alt=\"...\" class=\"img-thumbnail\" style=\"height: 200px;width: auto\">\n"
                    + "                    </div>";
            } else {
                html += "<div class=\"col-md-12 mb-3\" id=\"" + c.getColumnJsImagePreviewSectionId() + "\">\n"
                    + "                        <label for=\"" + c.getColumnJsImageSrcPreviewId() + "\">Attached " + (c.getSqlName().toLowerCase().contains("signature") ? "Signature" : "Image") + "</label>\n"
                    + "                        <br>\n"
                    + "                        <img id=\"" + c.getColumnJsImageSrcPreviewId() + "\" name=\"" + c.getColumnJsImageSrcPreviewName() + "\" src=\"/grama/Views/assets/img/placeholder-image.png\" alt=\"...\" class=\"img-thumbnail\" style=\"height: 200px;width: auto\">\n"
                    + "                    </div>";
            }
        }
        return html;
    }

    public String generateApplicationNoFormFieldSection() {
    String html = "                    <div class=\"row d-flex flex-row-reverse\">\n"
                + "                        <div class=\"col-md-5\">\n"
                + "                            <label for=\"applicationNo\"><small>Application No</small></label>\n"
                + "                            <div class=\"input-group mb-2\">\n"
                + "                                <!--                                    <div class=\"input-group-prepend\">-->\n"
                + "                                <!--                                        <a onclick=\"generateApplicationNumber()\" class=\"btn btn-sm btn-info input-group-text\">Generate</a>-->\n"
                + "                                <!--                                    </div>-->\n"
                + "                                <input required name=\"applicationNo\" id=\"applicationNo\" readonly type=\"text\"\n"
                + "                                       class=\"form-control\">\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>";

        return html;
    }
    
    public String generateDataVariableForListTableData(Table table){
        String firstName = null;
        String lastName = null;
        String fullName = null;
        String nic = null;
        
        for (Column column : table.getColumns()) {
            if (column.getSqlName().contains("first_name")) {
                firstName = column.getSqlName();
            } 
            if (column.getSqlName().contains("last_name")) {
                lastName = column.getSqlName();
            }
            if (column.getSqlName().contains("full_name")) {
                firstName = column.getSqlName();
            }
            if (column.getSqlName().contains("national_identity_card_number")) {
                nic = column.getSqlName();
            }
        }
        
        if ((firstName!=null)&&(lastName!=null)) {
            return "$value['"+firstName+"'] . ' ' . $value['"+lastName+"']";
        }else if (fullName!=null) {
            return "$value['"+fullName+"']";
        }else if (nic!=null) {
            return "$value['"+nic+"']";
        }else if (firstName!=null) {
            return "$value['"+firstName+"']";
        }else if (lastName!=null) {
            return "$value['"+lastName+"']";
        }
        return "";
    }

}
