package com.sales.survey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.survey.manager.IOManager;
import com.sales.survey.transport.SalesSurveyData;

@Service
public class SalesSurveyServiceImpl implements SalesSurveyService {

	private static final Logger logger = LoggerFactory.getLogger(SalesSurveyServiceImpl.class);
	
	@Autowired
	private IOManager ioManager;
	
	@Override
	public String survey() {
		logger.debug("[SALES_SURVEY]: survey().");
		
		// TODO IOManager read imput directory in search for dat files
		SalesSurveyData extractedData = ioManager.extractInputData();
		
		return "Survey development.";
	}

}
