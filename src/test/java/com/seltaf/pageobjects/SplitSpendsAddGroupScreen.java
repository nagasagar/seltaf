package com.seltaf.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.dataobjects.Group;
import com.seltaf.dataobjects.User;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.LabelElement;
import com.seltaf.webelements.SelectList;
import com.seltaf.webelements.TextFieldElement;

public class SplitSpendsAddGroupScreen extends SeltafPageObject  {
	private static LabelElement identifier_element = new LabelElement("Add Group Header",By.xpath("//ion-modal-view[@view-title='Add Group' and contains(@class,'active')]"));
	private static ButtonElement AddButton = new ButtonElement("Add New Group Button", By.xpath("//ion-modal-view[@view-title='Add Group' and contains(@class,'active')]//form[@name='addgroupform']//button[@type='submit']"));
	
	private static ButtonElement HideButton = new ButtonElement("HIde Modal Button", By.xpath("//div[contains(@class,'modal-backdrop active')]//button[contains(@ng-click,'hideaddgroupmodal()')]"));
	
	private static TextFieldElement group_name = new TextFieldElement("Group Name TextFeild",  By.xpath("//div[contains(@class,'active')]//form[@name='addgroupform']//input[@name='groupname']"));
	private static ButtonElement SelectUsersLabel = new ButtonElement("selectusers label", By.xpath("//div[contains(@class,'active')]//form[@name='addgroupform']//div[contains(@ng-click,'showselectusers()')]"));
	private static SelectList MultiSelectUsersCheckboxList =  new SelectList("Select_users_List",By.xpath("//div[contains(@class,'modal-backdrop active')]//label[@ng-repeat='user in users']"));
	private static ButtonElement SelectUsersAcceptButton = new ButtonElement("Accept selected users Button", By.xpath("//div[contains(@class,'modal-backdrop active')]//button[contains(@ng-click,'validateselectusers()')]"));
	private static ButtonElement SelectUsersHideButton = new ButtonElement("HIde Modal Button", By.xpath("//div[contains(@class,'modal-backdrop active')]//button[contains(@ng-click,'hideselectusers()')]"));
	public SplitSpendsAddGroupScreen() throws Exception {
		super(identifier_element);
	}

	public SplitSpendsListGroupsScreen HideModal() throws Exception
	 {
		HideButton.click();
		return new SplitSpendsListGroupsScreen();
	 }
	
	public List<User> Addgroup(Group grp) throws Exception
	 {
		group_name.sendKeys(grp.getName());
		List<User> yet2baddedusers = selectusers(grp.getUsers());
		if(yet2baddedusers.size()>0)
		{
			AddButton.submit();
		}
		else{
			AddButton.click();
		}
		return yet2baddedusers;
		 
	 }
	

	public void clickAddButton(){
		AddButton.click();
	}
	public List<User> selectusers(List<User> Users){
		SelectUsersLabel.click();
		waitForElementPresent(MultiSelectUsersCheckboxList);
		List<User> yet2baddedusers = new ArrayList<User>();
		int selectedusers=0;
		for(User user:Users)
		{
			boolean tempflag = false;
			for(WebElement element : MultiSelectUsersCheckboxList.getAllElements())
			{
				String eletext = element.getText();
				if(eletext.equalsIgnoreCase(user.getName()))
				{
					tempflag=true;
					element.click();
					selectedusers++;
				}
			}
			if(!tempflag)
			{
				yet2baddedusers.add(user);
			}
		}
		if(selectedusers==0)
		{
			SelectUsersHideButton.click();
		}
		else{
			SelectUsersAcceptButton.click();
		}
		
		return yet2baddedusers;
		
	}
}
