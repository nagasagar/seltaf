package com.seltaf.core;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.seltaf.customexceptions.NotCurrentPageException;
import com.seltaf.customexceptions.SeltafException;
import com.seltaf.driver.DriverManager;
import com.seltaf.helpers.WaitHelper;
import com.seltaf.utils.ScreenshotUtility;
import com.seltaf.utils.WebUtility;
import com.seltaf.webelements.HtmlElement;
import com.seltaf.webelements.LinkElement;
import com.seltaf.webelements.WebPageSection;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.Wait.WaitTimedOutException;

public class SeltafPageObject extends BasePage {
	
	private static final Logger logger = Logger.getLogger(SeltafPageObject.class);
    private static final int MAX_WAIT_TIME_FOR_REDIRECTION = 3;
    private boolean frameFlag = false;
    private HtmlElement pageIdentifierElement = null;
    private String popupWindowName = null;
    private String windowHandle = null;
    private String title = null;
    private String url = null;
    private String bodyText = null;
    private String htmlSource = null;
    private String htmlSavedToPath = null;
    private String suiteName = null;
    private String outputDirectory = null;
    private String htmlFilePath = null;
    private String imageFilePath = null;
    
    
	protected WebDriver driver = DriverManager.getWebDriver(true);
	
	
	/**
     * Constructor for non-entry point page. The control is supposed to have reached the page from other API call.
     *
     * @throws  Exception
     */
    public SeltafPageObject() throws Exception {
        this(null, null);
    }

    /**
     * Constructor for non-entry point page. The control is supposed to have reached the page from other API call.
     *
     * @param   pageIdentifierElement
     *
     * @throws  Exception
     */
    public SeltafPageObject(final HtmlElement pageIdentifierElement) throws Exception {
        this(pageIdentifierElement, null);
    }

    /**
     * Base Constructor.
     *
     * @param   url
     *
     * @throws  Exception
     */
    public SeltafPageObject(final HtmlElement pageIdentifierElement, final String url) throws Exception {
        Calendar start = Calendar.getInstance();
        start.setTime(new Date());

        if (SeltafContextManager.getGlobalContext() != null
                && SeltafContextManager.getGlobalContext().getTestNGContext() != null) {
            suiteName = SeltafContextManager.getGlobalContext().getTestNGContext().getSuite().getName();
            outputDirectory = SeltafContextManager.getGlobalContext().getTestNGContext().getOutputDirectory();
        }

        this.pageIdentifierElement = pageIdentifierElement;
        driver = DriverManager.getWebDriver();

        if (url != null) {
            open(url);
        }

        // Wait for page load is applicable only for web test
        // When running tests on an iframe embedded site then test will fail if this command is not used
        if (SeltafContextManager.isWebTest()) {
            waitForPageToLoad();
        }

        assertCurrentPage(false);

        try {
            this.windowHandle = driver.getWindowHandle();
        } catch (Exception ex) {
            // Ignore for OperaDriver
        }


        Calendar end = Calendar.getInstance();
        start.setTime(new Date());

        long startTime = start.getTimeInMillis();
        long endTime = end.getTimeInMillis();
        if ((endTime - startTime) / 1000 > 0) {
            SeltafTestLogger.log("Open web page in :" + (endTime - startTime) / 1000 + "seconds");
        }
    }

	@Override
	protected void assertCurrentPage(boolean log) throws NotCurrentPageException {
		if (pageIdentifierElement == null) { }
        else if (this.isElementPresent(pageIdentifierElement.getBy())) { }
        else {
            try {
                if (!SeltafContextManager.getThreadContext().getCaptureSnapshot()) {
                    new ScreenshotUtility(driver).capturePageSnapshotOnException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            throw new NotCurrentPageException(getClass().getCanonicalName()
                    + " is not the current page.\nPageIdentifierElement " + pageIdentifierElement.toString()
                    + " is not found.");
        }

        if (log) {
        	SeltafTestLogger.logWebStep(null,
                "assert \"" + getClass().getSimpleName() + "\" is the current page"
                    + (pageIdentifierElement != null
                        ? " (assert PageIdentifierElement " + pageIdentifierElement.toHTML() + " is present)." : "."),
                false);
        }
		
	}
	
	@SuppressWarnings("deprecation")
	private void waitForPageToLoad() throws Exception {
        try {
            new Wait() {
                @Override
                public boolean until() {
                    try {
                        driver.switchTo().defaultContent();
                        return true;
                    } catch (UnhandledAlertException ex) {
                        WaitHelper.waitForSeconds(2);
                    } catch (WebDriverException e) { }

                    return false;
                }
            }.wait(String.format("Timed out waiting for page to load"),
                DriverManager.getDriverManager().getWebSessionTimeout());
        } catch (WaitTimedOutException ex) { }

        // populate page info
        try {
            populateAndCapturePageSnapshot();
        } catch (Exception ex) {

            // ex.printStackTrace();
            throw ex;
        }
    }
	
	private void open(final String url) throws Exception {

        if (this.getDriver() == null) {
        	SeltafTestLogger.logWebStep(url, "Launch application", false);
            driver = driverManager.createWebDriver();
        }

        setUrl(url);
        try {

            // Navigate to app URL for browser test
            if (SeltafContextManager.isWebTest()) {
                driver.navigate().to(url);
            }
        } catch (UnreachableBrowserException e) {

            // handle if the last window is closed
        	SeltafTestLogger.logWebStep(url, "Launch application", false);
            driver = driverManager.createWebDriver();
            maximizeWindow();
            driver.navigate().to(url);
        } catch (UnsupportedCommandException e) {
        	SeltafTestLogger.log("get UnsupportedCommandException, retry");
            driver = driverManager.createWebDriver();
            maximizeWindow();
            driver.navigate().to(url);
        } catch (org.openqa.selenium.TimeoutException ex) {
        	SeltafTestLogger.log("got time out when loading " + url + ", ignored");
        } catch (org.openqa.selenium.UnhandledAlertException ex) {
        	SeltafTestLogger.log("got UnhandledAlertException, retry");
            driver.navigate().to(url);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new SeltafException(e);
        }

        // switchToDefaultContent();
    }
	
	private void populateAndCapturePageSnapshot() {
        try {
            setTitle(driver.getTitle());
            htmlSource = driver.getPageSource();
            try {
                bodyText = driver.findElement(By.tagName("body")).getText();
            } catch (StaleElementReferenceException ignore) {
                logger.warn("StaleElementReferenceException got in populateAndCapturePageSnapshot");
                bodyText = driver.findElement(By.tagName("body")).getText();
            }

        } catch (UnreachableBrowserException e) { // throw

            // UnreachableBrowserException
            throw new WebDriverException(e);
        } catch (WebDriverException e) {
            throw e;
        }

        capturePageSnapshot();
    }
	
	 public void capturePageSnapshot() {
	        ScreenShot screenShot = new ScreenshotUtility(driver).captureWebPageSnapshot();
	        this.title = screenShot.getTitle();

	        if (screenShot.getHtmlSourcePath() != null) {
	            htmlFilePath = screenShot.getHtmlSourcePath().replace(suiteName, outputDirectory);
	            htmlSavedToPath = screenShot.getHtmlSourcePath();
	        }

	        if (screenShot.getImagePath() != null) {
	            imageFilePath = screenShot.getImagePath().replace(suiteName, outputDirectory);
	        }

	        SeltafTestLogger.logWebOutput(url, title + " (" + SeltafTestLogger.buildScreenshotLog(screenShot) + ")", false);

	    }
	
	protected void setHtmlSavedToPath(final String htmlSavedToPath) {
        this.htmlSavedToPath = htmlSavedToPath;
    }

    protected void setTitle(final String title) {
        this.title = title;
    }

    protected void setUrl(final String openUrl) {
        this.url = openUrl;
    }
    
    public final void maximizeWindow() {
        new WebUtility(driver).maximizeWindow();
    }
    
    public String getBodyText() {
        return bodyText;
    }

    public final String getCookieByName(final String name) {
        if (driver.manage().getCookieNamed(name) == null) {
            return null;
        }

        return driver.manage().getCookieNamed(name).getValue();
    }

    public final int getElementCount(final HtmlElement element)  {
        return driver.findElements(element.getBy()).size();
    }

    public String getEval(final String expression) {
        CustomAssertion.assertTrue(false, "focus not implemented yet");
        return null;
    }

    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public String getHtmlSavedToPath() {
        return htmlSavedToPath;
    }

    public String getHtmlSource() {
        return htmlSource;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
    
    public String getLocation() {
        return driver.getCurrentUrl();
    }

    public String getPopupWindowName() {
        return popupWindowName;
    }

    public int getTimeout() {
        return SeltafContextManager.getThreadContext().getWebSessionTimeout();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return url;
    }

    public String getCanonicalURL() {
        return new LinkElement("Canonical URL", By.cssSelector("link[rel=canonical]")).getAttribute("href");
    }

    public String getWindowHandle() {
        return windowHandle;
    }

    public final void goBack() {
    	SeltafTestLogger.logWebStep(null, "goBack", false);
        driver.navigate().back();
        frameFlag = false;
    }

    public final void goForward() {
    	SeltafTestLogger.logWebStep(null, "goForward", false);
        driver.navigate().forward();
        frameFlag = false;
    }

    public final boolean isCookiePresent(final String name) {
        return getCookieByName(name) != null;
    }

    public boolean isFrame() {
        return frameFlag;
    }
    
    public final void refresh() throws NotCurrentPageException {
    	SeltafTestLogger.logWebStep(null, "refresh", false);
        try {
            driver.navigate().refresh();
        } catch (org.openqa.selenium.TimeoutException ex) {
        	SeltafTestLogger.log("got time out customexception, ignore");
        }
    }

    public final void resizeTo(final int width, final int height) {
        new WebUtility(driver).resizeWindow(width, height);
    }

    public final void selectFrame(final int index) {
    	SeltafTestLogger.logWebStep(null, "select frame using index" + index, false);
        driver.switchTo().frame(index);
        frameFlag = true;
    }

    public final void selectFrame(final By by) {
    	SeltafTestLogger.logWebStep(null, "select frame, locator={\"" + by.toString() + "\"}", false);
        driver.switchTo().frame(driver.findElement(by));
        frameFlag = true;
    }

    public final void selectFrame(final String locator) {
    	SeltafTestLogger.logWebStep(null, "select frame, locator={\"" + locator + "\"}", false);
        driver.switchTo().frame(locator);
        frameFlag = true;
    }

    public final void selectWindow() throws NotCurrentPageException {
    	SeltafTestLogger.logWebStep(null, "select window, locator={\"" + getPopupWindowName() + "\"}", false);

        // selectWindow(getPopupWindowName());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        WaitHelper.waitForSeconds(1);

        // Check whether it's the expected page.
        assertCurrentPage(true);
    }

    public final void selectWindow(final int index) throws NotCurrentPageException {
    	SeltafTestLogger.logWebStep(null, "select window, locator={\"" + index + "\"}", false);
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[index]);
    }

    public final void selectNewWindow() throws NotCurrentPageException {
    	SeltafTestLogger.logWebStep(null, "select new window", false);
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        WaitHelper.waitForSeconds(1);
    }

   public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (UnhandledAlertException e) { }
    }
   
   public WebElement getElement(final By by, final String elementName) {
       WebElement element = null;
       try {
           element = driver.findElement(by);
       } catch (ElementNotFoundException e) {
    	   SeltafTestLogger.errorLogger(elementName + " is not found with locator - " + by.toString());
           throw e;
       }

       return element;
   }

   public String getElementUrl(final By by, final String name) {
       return getElement(by, name).getAttribute("href");
   }

   public String getElementText(final By by, final String name) {
       return getElement(by, name).getText();
   }

   public String getElementSrc(final By by, final String name) {
       return getElement(by, name).getAttribute("src");
   }
    public void assertHtmlSource(final String text) {
    	SeltafTestLogger.logWebStep(null, "assert text \"" + text + "\" is present in page source.", false);
        assertHTML(getHtmlSource().contains(text), "Text: {" + text + "} not found on page source.");
    }

    public void assertKeywordNotPresent(final String text) {
    	SeltafTestLogger.logWebStep(null, "assert text \"" + text + "\" is present in page source.", false);
        Assert.assertFalse(getHtmlSource().contains(text), "Text: {" + text + "} not found on page source.");
    }

    public void assertLocation(final String urlPattern) {
    	SeltafTestLogger.logWebStep(null, "assert location \"" + urlPattern + "\".", false);
        assertHTML(getLocation().contains(urlPattern), "Pattern: {" + urlPattern + "} not found on page location.");
    }

    public void assertPageSectionPresent(final WebPageSection pageSection) {
    	SeltafTestLogger.logWebStep(null, "assert pagesection \"" + pageSection.getName() + "\"  is present.", false);
        assertElementPresent(new HtmlElement(pageSection.getName(), pageSection.getBy()));
    }

    public void assertTitle(final String text) {
    	SeltafTestLogger.logWebStep(null, "assert text \"" + text + "\"  is present on title.", false);
        assertHTML(getTitle().contains(text), "Text: {" + text + "} not found on page title.");

    }
    
    public void assertCookiePresent(final String name) {
    	SeltafTestLogger.logWebStep(null, "assert cookie " + name + " is present.", false);
        assertHTML(getCookieByName(name) != null, "Cookie: {" + name + "} not found.");
    }

}
