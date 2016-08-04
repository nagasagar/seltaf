package com.seltaf.pageobjects;

import org.openqa.selenium.By;

import com.seltaf.core.SeltafContextManager;
import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.LabelElement;

public class SplitSpendsListGroupsScreen extends SeltafPageObject {
	private static LabelElement identifier_element = new LabelElement("List Groups Header",By.xpath("//ion-header-bar//div[contains(text(),'Groups')]"));
	
	private static ButtonElement AddNewGroupButton = new ButtonElement("Add New Group Button", By.xpath("//button[@ng-click='showaddgroupmodal()']"));
	
	private static ButtonElement MenuButton = new ButtonElement("Menu Button", By.xpath("//div[@nav-bar='active']//ion-header-bar//button[contains(@class,'ion-navicon')]"));
	
	
	 public SplitSpendsListGroupsScreen(final boolean openAPP) throws Exception {
	        super(null, openAPP ? SeltafContextManager.getThreadContext().getApp() : null);
	        
	    }
	 public SplitSpendsListGroupsScreen(final boolean openAPP,final boolean switch2hybrid ) throws Exception {
		 //second parameter true is to specify that app is hybrid app
	        super(identifier_element, true);
	        
	    }
	 
	 
	 public SplitSpendsListGroupsScreen() throws Exception {
		super(identifier_element);
	}

	public SplitSpendsMenuScreen clickOnMenuButton() throws Exception
	 {
		 MenuButton.simulateClick();
		 return new SplitSpendsMenuScreen();		 
	 }
	
	 public SplitSpendsAddGroupScreen clickOnAddNewGroupButton() throws Exception
	 {
		 AddNewGroupButton.click();
		 return new SplitSpendsAddGroupScreen();		 
	 }
	 
	 
	 public SplitSpendsListGroupsScreen searchforagroup()
	 {
		
		return this;
		 
	 }
	 public SplitSpendsListGroupsScreen VerifyScreen()
	 {
		 assertElementPresent(identifier_element);
		return this;
		 
	 }

}
 	