package com.seltaf.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * Hello world!
 *
 */
public class DriverManager 
{
	private static WebDriver driver = null;
	public static WebDriver getWebdriver() {
		/* String currentDir = System.getProperty("user.dir");
	        System.out.println("Current dir using System:" +currentDir);
		System.setProperty("webdriver.chrome.driver", currentDir + "\\src\\test\\resources\\chromedriver.exe");*/
		 ChromeDriverManager.getInstance().setup();
		if(driver==null)
			driver = new ChromeDriver();
		return driver;
	}
	
	public static void cleanup(){
		driver.quit();
	}
	
}
