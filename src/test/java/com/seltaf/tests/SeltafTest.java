package com.seltaf.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.seltaf.core.SeltafTestPlan;
import com.seltaf.pageobjects.MercureToursLoginPage;

public class SeltafTest extends SeltafTestPlan {

	@Test
	public void test_Home_Page_Appear_Correct() {

		// Create Login Page object

		MercureToursLoginPage Loginpage = new MercureToursLoginPage(true);

		// Verify login page title

		String loginPageTitle = Loginpage.getPageTitle();
		
		Assert.assertTrue(loginPageTitle.toLowerCase().contains("mercury"));
	}
}
