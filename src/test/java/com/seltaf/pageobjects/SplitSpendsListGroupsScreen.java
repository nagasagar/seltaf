package com.seltaf.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.seltaf.core.CustomAssertion;
import com.seltaf.core.SeltafContextManager;
import com.seltaf.core.SeltafPageObject;
import com.seltaf.dataobjects.Group;
import com.seltaf.helpers.WaitHelper;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.LabelElement;
import com.seltaf.webelements.MultipleElementsList;
import com.seltaf.webelements.TextFieldElement;

public class SplitSpendsListGroupsScreen extends SeltafPageObject {
	private static LabelElement identifier_element = new LabelElement("List Groups Header",By.xpath("//ion-header-bar//div[contains(text(),'Groups')]"));
	
	private static ButtonElement AddNewGroupButton = new ButtonElement("Add New Group Button", By.xpath("//button[@ng-click='showaddgroupmodal()']"));
	
	private static ButtonElement MenuButton = new ButtonElement("Menu Button", By.xpath("//div[@nav-bar='active']//ion-header-bar//button[contains(@class,'ion-navicon')]"));
	
	private static TextFieldElement SearchGroupeditField = new TextFieldElement("Search Group TextField", By.xpath("//ion-view[@ng-controller='groupcontrol']//input[@ng-model='searchText']"));
	
	private static MultipleElementsList GroupsList = new MultipleElementsList("Group List", By.xpath("//ion-view[@ng-controller='groupcontrol']//ion-content//ion-item[contains(@ng-repeat,'group in groups')]"));
	 public SplitSpendsListGroupsScreen(final boolean openAPP) throws Exception {
	        super(null, openAPP ? SeltafContextManager.getThreadContext().getApp() : null);
	        
	    }
	 public SplitSpendsListGroupsScreen(final boolean openAPP,final boolean switch2hybrid ) throws Exception {
		 //second parameter true is to specify the driver to switch to Hybrid  context
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
	
	public SplitSpendsGroupDetailsScreen clickOnGroup(String grpname) throws Exception
	 {
		 GroupsList.getElementByText(grpname).click();
		 return new SplitSpendsGroupDetailsScreen();		 
	 }
	
	 public SplitSpendsAddGroupScreen clickOnAddNewGroupButton() throws Exception
	 {
		 AddNewGroupButton.click();
		 return new SplitSpendsAddGroupScreen();		 
	 }
	 
	 
	 public SplitSpendsListGroupsScreen searchforagroup(String grpname)
	 {
		 WaitHelper.waitForSeconds(3);
		 SearchGroupeditField.click();
		 waitForElementEditable(SearchGroupeditField);
		 SearchGroupeditField.clear();
		 SearchGroupeditField.sendKeys(grpname);
		return this;
		 
	 }
	 public SplitSpendsListGroupsScreen clearsearchfield()
	 {
		 WaitHelper.waitForSeconds(3);
		 SearchGroupeditField.click();
		 waitForElementEditable(SearchGroupeditField);
		 SearchGroupeditField.clear();
		return this;
		 
	 }
	 public SplitSpendsListGroupsScreen VerifyScreen()
	 {
		 assertElementPresent(identifier_element);
		return this;
		 
	 }
	public SplitSpendsListGroupsScreen verifygroupexistsBySearch(String grpname) {
		searchforagroup(grpname);
		boolean filterworking = true ;
		for (WebElement ele : GroupsList.getElements())
		{
			if(!ele.getText().equalsIgnoreCase(grpname))
			{
				filterworking = false;
			}
		}
		CustomAssertion.assertTrue(filterworking, "Verify Group Exists");
		clearsearchfield();
		return this;
		
	}
	public SplitSpendsListGroupsScreen verifygroupnotexistsBySearch(String grpname) {
		searchforagroup(grpname);
		boolean filterworking = true ;
		for (WebElement ele : GroupsList.getElements())
		{
			if(ele.getText().equalsIgnoreCase(grpname))
			{
				filterworking = false;
			}
		}
		CustomAssertion.assertTrue(filterworking, "Verify Group not Exists");
		clearsearchfield();
		return this;
		
	}
	public SplitSpendsListGroupsScreen verifygroupexistsBySearch(Group grp) throws Exception {
		searchforagroup(grp.getName());
		boolean filterworking = true ;
		for (WebElement ele : GroupsList.getAllElements())
		{
			if(!ele.getText().equalsIgnoreCase(grp.getName()))
			{
				filterworking = false;
			}
		}
		CustomAssertion.assertTrue(filterworking, "Verify Group Exists");
		SplitSpendsGroupDetailsScreen grpdetailsscreen = clickOnGroup(grp.getName());
		grpdetailsscreen.nav2UsersTab();
		grpdetailsscreen.VerifyUsers(grp);
		grpdetailsscreen.HideGrpDetailsScreen();
		clearsearchfield();
		return this;
		
	}

}
 	