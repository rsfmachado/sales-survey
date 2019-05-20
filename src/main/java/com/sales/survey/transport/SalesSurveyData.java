package com.sales.survey.transport;

import java.util.ArrayList;
import java.util.List;

import com.sales.survey.layout.ClientLayout;
import com.sales.survey.layout.SaleLayout;
import com.sales.survey.layout.SellerLayout;

public class SalesSurveyData {
	
	private List<SellerLayout> sellerList;
	private List<ClientLayout> clientList;
	private List<SaleLayout> salesList;
	
	public SalesSurveyData() {
		super();
		this.sellerList = new ArrayList<SellerLayout>();
		this.clientList = new ArrayList<ClientLayout>();
		this.salesList = new ArrayList<SaleLayout>();
	}
	
	public SalesSurveyData(List<SellerLayout> sellerList, List<ClientLayout> clientList, List<SaleLayout> salesList) {
		super();
		this.sellerList = sellerList;
		this.clientList = clientList;
		this.salesList = salesList;
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

}
