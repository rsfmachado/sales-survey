package com.sales.survey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sales.survey.manager.IOManager;
import com.sales.survey.processor.SurveyProcessor;
import com.sales.survey.transport.SalesSurveyData;
import com.sales.survey.transport.SalesSurveyResult;

@Service
public class SalesSurveyServiceImpl implements SalesSurveyService {

	private static final Logger logger = LoggerFactory.getLogger(SalesSurveyServiceImpl.class);
	
	@Autowired
	private IOManager ioManager;
	
	@Autowired
	private SurveyProcessor surveyProcessor;
	
	@Override
	public SalesSurveyResult survey(MultipartFile[] fileDatas, String absolutePath) {
		logger.debug("[SALES_SURVEY]: survey(fileDatas, absolutePath).");
		
		SalesSurveyData extractedData = ioManager.extractInputData(fileDatas, absolutePath);
		
		SalesSurveyResult surveyResult = surveyProcessor.executeSurvey(extractedData,absolutePath);
		
		return surveyResult;
	}

}
