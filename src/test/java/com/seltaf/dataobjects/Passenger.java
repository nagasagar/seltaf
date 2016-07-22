package com.seltaf.dataobjects;

public class Passenger {

	private String FirstName;
	private String LastName;
	private String MealPreference;
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getMealPreference() {
		return MealPreference;
	}
	public void setMealPreference(String mealPreference) {
		MealPreference = mealPreference;
	}
	


@Override
public String toString() {
	
        return ("Passenger: ["+
 " First Name: " + FirstName +","+
 "Last Name:"+LastName+","+
 "Meal Preference :"+MealPreference+","+
 " ]");
        
 }

}
