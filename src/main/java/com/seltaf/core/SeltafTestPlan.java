package com.seltaf.core;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.seltaf.driver.DriverManager;

public class SeltafTestPlan {
	
	@BeforeSuite(alwaysRun = true)
    public void beforeTestSuite(final ITestContext TestNGContext) throws IOException {
        System.out.println("####################################################");
        System.out.println("start");
        System.out.println("####################################################");
        System.out.println(TestNGContext.getSuite().getParameter("appURL"));
        
    }
	@AfterSuite(alwaysRun = true)
    public void afterTestSuite() throws IOException {
        System.out.println("####################################################");
        System.out.println("end");
        System.out.println("####################################################");
      //  DriverManager.cleanup();
    }


}
