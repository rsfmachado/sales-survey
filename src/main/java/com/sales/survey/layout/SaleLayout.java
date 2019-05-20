package com.sales.survey.layout;

import java.util.List;

public class SaleLayout extends Layout {

	private String id;
	private List<ItemLayout> items;
	private String seller;
	
	public SaleLayout(String id, List<ItemLayout> items, String seller) {
		super();
		this.id = id;
		this.items = items;
		this.seller = seller;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ItemLayout> getItems() {
		return items;
	}
	public void setItems(List<ItemLayout> items) {
		this.items = items;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
}
