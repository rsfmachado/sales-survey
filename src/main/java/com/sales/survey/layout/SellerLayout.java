package com.sales.survey.layout;

public class SellerLayout extends Layout {

	private String cpf;
	private String name;
	private String salary;
	
	public SellerLayout(String cpf, String name, String salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
}
