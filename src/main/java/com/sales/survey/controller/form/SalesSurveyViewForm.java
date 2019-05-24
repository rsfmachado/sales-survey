package com.sales.survey.controller.form;

import org.springframework.web.multipart.MultipartFile;

public class SalesSurveyViewForm {

    private MultipartFile[] fileDatas;
 
    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }
 
    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
}
