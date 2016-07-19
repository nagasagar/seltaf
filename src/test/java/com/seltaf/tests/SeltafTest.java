package com.seltaf.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.seltaf.core.SeltafTestPlan;
import com.seltaf.pageobjects.MercureTourLoginPage;

public class SeltafTest extends SeltafTestPlan {

	@Test
	public void test_Home_Page_Appear_Correct() throws Exception {

		// Create Login Page object

		MercureTourLoginPage Loginpage = new MercureTourLoginPage(true);

		// Verify login page title

		String loginPageTitle = Loginpage.getPageTitle();
		
		Assert.assertTrue(loginPageTitle.toLowerCase().contains("mercury"));
	}
}
