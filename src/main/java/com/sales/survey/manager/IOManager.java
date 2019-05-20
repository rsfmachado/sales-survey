package com.sales.survey.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sales.survey.layout.ClientLayout;
import com.sales.survey.layout.SellerLayout;
import com.sales.survey.transport.SalesSurveyData;

@Component
public class IOManager {
	
	private static final Logger logger = LoggerFactory.getLogger(IOManager.class);

	public SalesSurveyData extractInputData(){
		logger.debug("[SALES_SURVEY]: extractImputData().");
		
		SalesSurveyData surveyData = new SalesSurveyData();
		BufferedReader reader;
		try {
			
			/* TODO
			 * - Input files folder: %HOMEPATH%/data/in
			 * - Check only .dat files
			 * - Make this method more generic?
			*/
			reader = new BufferedReader(new FileReader("/Users/rsfmachado/Development/PortfolioProject/IBM-Sicredi/data/in/example_1.dat"));
			String line = reader.readLine();
			
			while (line != null) {
				logger.debug("[SALES_SURVEY]: extractImputData(): line = " + line);
				String[] lineFields = line.split("ç");
				
				surveyData = generateSurveyData(lineFields);
				
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return surveyData;
	}
	
	private SalesSurveyData generateSurveyData(String[] entry) {
		
		SalesSurveyData surveyData = new SalesSurveyData();
		
		String layout = entry[0];
		if (layout.equals("001")) {
			logger.debug("[SALES_SURVEY]: extractImputData(): layout = seller");
			SellerLayout seller = new SellerLayout(entry[1],entry[2],entry[3]);
			surveyData.getSellerList().add(seller);
		}
		if (layout.equals("002")) {
			logger.debug("[SALES_SURVEY]: extractImputData(): layout = client");
			ClientLayout client = new ClientLayout(entry[1],entry[2],entry[3]);
			surveyData.getClientList().add(client);
		}
		if (layout.equals("003")) {
			logger.debug("[SALES_SURVEY]: extractImputData(): layout = sale");
			
			//SaleLayout client = new SaleLayout(entry[1],entry[2],entry[3]);
		}
		
		return surveyData;
	}
	
}
