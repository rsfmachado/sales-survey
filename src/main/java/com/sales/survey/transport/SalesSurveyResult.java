package com.sales.survey.transport;

public class SalesSurveyResult {

	private String numberOfClients;
	private String numberOfSellers;
	private String bestSaleID;
	private String worstSellerName;
	private String resultFileName;
	private String resultFolderPath;
	
	public String getNumberOfClients() {
		return numberOfClients;
	}
	public void setNumberOfClients(String numberOfClients) {
		this.numberOfClients = numberOfClients;
	}
	public String getNumberOfSellers() {
		return numberOfSellers;
	}
	public void setNumberOfSellers(String numberOfSellers) {
		this.numberOfSellers = numberOfSellers;
	}
	public String getBestSaleID() {
		return bestSaleID;
	}
	public void setBestSaleID(String bestSaleID) {
		this.bestSaleID = bestSaleID;
	}
	public String getWorstSellerName() {
		return worstSellerName;
	}
	public void setWorstSellerName(String worstSellerName) {
		this.worstSellerName = worstSellerName;
	}
	public String getResultFileName() {
		return resultFileName;
	}
	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}
	public String getResultFolderPath() {
		return resultFolderPath;
	}
	public void setResultFolderPath(String resultFolderPath) {
		this.resultFolderPath = resultFolderPath;
	}

}
