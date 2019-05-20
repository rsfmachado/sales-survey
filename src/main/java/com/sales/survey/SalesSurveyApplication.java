package com.sales.survey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesSurveyApplication {

	private static final Logger logger = LoggerFactory.getLogger(SalesSurveyApplication.class);
	
	public static void main(String[] args) {
		logger.debug("[SALES_SURVEY] main(String[] args)");
		SpringApplication.run(SalesSurveyApplication.class, args);
	}

}
