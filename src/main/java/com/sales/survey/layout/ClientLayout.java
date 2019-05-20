package com.sales.survey.layout;

public class ClientLayout extends Layout{

	private String cnpj;
	private String Name;
	private String businessArea;
	
	public ClientLayout(String cnpj, String name, String businessArea) {
		super();
		this.cnpj = cnpj;
		Name = name;
		this.businessArea = businessArea;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
}
