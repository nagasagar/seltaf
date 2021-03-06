package com.seltaf.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.helpers.WaitHelper;

public class MercureTourLoginPage extends SeltafPageObject {

	@FindBy(name = "userName")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(css = "img[src='/images/masts/mast_signon.gif']")
	WebElement titleimage;

	@FindBy(css = "input[src='/images/forms/submit.gif']")
	WebElement SubmitButton;
	
	
	public MercureTourLoginPage() throws Exception{
		PageFactory.initElements(driver, this);
	}
	
	public MercureTourLoginPage(boolean NavigateTo) throws Exception{
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
