package com.sales.survey.transport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sales.survey.layout.ClientLayout;
import com.sales.survey.layout.SaleLayout;
import com.sales.survey.layout.SellerLayout;

public class SalesSurveyData {
	
	private List<SellerLayout> sellerList;
	private List<ClientLayout> clientList;
	private List<SaleLayout> salesList;
	
	private List<File> uploadedFiles;
	private List<String> failedFiles;
	
	public SalesSurveyData() {
		super();
		this.sellerList = new ArrayList<SellerLayout>();
		this.clientList = new ArrayList<ClientLayout>();
		this.salesList = new ArrayList<SaleLayout>();
		this.setUploadedFiles(new ArrayList<File>());
		this.setFailedFiles(new ArrayList<String>());
	}
	
	public SalesSurveyData(List<SellerLayout> sellerList, List<ClientLayout> clientList, List<SaleLayout> salesList, ArrayList<File> uploadedFiles, ArrayList<String> failedFiles) {
		super();
		this.sellerList = sellerList;
		this.clientList = clientList;
		this.salesList = salesList;
		this.uploadedFiles = uploadedFiles;
		this.failedFiles =failedFiles;
	}
	
	public List<SellerLayout> getSellerList() {
		if (this.sellerList==null)
			this.sellerList = new ArrayList<SellerLayout>();			
		return sellerList;
	}
	public void setSellerList(List<SellerLayout> sellerList) {
		this.sellerList = sellerList;
	}
	public List<ClientLayout> getClientList() {
		if (this.clientList==null)
			this.clientList = new ArrayList<ClientLayout>();
		return clientList;
	}
	public void setClientList(List<ClientLayout> clientList) {
		this.clientList = clientList;
	}
	public List<SaleLayout> getSalesList() {
		if (this.salesList==null)
			this.salesList = new ArrayList<SaleLayout>();
		return salesList;
	}
	public void setSalesList(List<SaleLayout> salesList) {
		this.salesList = salesList;
	}
	public List<File> getUploadedFiles() {
		if (this.uploadedFiles==null)
			this.uploadedFiles = new ArrayList<File>();
		return uploadedFiles;
	}
	public void setUploadedFiles(List<File> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	public List<String> getFailedFiles() {
		if (this.failedFiles==null)
			this.failedFiles = new ArrayList<String>();
		return failedFiles;
	}
	public void setFailedFiles(List<String> failedFiles) {
		this.failedFiles = failedFiles;
	}

}
