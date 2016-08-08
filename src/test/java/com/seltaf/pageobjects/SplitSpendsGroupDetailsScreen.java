package com.seltaf.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.seltaf.core.CustomAssertion;
import com.seltaf.core.SeltafPageObject;
import com.seltaf.dataobjects.Group;
import com.seltaf.dataobjects.User;
import com.seltaf.helpers.WaitHelper;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.CheckBoxElement;
import com.seltaf.webelements.LabelElement;
import com.seltaf.webelements.LinkElement;
import com.seltaf.webelements.MultipleElementsList;

public class SplitSpendsGroupDetailsScreen extends SeltafPageObject {
	private static LabelElement identifier_element = new LabelElement("List Groups Header",By.xpath("//div[@nav-bar='active']//button[@ng-click='showManageGroupActionsheet(group)']"));
	
	private static LinkElement GrpUsers_link = new LinkElement("Users tab Link", By.xpath("//ion-view[@nav-view='active']//ion-tabs//span[contains(text(),'Users')]"));

	private static MultipleElementsList UsersList = new MultipleElementsList("User List", By.xpath("//ion-view[@nav-view='active']//ion-tabs//div[contains(@class,'pane') and @nav-view='active']//ion-item[contains(@ng-repeat,'user in users')]//div[@class='row']//div[contains(@class,'bold')]"));
	
	private static ButtonElement HideGroupDetailsButton = new ButtonElement("HIde GroupDetails Button", By.xpath("//div[@nav-bar='active']//ion-header-bar//button[contains(@ng-click,'ionicGoBack()')]"));
	
	private static ButtonElement ActionSheet_DeleteButton = new ButtonElement("Action Sheet Delete Group Button", By.xpath("//div[@class='action-sheet-backdrop active']//button[2]"));
	
	private static CheckBoxElement DeleteGroupusersCheckbox = new CheckBoxElement("Action Sheet Delete Group Button", By.xpath("//div[@class='popup-container popup-showing active']//input[@type='checkbox']"));
	
	private static ButtonElement Confirm_DeleteButton = new ButtonElement("Confirm Delete  Button", By.xpath("//div[@class='popup-container popup-showing active']//div[@class='popup-buttons']//button[contains(@class,'dark')]"));
	
	public SplitSpendsGroupDetailsScreen() throws Exception {
		super(identifier_element);
	}
	
	public SplitSpendsGroupDetailsScreen nav2UsersTab(){
		GrpUsers_link.click();
		return this;
	}
	
	public SplitSpendsGroupDetailsScreen ClickEditGroupButton(){
		identifier_element.click();
		return this;
	}
	
	public SplitSpendsGroupDetailsScreen VerifyUsers(Group grp){
		boolean alluserspresent = true;
		List<User> users = grp.getUsers();
		for(User user :users){
			boolean userfound = false;
			for(WebElement e : UsersList.getElements())
			{
				if(e.getText().equalsIgnoreCase(user.getName())){
					userfound = true;
					break;
				}
			}
			if(!userfound)
			{
				alluserspresent = false;
				CustomAssertion.fail("User "+user.getName()+" does not exist in Group "+grp.getName());
			}
			
		}
		if(!alluserspresent)
			CustomAssertion.fail("Verifivation failed for Group  : "+grp.getName());
		this.captureAPPSnapshot();
		return this;
	}
	
	public SplitSpendsListGroupsScreen HideGrpDetailsScreen() throws Exception{
		HideGroupDetailsButton.click();
		return new SplitSpendsListGroupsScreen();
	}

	public SplitSpendsListGroupsScreen DeleteGroup() throws Exception {
		waitForElementPresent(ActionSheet_DeleteButton);
		ActionSheet_DeleteButton.click();
		waitForElementPresent(DeleteGroupusersCheckbox);
		if(!DeleteGroupusersCheckbox.isSelected())
		{
			DeleteGroupusersCheckbox.simulateClick();	
		}		
		Confirm_DeleteButton.click();
		WaitHelper.waitForSeconds(4);
		return new SplitSpendsListGroupsScreen();
		
		
	}
	

}
