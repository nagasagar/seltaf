package com.seltaf.pageobjects;

import org.openqa.selenium.By;

import com.seltaf.core.SeltafContextManager;
import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.ImageElement;
import com.seltaf.webelements.TextFieldElement;



public class MercureToursLoginPage extends SeltafPageObject {
	
	 private static ImageElement identifier_element = new ImageElement("Sign - on Image",By.cssSelector("img[src='/images/masts/mast_signon.gif']"));
	
	 private static TextFieldElement UserNameTextBox = new TextFieldElement("UserName Text Box", By.name("userName"));
	 private static TextFieldElement PasswordTextBox = new TextFieldElement("Password Text Box", By.name("password"));
	 private static ButtonElement SubmitButton = new ButtonElement("Submit Button", By.cssSelector("input[src='/images/forms/submit.gif']"));

	 public MercureToursLoginPage(final boolean openPageURL) throws Exception {
			super(identifier_element, openPageURL ? SeltafContextManager.getThreadContext().getAppURL() : null);
	 }
	 
	 public FlightFinderPage login2Website() throws Exception {
		 UserNameTextBox.clear();
		 UserNameTextBox.type("Apache");
		 PasswordTextBox.clear();
		 PasswordTextBox.type("jmeter");
		 SubmitButton.submit();
		 return new FlightFinderPage();
	    } 
	 
	 
}
