package com.seltaf.core;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.seltaf.driver.DriverManager;
import com.seltaf.webelements.HtmlElement;

public class SeltafPageObject {
	
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

        SeleniumTestsPageListener.informPageLoad(this);

        Calendar end = Calendar.getInstance();
        start.setTime(new Date());

        long startTime = start.getTimeInMillis();
        long endTime = end.getTimeInMillis();
        if ((endTime - startTime) / 1000 > 0) {
            TestLogging.log("Open web page in :" + (endTime - startTime) / 1000 + "seconds");
        }
    }
}
