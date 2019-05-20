package com.sales.survey.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sales.survey.layout.ClientLayout;
import com.sales.survey.layout.ItemLayout;
import com.sales.survey.layout.SaleLayout;
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
			reader = new BufferedReader(new FileReader("C:\\Users\\ricardo_machado\\Development\\Portfolio\\projects\\ibm-sicredi\\data\\in\\example_1.dat"));
			String line = reader.readLine();
			
			while (line != null) {
				logger.debug("[SALES_SURVEY]: extractImputData(): line = " + line);
				String[] lineFields = line.split("ç");
				
				surveyData = generateSurveyData(lineFields);
				
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return surveyData;
	}
	
	private SalesSurveyData generateSurveyData(String[] entry) {
		
		SalesSurveyData surveyData = new SalesSurveyData();
		
		String layout = entry[0];
		if (layout.equals("001")) {
			logger.debug("[SALES_SURVEY]: extractImputData(): layout = seller");
			SellerLayout seller = new SellerLayout(entry[1],entry[2],entry[3]);
			logger.debug("[SALES_SURVEY]: extractImputData(): SellerLayout ["+seller.getCpf()+","+seller.getName()+","+seller.getSalary()+"]");
			surveyData.getSellerList().add(seller);
		}
		
		if (layout.equals("002")) {
			logger.debug("[SALES_SURVEY]: extractImputData(): layout = client");
			ClientLayout client = new ClientLayout(entry[1],entry[2],entry[3]);
			logger.debug("[SALES_SURVEY]: extractImputData(): ClientLayout ["+client.getCnpj()+","+client.getName()+","+client.getBusinessArea()+"]");
			surveyData.getClientList().add(client);
		}
		
		if (layout.equals("003")) {
			logger.debug("[SALES_SURVEY]: extractImputData(): layout = sale");
			
			List<ItemLayout> listItems = new ArrayList<ItemLayout>();
			String[] itemsInput = entry[2].replaceAll("[\\[\\](){}]","").split(",");
			for (int i = 0; i < itemsInput.length; i++) {
				String[] itemField = itemsInput[i].split("-");
				ItemLayout itemLayout = new ItemLayout(itemField[0], itemField[1], itemField[2]);
				logger.debug("[SALES_SURVEY]: extractImputData(): 	ItemLayout ["+itemLayout.getId()+","+itemLayout.getQuantity()+","+itemLayout.getPrice()+"]");
				listItems.add(itemLayout);				
			}

			SaleLayout sale = new SaleLayout(entry[1],listItems,entry[3]);
			logger.debug("[SALES_SURVEY]: extractImputData(): SaleLayout ["+sale.getId()+", itemsList,"+sale.getSeller()+"]");
			surveyData.getSalesList().add(sale);
		}
		
		return surveyData;
	}
	
}
