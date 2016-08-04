package com.seltaf.pageobjects;

import org.openqa.selenium.By;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.webelements.LabelElement;
import com.seltaf.webelements.LinkElement;

public class SplitSpendsMenuScreen extends SeltafPageObject{
	private static LabelElement identifier_element = new LabelElement("Menu Header",By.xpath("//ion-side-menu//div[contains(text(),'Menu')]"));
	
	private static LinkElement Nav_Grps_Link = new LinkElement("Groups_Menu_Link",By.xpath("//ion-side-menu//ion-item[@href='#/app/listgroups']"));
	private static LinkElement Nav_Usrs_Link = new LinkElement("Userss_Menu_Link",By.xpath("//ion-side-menu//ion-item[@href='#/app/listusers']"));
	private static LinkElement Nav_Exps_Link = new LinkElement("Expenses_Menu_Link",By.xpath("//ion-side-menu//ion-item[@href='#/app/listexpenses']"));
	private static LinkElement Nav_settings_Link = new LinkElement("Settings_Menu_Link",By.xpath("//ion-side-menu//ion-item[@href='#/app/settings']"));
	private static LinkElement Nav_help_Link = new LinkElement("Help_Menu_Link",By.xpath("//ion-side-menu//ion-item[@href='#/app/help']"));
	public SplitSpendsMenuScreen() throws Exception {
		super(identifier_element);
	}
	
	public SplitSpendsListGroupsScreen NavigatetoListGroups() throws Exception
	 {
		Nav_Grps_Link.click();
		 return new SplitSpendsListGroupsScreen();		 
	 }
	public SplitSpendsListUsersScreen NavigatetoListUsers() throws Exception
	 {
		Nav_Usrs_Link.click();
		 return new SplitSpendsListUsersScreen();		 
	 }
	public SplitSpendsListExpensesScreen NavigatetoListExpenses() throws Exception
	 {
		Nav_Exps_Link.click();
		 return new SplitSpendsListExpensesScreen();		 
	 }
	public SplitSpendsSettingsScreen NavigatetoListSettings() throws Exception
	 {
		Nav_settings_Link.click();
		 return new SplitSpendsSettingsScreen();		 
	 }
	public SplitSpendsHelpScreen NavigatetoHelp() throws Exception
	 {
		Nav_help_Link.click();
		 return new SplitSpendsHelpScreen();		 
	 }

	

}
