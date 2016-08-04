package com.seltaf.tests;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seltaf.core.Filter;
import com.seltaf.core.SeltafTestPlan;
import com.seltaf.dataobjects.CreditCard;
import com.seltaf.dataobjects.FlightSearchinput;
import com.seltaf.dataobjects.Group;
import com.seltaf.dataobjects.Passenger;
import com.seltaf.dataobjects.User;
import com.seltaf.helpers.SpreadSheetHelper;
import com.seltaf.helpers.WaitHelper;
import com.seltaf.helpers.XmlObjectDataHelper;
import com.seltaf.pageobjects.SplitSpendsAddGroupScreen;
import com.seltaf.pageobjects.SplitSpendsAddUsersScreen;
import com.seltaf.pageobjects.SplitSpendsListGroupsScreen;
import com.seltaf.pageobjects.SplitSpendsListUsersScreen;
import com.seltaf.pageobjects.SplitSpendsMenuScreen;
import com.seltaf.util.internal.entity.TestEntity;
import com.seltaf.utils.XMLWrapper;


/**
 * Hybrid Android app test suite.
 */
public class AndroidHybridAppTest extends SeltafTestPlan {

	
	@DataProvider(
	        name = "GroupData",
	        parallel = false
	    )
	    public static Iterator<Object[]> getData(final Method m)
	        throws Exception {
	        Filter filter = Filter.equalsIgnoreCase(TestEntity.TEST_METHOD,
	                m.getName());

	        LinkedHashMap<String, Class<?>> classMap = new LinkedHashMap<String, Class<?>>();
			 classMap.put("TestEntity", TestEntity.class);
			 classMap.put("Group", Group.class);
			 classMap.put("user", User.class);
			 Iterator<Object[]> entities = XmlObjectDataHelper.getEntitiesFromxml(classMap,m.getName(), "./src/test/resources/TestData/grpdata.xml",filter);
	        
	        return entities;
	    }
    	
    @Test(groups = {"splitspends","hybridapp"},dataProvider = "GroupData", description = "Adds new group and adds users to it")
    public void addNewGroup(TestEntity testentity,Group grp) throws Exception {
    	System.out.println(testentity);
    	System.out.println(grp);
    	System.out.println("****");
    	SplitSpendsListGroupsScreen listGroupsScreen = new SplitSpendsListGroupsScreen(true,true);
    	listGroupsScreen.VerifyScreen();
    	SplitSpendsAddGroupScreen addgrpscreen = listGroupsScreen.clickOnAddNewGroupButton();
    	List<User> yet2baddedusers = addgrpscreen.Addgroup(grp);
    	if(yet2baddedusers.size()>0)
		{
    		//verify empty group created
    		SplitSpendsMenuScreen menu = listGroupsScreen.clickOnMenuButton();
    		SplitSpendsListUsersScreen listusersscreen = menu.NavigatetoListUsers();
    		SplitSpendsAddUsersScreen adduserscreen = listusersscreen.clickOnAddNewUserButton();
    		adduserscreen.capturePageSnapshot();
    		//verify group members
		}
		else{
			// verifygroup
		}
    }
}
