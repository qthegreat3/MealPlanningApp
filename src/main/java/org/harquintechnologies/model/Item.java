package org.harquintechnologies.model;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private String name;
	private String qty;
	private List<Grocery> groceryList = new ArrayList<>();
	
	public void addGrocery(Grocery grocery)
	{
		this.groceryList.add(grocery);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public List<Grocery> getGroceryList() {
		return groceryList;
	}
	public void setGroceryList(List<Grocery> groceryList) {
		this.groceryList = groceryList;
	}
	
	
}
