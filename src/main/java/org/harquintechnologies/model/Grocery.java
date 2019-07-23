package org.harquintechnologies.model;

public class Grocery {
	private String name;
	private double qty;
	private String qtyType;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getQty() {
		return qty;
	}
	
	public void setQty(double qty) {
		this.qty = qty;
	}
	
	public String getQtyType() {
		return qtyType;
	}
	
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}
}
