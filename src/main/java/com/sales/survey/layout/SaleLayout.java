package com.sales.survey.layout;

public class SaleLayout extends Layout {

	private String id;
	private ItemLayout item;
	private String seller;
	
	public SaleLayout(String id, ItemLayout item, String seller) {
		super();
		this.id = id;
		this.item = item;
		this.seller = seller;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ItemLayout getItem() {
		return item;
	}
	public void setItem(ItemLayout item) {
		this.item = item;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
}
