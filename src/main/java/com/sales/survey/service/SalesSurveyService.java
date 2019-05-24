package com.sales.survey.service;

import org.springframework.web.multipart.MultipartFile;

import com.sales.survey.transport.SalesSurveyResult;

public interface SalesSurveyService {

	public SalesSurveyResult survey(MultipartFile[] fileDatas, String absolutePath);
	
}
