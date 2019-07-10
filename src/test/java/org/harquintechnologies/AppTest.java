package org.harquintechnologies;

import java.util.ArrayList;
import java.util.List;

import org.harquintechnologies.dto.MealListDTO;
import org.harquintechnologies.model.Grocery;
import org.harquintechnologies.model.Item;
import org.harquintechnologies.model.Meal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws JsonProcessingException 
     */    
    public void testApp() throws JsonProcessingException
    {
    	Grocery chocolateChips = new Grocery();
    	chocolateChips.setName("chocolate chips");
    	chocolateChips.setQty(1);
    	chocolateChips.setQtyType("cup");
    	
    	Grocery beans = new Grocery();
    	beans.setName("beans");
    	beans.setQty(1);
    	beans.setQtyType("bag");
    		
    	Grocery chicken = new Grocery();
    	chicken.setName("chicken");
    	chicken.setQty(4);
    	chicken.setQtyType("oz");
    	
    	Grocery spincah = new Grocery();
    	spincah.setName("spinach");
    	spincah.setQty(2);
    	spincah.setQtyType("cups");
    	
    	Item proteinBar = new Item();
    	proteinBar.setName("Protein Bar");
    	proteinBar.setQty("1");
    	proteinBar.addGrocery(chocolateChips);
    	proteinBar.addGrocery(beans);
    	
    	Item chickenAndSpinach = new Item();
    	chickenAndSpinach.setName("Chicken And Spinach");
    	chickenAndSpinach.setQty("1");
    	chickenAndSpinach.addGrocery(spincah);
    	chickenAndSpinach.addGrocery(chicken);
    	
    	//Build Meal
    	Meal breakfast = new Meal();
    	breakfast.setType("breakfast");
    	breakfast.setCalories(586);
    	breakfast.setCarbsInGrams(53);
    	breakfast.setProteinInGrams(59);
    	breakfast.setFatInGrams(19);
    	
    	breakfast.addItem(proteinBar);
    	breakfast.addItem(chickenAndSpinach);
    	
    	Meal meal2 = new Meal();
    	meal2.setType("Dinner");
    	meal2.setCalories(548);
    	meal2.setCarbsInGrams(39);
    	meal2.setProteinInGrams(68);
    	meal2.setFatInGrams(18);
    	
    	meal2.addItem(chickenAndSpinach);
    	meal2.addItem(proteinBar);
    	
    	MealListDTO mealList = new MealListDTO();
    	
    	List<Meal> theMealList = new ArrayList<>();
    	theMealList.add(breakfast);
    	theMealList.add(meal2);
    	theMealList.add(breakfast);
    	
    	mealList.setMealList(theMealList);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String mealDtoString = mapper.writeValueAsString(mealList);
    	
    	System.out.println(mealDtoString);
        assertTrue( true );
    }
}
