package com.seltaf.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;

public class SeltafContext {
	public static final String TEST_CONFIGURATION = "testConfig";
    public static final String APP_URL = "appURL";
    public static final String WEB_SESSION_TIME_OUT = "webSessionTimeOut";
    public static final String IMPLICIT_WAIT_TIME_OUT = "implicitWaitTimeOut";
    public static final String EXPLICIT_WAIT_TIME_OUT = "explicitWaitTimeOut";
    public static final String PAGE_LOAD_TIME_OUT = "pageLoadTimeout";
    public static final String WEB_DRIVER_GRID = "webDriverGrid";
    public static final String RUN_MODE = "runMode";
    public static final String BROWSER = "browser";
    public static final String BROWSER_VERSION = "browserVersion";
    public static final String WEB_PLATFORM = "webPlatform";
    public static final String FIREFOX_USER_PROFILE_PATH = "firefoxUserProfilePath";
    public static final String USE_DEFAULT_FIREFOX_PROFILE = "useFirefoxDefaultProfile";
    public static final String OPERA_USER_PROFILE_PATH = "operaUserProfilePath";
    public static final String FIREFOX_BINARY_PATH = "firefoxBinaryPath";
    public static final String CHROME_DRIVER_PATH = "chromeDriverPath";
    public static final String CHROME_BINARY_PATH = "chromeBinaryPath";
    public static final String IE_DRIVER_PATH = "ieDriverPath";
    public static final String USER_AGENT = "userAgent";

    public static final String Set_Assume_Untrusted_Certificate_Issuer = "setAssumeUntrustedCertificateIssuer";
    public static final String Set_Accept_Untrusted_Certificates = "setAcceptUntrustedCertificates";
    public static final String ENABLE_JAVASCRIPT = "enableJavascript";
    public static final String NTLM_AUTH_TRUSTED_URIS = "ntlmAuthTrustedUris";
    public static final String BROWSER_DOWNLOAD_DIR = "browserDownloadDir";
    public static final String BROWSER_WINDOW_SIZE = "browserWindowSize";
    public static final String ADD_JS_ERROR_COLLECTOR_EXTENSION = "addJSErrorCollectorExtension";

    public static final String WEB_PROXY_ENABLED = "webProxyEnabled";
    public static final String WEB_PROXY_TYPE = "webProxyType";
    public static final String WEB_PROXY_ADDRESS = "webProxyAddress";

    public static final String TEST_ENTITY = "testEntity";

    public static final String REPORT_GENERATION_CONFIG = "reportGenerationConfig";
    public static final String OPEN_REPORT_IN_BROWSER = "openReportInBrowser";
    public static final String CAPTURE_SNAPSHOT = "captureSnapshot";
    public static final String ENABLE_EXCEPTION_LISTENER = "enableExceptionListener";

    public static final String DP_TAGS_INCLUDE = "dpTagsInclude";
    public static final String DP_TAGS_EXCLUDE = "dpTagsExclude";

    public static final String SSH_COMMAND_WAIT = "sshCommandWait";
    public static final String SOFT_ASSERT_ENABLED = "softAssertEnabled";

    public static final String OUTPUT_DIRECTORY = "outputDirectory";
    public static final String WEB_DRIVER_LISTENER = "webDriverListener";

    public static final String TEST_METHOD_SIGNATURE = "testMethodSignature";
    public static final String PLUGIN_CONFIG_PATH = "pluginConfigPath";

    public static final String TEST_DATA_FILE = "testDataFile";

    public static final String TEST_TYPE = "testType";

    // Appium specific properties
    public static final String APPIUM_SERVER_URL = "appiumServerURL";
    public static final String AUTOMATION_NAME = "automationName";
    public static final String MOBILE_PLATFORM_NAME = "platformName";
    public static final String MOBILE_PLATFORM_VERSION = "mobilePlatformVersion";
    public static final String DEVICE_NAME = "deviceName";
    public static final String APP = "app";

    public static final String BROWSER_NAME = "browserName";
    public static final String APP_PACKAGE = "appPackage";
    public static final String APP_ACTIVITY = "appActivity";
    public static final String NEW_COMMAND_TIMEOUT = "newCommandTimeout";

    // SauceLabs specific properties
    public static final String VERSION = "version";
    public static final String PLATFORM = "platform";
    public static final String SAUCELABS_URL = "sauceLabsURL";

    private Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();
    private Map<String, Object> contextDataMap = Collections.synchronizedMap(new HashMap<String, Object>());
    
    private ITestContext testNGContext = null;

}
