package com.seltaf.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.dataobjects.Group;
import com.seltaf.dataobjects.User;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.LabelElement;
import com.seltaf.webelements.SelectList;
import com.seltaf.webelements.TextFieldElement;

public class SplitSpendsAddUsersScreen extends SeltafPageObject{
	
	private static LabelElement identifier_element = new LabelElement("AddUser_Header",By.xpath("//ion-modal-view[@view-title='Add User' and contains(@class,'active')]"));
	private static ButtonElement AddButton = new ButtonElement("Add New User Button", By.xpath("//ion-modal-view[@view-title='Add User' and contains(@class,'active')]//form[@name='adduserform']//button[@type='submit']"));
	
	private static ButtonElement HideButton = new ButtonElement("Hide Modal Button", By.xpath("//div[contains(@class,'modal-backdrop active')]//button[contains(@ng-click,'hideaddusermodal()')]"));
	
	private static TextFieldElement user_name = new TextFieldElement("User Name TextFeild",  By.xpath("//div[contains(@class,'active')]//form[@name='adduserform']//input[@name='username']"));
	private static ButtonElement SelectGroupLabel = new ButtonElement("select groups button", By.xpath("//div[contains(@class,'active')]//form[@name='adduserform']//div[contains(@ng-click,'showselectgroups()')]"));
	private static SelectList MultiSelectGroupssCheckboxList =  new SelectList("Select_users_List",By.xpath("//div[contains(@class,'modal-backdrop active')]//label[@ng-repeat='group in groups']"));
	
	public SplitSpendsAddUsersScreen() throws Exception {
	        super(identifier_element); 
	}
	
	public SplitSpendsListUsersScreen HideModal() throws Exception
	 {
		HideButton.click();
		return new SplitSpendsListUsersScreen();
	 }
	
	public SplitSpendsListUsersScreen AddUser(User user,Group group) throws Exception
	 {
		user_name.sendKeys(user.getName());
		selectgroup(group);
		AddButton.click();
		return click_adduserButton();
		 
	 }
	
	
	private void selectgroup(Group group) throws Exception {
		SelectGroupLabel.click();
		waitForElementPresent(MultiSelectGroupssCheckboxList);
		boolean groupexists = false;
		for(WebElement element : MultiSelectGroupssCheckboxList.getAllElements())
		{
			String eletext = element.getText();
			if(eletext.equalsIgnoreCase(group.getName()))
			{
				groupexists=true;
				element.click();
				break;
			}
		}
		if(!groupexists)
		{
			throw new Exception("input group doesnot exist");
		}
		
		
	}

	public SplitSpendsListUsersScreen click_adduserButton() throws Exception{
		AddButton.click();
		return new SplitSpendsListUsersScreen();
	}
	
	
}
