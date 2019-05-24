package com.sales.survey.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sales.survey.layout.ItemLayout;
import com.sales.survey.layout.SaleLayout;
import com.sales.survey.transport.SalesSurveyData;
import com.sales.survey.transport.SalesSurveyResult;

@Component
public class SurveyProcessor {

	private static final Logger logger = LoggerFactory.getLogger(SurveyProcessor.class);
	
	public SalesSurveyResult executeSurvey(SalesSurveyData surveyData, String absolutePath) {
		logger.debug("[SALES_SURVEY]: executeSurvey(surveyData).");
		
		SalesSurveyResult result = new SalesSurveyResult();
		
		String numberOfClients = String.valueOf(surveyData.getClientList().size());
		result.setNumberOfClients(numberOfClients);
		logger.debug("[SALES_SURVEY]: executeSurvey(surveyData) Number of Clients = " + result.getNumberOfClients());
		
		String numberOfSellers = String.valueOf(surveyData.getSellerList().size());
		result.setNumberOfSellers(numberOfSellers);
		logger.debug("[SALES_SURVEY]: executeSurvey(surveyData) Number of Sellers = " + result.getNumberOfSellers());
		
		String bestSaleId = getBestSale(surveyData.getSalesList());
		result.setBestSaleID(bestSaleId);
		logger.debug("[SALES_SURVEY]: executeSurvey(surveyData) Best Sale ID = " + result.getBestSaleID());
		
		String worstSellerName = getWorstSellerName(surveyData.getSalesList());
		result.setWorstSellerName(worstSellerName);
		logger.debug("[SALES_SURVEY]: executeSurvey(surveyData) Worst Seller = " + result.getWorstSellerName());
		
		String fileName = "sales_survey_result.done.dat";
		saveReportFile(numberOfClients, numberOfSellers, bestSaleId, worstSellerName, absolutePath, fileName);
		result.setResultFileName(fileName);
		result.setResultFolderPath(absolutePath);
		
		return result;
		
	}
	
	private String getBestSale(List<SaleLayout> salesList) {
		
		String bestSaleId = "";
		
		double higherSale = 0;
		for (SaleLayout sale : salesList) {
			double saleAmount = calculateSaleAmount(sale.getItems());
			if (saleAmount >= higherSale) { 
				bestSaleId = sale.getId();
				higherSale = saleAmount;
			}
		}
		
		return bestSaleId;
	}
	
	private String getWorstSellerName(List<SaleLayout> salesList) {
		
		String worstSellerName = "";
		
		double lowerSale = 0;
		for (SaleLayout sale : salesList) {
			double saleAmount = calculateSaleAmount(sale.getItems());
			if (lowerSale == 0) {
				lowerSale = saleAmount;
				worstSellerName = sale.getSeller();
			} else {
				if (saleAmount <= lowerSale) { 
					worstSellerName = sale.getSeller();
					lowerSale = saleAmount;
				}
			}
		}
		
		return worstSellerName;
	}
	
	private double calculateSaleAmount(List<ItemLayout> items) {
		double amount = 0;
		
		for (ItemLayout item : items) {
			amount = amount + (Double.parseDouble(item.getPrice()) * Double.parseDouble(item.getQuantity()));
		}
		
		return amount;
		
	}
	
	private void saveReportFile(String numberOfClients, String numberOfSellers, String bestSaleId, String worstSellerName, String absolutePath, String fileName) {
		
		String reportFile = absolutePath + File.pathSeparator + fileName;
		
		FileWriter fw;
		try {
			fw = new FileWriter(reportFile);

			fw.write("Total de Clientes: " + numberOfClients);
			fw.write("Total de Vendedores: " + numberOfSellers);
			fw.write("Id da Melhor Venda: " + bestSaleId);
			fw.write("Pior vendedor" + worstSellerName);
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
}
