package com.seltaf.pageobjects;

import org.openqa.selenium.By;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.HtmlElement;
import com.seltaf.webelements.LabelElement;

public class SplitSpendsListUsersScreen extends SeltafPageObject {
	
private static LabelElement identifier_element = new LabelElement("List Users Header",By.xpath("//ion-header-bar//div[contains(text(),'Users')]"));
	
	private static ButtonElement AddNewUserButton = new ButtonElement("Add New User Button", By.xpath("//button[@ng-click='showaddUser()']"));
	
	private static ButtonElement MenuButton = new ButtonElement("Menu Button", By.xpath("//div[@nav-bar='active']//ion-header-bar//button[contains(@class,'ion-navicon')]"));
	

	public SplitSpendsListUsersScreen() throws Exception {
		super(identifier_element);
	}
	
	public SplitSpendsMenuScreen clickOnMenuButton() throws Exception
	 {
		 MenuButton.simulateClick();
		 return new SplitSpendsMenuScreen();		 
	 }
	public SplitSpendsAddUsersScreen clickOnAddNewUserButton() throws Exception
	 {
		AddNewUserButton.click();
		 return new SplitSpendsAddUsersScreen();		 
	 }

}
