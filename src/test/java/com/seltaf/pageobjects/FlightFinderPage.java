package com.seltaf.pageobjects;

import java.util.Calendar;

import org.openqa.selenium.By;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.dataobjects.FlightSearchinput;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.ImageElement;
import com.seltaf.webelements.RadioButtonElement;
import com.seltaf.webelements.SelectList;


public class FlightFinderPage extends SeltafPageObject {
	
	private static ImageElement identifier_element = new ImageElement("Flight Finder Image",By.cssSelector("img[src='/images/masts/mast_flightfinder.gif']"));
	
	 private static RadioButtonElement trip_type_return = new RadioButtonElement("UserName Text Box", By.cssSelector("input[value='roundtrip']"));
	 private static RadioButtonElement trip_type_oneway = new RadioButtonElement("UserName Text Box", By.cssSelector("input[value='oneway']"));
	 private static SelectList passengersDropDown = new SelectList("Documentation drop down", By.name("passCount"));
	 private static SelectList departureDropDown = new SelectList("Documentation drop down", By.name("fromPort"));
	 private static SelectList departureMonth = new SelectList("Documentation drop down", By.name("fromMonth"));
	 private static SelectList departureDay = new SelectList("Documentation drop down", By.name("fromDay"));
	 private static SelectList arrivalDropDown = new SelectList("Documentation drop down", By.name("toPort"));
	 private static SelectList returnMonth = new SelectList("Documentation drop down", By.name("toMonth"));
	 private static SelectList returnDay = new SelectList("Documentation drop down", By.name("toDay"));
	 private static RadioButtonElement Economy_class = new RadioButtonElement("UserName Text Box", By.cssSelector("input[value='Coach']"));
	 private static RadioButtonElement Business_class = new RadioButtonElement("UserName Text Box", By.cssSelector("input[value='Business']"));
	 private static RadioButtonElement First_class = new RadioButtonElement("UserName Text Box", By.cssSelector("input[value='First']"));
	 private static SelectList AirlineDropdown = new SelectList("Documentation drop down", By.name("airline"));
	 private static ButtonElement ContinueButton = new ButtonElement("Continue Button", By.name("findFlights"));

	 public FlightFinderPage() throws Exception {
			super(identifier_element);
	 }
	 

	public FlightResultsPage Searchflight(final FlightSearchinput input) throws Exception {
		 if (input.getTriptype().equalsIgnoreCase("oneway"))
			 trip_type_oneway.check();
		 else
			trip_type_return.check();
	     passengersDropDown.selectByValue(input.getNo_passengers()+"");
	     departureDropDown.selectByValue(input.getSource());
	     departureMonth.selectByValue(input.getDeparturedate().get(Calendar.MONTH)+1+"");
	     departureDay.selectByValue(input.getDeparturedate().get(Calendar.DATE)+"");
	     arrivalDropDown.selectByValue(input.getDest());
	     returnMonth.selectByValue(input.getReturndate().get(Calendar.MONTH)+1+"");
	     returnDay.selectByValue(input.getReturndate().get(Calendar.DATE)+"");
	     if (input.getTriptype().equalsIgnoreCase("economy"))
			 Economy_class.check();
		 else if (input.getTriptype().equalsIgnoreCase("business"))
			Business_class.check();
		 else if (input.getTriptype().equalsIgnoreCase("first"))
			 First_class.check();
	     AirlineDropdown.selectByValue(input.getAirlinePreference());
		 ContinueButton.submit();
	     return new FlightResultsPage();
	    }

}
