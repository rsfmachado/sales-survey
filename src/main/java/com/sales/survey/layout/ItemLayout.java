package com.sales.survey.layout;

public class ItemLayout extends Layout {

	private String id;
	private String quantity;
	private String price;
	
	public ItemLayout(String id, String quantity, String price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
