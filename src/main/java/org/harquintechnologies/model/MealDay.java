package org.harquintechnologies.model;

public class MealDay {
	
	private Meal breakfast = new Meal();
	private Meal lunch = new Meal();
	private Meal dinner = new Meal();
	private Meal morningSnack = new Meal();
	private Meal afternoonSnack = new Meal();
	
	public int getTotalCaloriesForDay()
	{
		return breakfast.getCalories() + lunch.getCalories() + dinner.getCalories() + morningSnack.getCalories() + afternoonSnack.getCalories();
	}
	
	public DailyMacro getMacrosForDay() {
		DailyMacro dailyMacro = new DailyMacro();
		
		int carbsForDay = breakfast.getCarbsInGrams() + lunch.getCarbsInGrams() + dinner.getCarbsInGrams() + morningSnack.getCarbsInGrams() + afternoonSnack.getCarbsInGrams();
		int fatForDay = breakfast.getFatInGrams() + lunch.getFatInGrams() + dinner.getFatInGrams() + morningSnack.getFatInGrams() + afternoonSnack.getFatInGrams();
		int proteinForDay = breakfast.getProteinInGrams() + lunch.getProteinInGrams() + dinner.getProteinInGrams() + morningSnack.getProteinInGrams() + afternoonSnack.getProteinInGrams();
		
		dailyMacro.setCarbsInGrams(carbsForDay);
		dailyMacro.setFatsInGrams(fatForDay);
		dailyMacro.setProteinInGrams(proteinForDay);
		
		return dailyMacro;
	}
	
	public String printMealDay() {
		
		return "";
	}
	
	public Meal getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Meal breakfast) {
		this.breakfast = breakfast;
	}
	public Meal getLunch() {
		return lunch;
	}
	public void setLunch(Meal lunch) {
		this.lunch = lunch;
	}
	public Meal getDinner() {
		return dinner;
	}
	public void setDinner(Meal dinner) {
		this.dinner = dinner;
	}
	public Meal getMorningSnack() {
		return morningSnack;
	}
	public void setMorningSnack(Meal morningSnack) {
		this.morningSnack = morningSnack;
	}
	public Meal getAfternoonSnack() {
		return afternoonSnack;
	}
	public void setAfternoonSnack(Meal afternoonSnack) {
		this.afternoonSnack = afternoonSnack;
	}	
}
