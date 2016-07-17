package com.seltaf.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.helpers.WaitHelper;

public class MercureToursLoginPage extends SeltafPageObject {

	@FindBy(name = "userName")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(css = "img[src='/images/masts/mast_signon.gif']")
	WebElement titleimage;

	@FindBy(css = "input[src='/images/forms/submit.gif']")
	WebElement SubmitButton;
	
	
	public MercureToursLoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public MercureToursLoginPage(boolean NavigateTo){
		super();
		PageFactory.initElements(driver, this);
		if(NavigateTo)
		{
			
			driver.get("http://newtours.demoaut.com/");
		}
	}
	
	public String getPageTitle()
	{
		
		
		return driver.getTitle();
		
	}

}
