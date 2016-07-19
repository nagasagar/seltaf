package com.seltaf.pageobjects;
import org.openqa.selenium.By;

import com.seltaf.core.SeltafContextManager;
import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.ImageElement;
import com.seltaf.webelements.LinkElement;



public class HomePage extends SeltafPageObject{
	
	 	 private static ImageElement Identifier_FeaturedDestination = new ImageElement ("Identifier element", By.cssSelector("img[alt='Featured Destination: Aruba']"));
	 	 private static LinkElement Sign_on_link = new LinkElement("Sign-ON link", By.linkText("SIGN-ON"));
	 	 
	 public HomePage(final boolean openPageURL) throws Exception {
			super(Identifier_FeaturedDestination, openPageURL ? SeltafContextManager.getThreadContext().getAppURL() : null);
	 }
	 
	 public MercureToursLoginPage navigateToLoginPage() throws Exception {
		 Sign_on_link.click();
	     return new MercureToursLoginPage(false);
	    }
	 
	 
}
