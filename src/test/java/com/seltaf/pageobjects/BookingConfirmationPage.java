package com.seltaf.pageobjects;

import org.openqa.selenium.By;


import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.ImageElement;
import com.seltaf.webelements.LabelElement;

public class BookingConfirmationPage extends SeltafPageObject {

	private static ImageElement identifier_element = new ImageElement("Flight Confirmation Image",By.cssSelector("img[src='/images/masts/mast_confirmation.gif']"));
	private static LabelElement confirmationelement = new LabelElement("Confirmation number",By.xpath("//*[contains(text(),'Confirmation #')]"));
	
	private static ButtonElement LogoutButton = new ButtonElement("Continue Button", By.cssSelector("img[src='/images/forms/Logout.gif']"));
	
	public BookingConfirmationPage() throws Exception {
		super(identifier_element);
	}
	
	public String getConfirmationNumber(){
		return confirmationelement.getText();
	}
	
	public MercureToursLoginPage logout() throws Exception{
		LogoutButton.click();
		return new MercureToursLoginPage(false);
	}
}
