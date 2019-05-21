package com.sales.survey.service;

import org.springframework.web.multipart.MultipartFile;

import com.sales.survey.transport.SalesSurveyData;

public interface SalesSurveyService {

	public SalesSurveyData survey(MultipartFile[] fileDatas, String absolutePath);
	
}
