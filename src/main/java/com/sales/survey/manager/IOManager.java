package com.sales.survey.manager;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sales.survey.layout.ClientLayout;
import com.sales.survey.layout.ItemLayout;
import com.sales.survey.layout.SaleLayout;
import com.sales.survey.layout.SellerLayout;
import com.sales.survey.transport.SalesSurveyData;

@Component
public class IOManager {
	
	private static final Logger logger = LoggerFactory.getLogger(IOManager.class);

	public SalesSurveyData extractInputData(MultipartFile[] fileDatas, String absolutePath){
		logger.debug("[SALES_SURVEY]: survey(fileDatas, absolutePath).");
		
		SalesSurveyData surveyData = new SalesSurveyData();
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();
		 
		for (MultipartFile fileData : fileDatas) {
		 
			// Client File Name
			String name = fileData.getOriginalFilename();
			logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm). Client File Name = " + name);
		 
		    	if (name != null && name.length() > 0) {
		    		try {
		    			// Create the file at server
		    			File serverFile = new File(absolutePath + File.separator + name);
		 
		    			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));		    			
		    			stream.write(fileData.getBytes());		    			
		    			stream.close();
		    			uploadedFiles.add(serverFile);
		    			logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm). Write file = " + serverFile);
		    			
		    			BufferedReader reader;
		    			try {
		    				
		    				/* TODO
		    				 * - Input files folder: %HOMEPATH%/data/in
		    				 * - Check only .dat files
		    				 * - Make this method more generic?
		    				*/
		    				reader = new BufferedReader(new FileReader(serverFile));
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
		    			
		    			
		    		} catch (Exception e) {
		    			logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm). Error Write file = " + name);
		            failedFiles.add(name);
		        }
		    	}
		}
		
		surveyData.getUploadedFiles().addAll(uploadedFiles);
		surveyData.getFailedFiles().addAll(failedFiles);
		
		return surveyData;	
	}
	
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
