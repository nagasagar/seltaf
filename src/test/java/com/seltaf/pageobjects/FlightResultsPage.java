package com.seltaf.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.ImageElement;
import com.seltaf.webelements.RadioButtonElement;

public class FlightResultsPage extends SeltafPageObject {

	private static ImageElement identifier_element = new ImageElement("Sign - on Image",By.cssSelector("img[src='/images/masts/mast_selectflight.gif']"));
	private static List<RadioButtonElement> onwardoption_radiobuttons = new ArrayList<RadioButtonElement>();
	private static List<RadioButtonElement> returnoption_radiobuttons = new ArrayList<RadioButtonElement>();
	private static ButtonElement ContinueButton = new ButtonElement("Continue Button", By.name("reserveFlights"));
	public FlightResultsPage() throws Exception {
		super(identifier_element);
		getonwardoptions();
		getreturnoptions();
	}
	
	public void getonwardoptions()
	{
		List<WebElement> elements = driver.findElements(By.cssSelector("input[name='inFlight']"));	
		for(WebElement element : elements)
		{
			String optionvalue = element.getAttribute("value");
			RadioButtonElement temp = new RadioButtonElement("return-option-"+optionvalue,By.cssSelector("input[value='"+optionvalue+"']"));
			onwardoption_radiobuttons.add(temp);
		}
	}
	
	public void getreturnoptions()
	{
		List<WebElement> elements = driver.findElements(By.cssSelector("input[name='outFlight']"));	
		for(WebElement element : elements)
		{
			String optionvalue = element.getAttribute("value");
			RadioButtonElement temp = new RadioButtonElement("onward-option-"+optionvalue,By.cssSelector("value='"+optionvalue+"'"));
			returnoption_radiobuttons.add(temp);
		}
	}
	
	public FlightResultsPage select_cheap_outgoing_flight()
	{ 
		flightoptionpriceComparator comparator = new flightoptionpriceComparator();
		Collections.sort(onwardoption_radiobuttons,comparator);
		onwardoption_radiobuttons.get(onwardoption_radiobuttons.size()-1).check();
		return this;
		
	}
	public FlightResultsPage select_cheap_return_flight()
	{ 
		flightoptionpriceComparator comparator = new flightoptionpriceComparator();
		Collections.sort(onwardoption_radiobuttons,comparator);
		onwardoption_radiobuttons.get(onwardoption_radiobuttons.size()-1).check();
		return this;
		
	}
	public FlightBookingPage continue_with_selected_flight_options() throws Exception
	{ 
		ContinueButton.click();
		return new FlightBookingPage();
		
	}
	
}

class flightoptionpriceComparator implements Comparator<Object>{
	 
	public int compare(Object arg0, Object arg1) {
		RadioButtonElement element1 = (RadioButtonElement) arg0;
		RadioButtonElement element2 = (RadioButtonElement) arg1;
		int val1 = Integer.parseInt(element1.getLabel().split("\\$")[2]);
		int val2 = Integer.parseInt(element2.getLabel().split("\\$")[2]);
		if(val1>val2)  
			return 1;  
		else  
			return -1; 
	}
}

class flightoptiontimeComparator implements Comparator<Object>{
	 
	public int compare(Object arg0, Object arg1) {
		RadioButtonElement element1 = (RadioButtonElement) arg0;
		RadioButtonElement element2 = (RadioButtonElement) arg1;
		LocalTime val1 = new LocalTime(Integer.parseInt(element1.getLabel().split("$")[3].split(":")[0]),Integer.parseInt(element1.getLabel().split("$")[3].split(":")[1]));
		LocalTime val2 = new LocalTime(Integer.parseInt(element2.getLabel().split("$")[3].split(":")[0]),Integer.parseInt(element2.getLabel().split("$")[3].split(":")[1]));
		if(val1.isAfter(val2))  
			return 1;  
		else  
			return -1; 
	}
}
