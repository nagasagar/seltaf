package com.seltaf.webdriverfactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.seltaf.driver.DriverConfig;



public class ChromeDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory  {

	public ChromeDriverFactory(DriverConfig cfg) {
		super(cfg);
	}
	
	protected WebDriver createNativeDriver() {
        return new ChromeDriver(new ChromeCapabilitiesFactory().createCapabilities(webDriverConfig));
    }
	
	 @Override
	    public WebDriver createWebDriver() throws IOException {
	        DriverConfig cfg = this.getWebDriverConfig();

	        driver = createNativeDriver();

	        setImplicitWaitTimeout(cfg.getImplicitWaitTimeout());
	        if (cfg.getPageLoadTimeout() >= 0) {
	            setPageLoadTimeout(cfg.getPageLoadTimeout());
	        }

	        this.setWebDriver(driver);
	        return driver;
	    }

	    protected void setPageLoadTimeout(final long timeout) {
	        try {
	            driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	        } catch (UnsupportedCommandException e) { }
	    }

}
