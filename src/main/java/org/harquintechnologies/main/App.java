package org.harquintechnologies.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.harquintechnologies.dto.MealListDTO;
import org.harquintechnologies.model.DailyMacro;
import org.harquintechnologies.model.Grocery;
import org.harquintechnologies.model.Item;
import org.harquintechnologies.model.Meal;
import org.harquintechnologies.model.MealDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	if(args.length != 1)
    	{
    		log.error("ERROR! No Meal Input File Specified In Start Command.  Please add file name to java -jar command.");
    		System.exit(1);
    	}
    	
    	MealListDTO mealListDto = new MealListDTO();
    	ObjectMapper mapper = new ObjectMapper();
    	//Read in json file
    	try
    	{
    		InputStream inputStream = new FileInputStream(args[0]);
    		String mealListString = IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
    		mealListDto = mapper.readValue(mealListString, MealListDTO.class);
    	}
    	catch (IOException ex)
    	{
    		log.error("Error Parsing Meal Input File: " + args[0] + " ", ex);
    		System.exit(1);
    	}
    	//Should read in List of meals
        //Sort List of meals by type into Breakfast, Lunch, Dinner, Snack List
    	List<Meal> breakfastMeals = new ArrayList<>();
    	List<Meal> lunchMeals = new ArrayList<>();
    	List<Meal> dinnerMeals = new ArrayList<>();
    	List<Meal> snackMeals = new ArrayList<>();
    	
    	List<Meal> completeMealList = mealListDto.getMealList();
    	for (Meal meal : completeMealList)
    	{
    		if(meal.getType().toUpperCase().startsWith("B"))
			{
    			breakfastMeals.add(meal);
			}
    		else if(meal.getType().toUpperCase().startsWith("L"))
    		{
    			lunchMeals.add(meal);
    		}
    		else if(meal.getType().toUpperCase().startsWith("D"))
    		{
    			dinnerMeals.add(meal);
    		}
    		else if(meal.getType().toUpperCase().startsWith("S"))
    		{
    			snackMeals.add(meal);
    		}
    	}
        //For 7 times, Pick out Meal For Each Day or build a Meal Day
    	List<MealDay> weeklyMealPlan = getMealPlanForWeek(breakfastMeals, lunchMeals, dinnerMeals, snackMeals);
    	
        //Make Email
    	//Make MEal plan list for Each day
    	//Iterate through MealDay Lists and print each MealDay
    	//Build Grocery List
    		//Iterate through each MealDay List and build Grocery Map
    	//Print Grocery List
    		//Iterate through Grocery Map
    	String email = buildMealPlanEmail(weeklyMealPlan);
    	
    	System.out.println(email);
    	//Send Email
    	//read from property list
    	//iterate through list and send to each one
    }
    
    private static String buildMealPlanEmail(List<MealDay> weeklyMealPlan)
    {
    	StringBuilder email = new StringBuilder();
    	//build MealPlan
    	int dayCounter = 1;
    	for(MealDay mealDay : weeklyMealPlan)
    	{
    		email.append("DAY " + dayCounter + System.lineSeparator());
    		email.append("Calories " + mealDay.getTotalCaloriesForDay() + "    ");
    		DailyMacro dailyMacros = mealDay.getMacrosForDay();
    		email.append("Macros: " + "Carbs " + dailyMacros.getCarbsInGrams() + "g Protein " + dailyMacros.getProteinInGrams() + "g Fats " + dailyMacros.getFatsInGrams() + "g" + System.lineSeparator());
    		email.append(System.lineSeparator());
    		email.append("BREAKFAST " + "DAY " + dayCounter + System.lineSeparator());
    		email.append(printAMeal(mealDay.getBreakfast()));
    		email.append(System.lineSeparator());
    		
    		email.append("LUNCH " + "DAY " + dayCounter + System.lineSeparator());
    		email.append(printAMeal(mealDay.getLunch()));
    		email.append(System.lineSeparator());
    		
    		email.append("DINNER " + "DAY " + dayCounter + System.lineSeparator());
    		email.append(printAMeal(mealDay.getDinner()));
    		email.append(System.lineSeparator());
    		
    		email.append("MORNING SNACK " + "DAY " + dayCounter + System.lineSeparator());
    		email.append(printAMeal(mealDay.getMorningSnack()));
    		email.append(System.lineSeparator());
    		
    		email.append("AFTERNOON SNACK " + "DAY " + dayCounter + System.lineSeparator());
    		email.append(printAMeal(mealDay.getAfternoonSnack()) + System.lineSeparator());
    		email.append(System.lineSeparator());
    		dayCounter++;
    	}
    	
    	email.append(System.lineSeparator());
    	email.append(System.lineSeparator());
    	email.append(System.lineSeparator());
    	email.append("GROCERY LIST: " + System.lineSeparator());
    	//build groceryList
    	Map<String, Grocery> groceryMap = new HashMap<>();
    	
    	for(MealDay mealDay : weeklyMealPlan)
    	{
    		Meal breakfast = mealDay.getBreakfast();
    		List<Item> dayItemList = new ArrayList<>();
    		dayItemList.addAll(breakfast.getItemList());
    		
    		dayItemList.addAll(mealDay.getLunch().getItemList());
    		dayItemList.addAll(mealDay.getDinner().getItemList());
    		dayItemList.addAll(mealDay.getAfternoonSnack().getItemList());
    		dayItemList.addAll(mealDay.getMorningSnack().getItemList());
    		
    		for(Item item : dayItemList)
    		{
    			List<Grocery> groceryList = item.getGroceryList();
    			
    			for(Grocery grocery : groceryList)
    			{
    				if(!groceryMap.containsKey(grocery.getName()))
    				{
    					groceryMap.put(grocery.getName(), grocery);
    				}
    				else
    				{
    					Grocery retrievedGrocery = groceryMap.get(grocery.getName());
    					retrievedGrocery.setQty(retrievedGrocery.getQty() + grocery.getQty());
    				}
    			}
    		}
    	}
    	
    	for(String groceryName : groceryMap.keySet())
    	{
    		Grocery grocery = groceryMap.get(groceryName);
    		email.append(grocery.getQty() + " " + grocery.getQtyType() + " " + groceryName + System.lineSeparator());
    	}
    	
    	return email.toString();
    }
    
    private static String printAMeal(Meal meal)
    {
    	StringBuilder printedMeal = new StringBuilder();
    	
    	printedMeal.append("Calories: " + meal.getCalories() + "    ");
    	printedMeal.append("Macros: " + "Carbs " + meal.getCarbsInGrams() + "g Protein " + meal.getProteinInGrams() + "g Fats " + meal.getFatInGrams() + "g" + System.lineSeparator());
    	printedMeal.append("Items: " + System.lineSeparator());
    	
    	List<Item> itemList = meal.getItemList();
    	
    	for(Item item : itemList)
    	{
    		printedMeal.append(item.getQty() + " " + item.getName() + System.lineSeparator());
    	}
    	
    	return printedMeal.toString();
    }
    
    private static List<MealDay> getMealPlanForWeek(List<Meal> breakfastList, List<Meal> lunchList, List<Meal> dinnerList, List<Meal> snackList)
    {
    	List<MealDay> weeklyMealPlan = new ArrayList<>();
    	
    	for(int weekIndex = 0; weekIndex < 7; weekIndex++)
    	{
    		MealDay mealDay = new MealDay();
    		//get breakfast
    		mealDay.setBreakfast(getRandomlySelectedMeal(breakfastList));
    		//get lunch
    		mealDay.setLunch(getRandomlySelectedMeal(lunchList));
    		//get dinner
    		mealDay.setDinner(getRandomlySelectedMeal(dinnerList));
    		//get morningSnack
    		mealDay.setMorningSnack(getRandomlySelectedMeal(snackList));
    		//get afternoonSnack
    		mealDay.setAfternoonSnack(getRandomlySelectedMeal(snackList));
    		
    		weeklyMealPlan.add(mealDay);
    	}
    	
    	return weeklyMealPlan;
    }
    
    private static Meal getRandomlySelectedMeal(List<Meal> mealList)
    {
		double randomPick = Math.random();		
		
		int selectedMealIndex = (int) (randomPick * mealList.size());
		
		if(selectedMealIndex == mealList.size())
		{
			selectedMealIndex = selectedMealIndex - 1;
		}
		Meal selectedMeal = mealList.get(selectedMealIndex);
		
		return selectedMeal;
    }
}
