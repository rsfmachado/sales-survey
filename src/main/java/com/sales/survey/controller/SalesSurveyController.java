package com.sales.survey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.survey.service.SalesSurveyService;

@RestController
public class SalesSurveyController {

	private static final Logger logger = LoggerFactory.getLogger(SalesSurveyController.class);
	
	@Autowired
	private SalesSurveyService salesSurveyService;
	
	@GetMapping("/survey")
	public String survey() {
		logger.debug("[SALES_SURVEY]: survey().");
		return salesSurveyService.survey();
	}
	
}