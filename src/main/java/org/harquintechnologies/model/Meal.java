package org.harquintechnologies.model;

import java.util.ArrayList;
import java.util.List;

public class Meal {

	private int calories;
	private String type;
	private int proteinInGrams;
	private int fatInGrams;
	private int carbsInGrams;
	private List<Item> itemList = new ArrayList<>();
	
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getProteinInGrams() {
		return proteinInGrams;
	}
	public void setProteinInGrams(int proteinInGrams) {
		this.proteinInGrams = proteinInGrams;
	}
	public int getFatInGrams() {
		return fatInGrams;
	}
	public void setFatInGrams(int fatInGrams) {
		this.fatInGrams = fatInGrams;
	}
	public int getCarbsInGrams() {
		return carbsInGrams;
	}
	public void setCarbsInGrams(int carbsInGrams) {
		this.carbsInGrams = carbsInGrams;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	public void addItem(Item item)
	{
		this.itemList.add(item);
	}
}
