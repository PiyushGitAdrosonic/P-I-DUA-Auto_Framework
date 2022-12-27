package com.as.autot.business;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;

import com.as.autot.core.common.Constants;
import com.as.autot.core.common.RunConfig;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.common.TestDataInstance.Browser;
import com.as.autot.tool.wrapper.api.ConfiguredBrowserInstance;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.as.autot.tool.wrapper.api.ToolAPI;

/** @author Sambodhan D. (Designer and Developer) June 2022 */

public class AppUtil {
	private static final Logger LOGGER = LogManager.getLogger(AppUtil.class);

	public final static String HDRIVER = "HDriver", BROWSER = "BROWSER";

	public static boolean isTestNGExecuting() {
		return Constants.TESTEXETOOL_VALUE.equals(Constants.TESTEXETOOL_TESTNG);
	}

	public static IHTMLWebDriver preparePrequisites() throws UnknownHostException, MalformedURLException {
		String browserName = (String) TestDataInstance.properties.get("DEFAULT.BROWSER");
		return preparePrequisites(Browser.valueOf(browserName));
	}

	public static IHTMLWebDriver preparePrequisites(Browser browser)
			throws UnknownHostException, MalformedURLException {
		LOGGER.debug("AppUtil.preparePrequisites: BrokerDashboard verifyTheVesselData finished");
		WebDriver driver = null;
		ITestContext oIContext = Reporter.getCurrentTestResult().getTestContext();
		driver = ConfiguredBrowserInstance.getDriver(browser.name());
		String sMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		IHTMLWebDriver hDriver = ToolAPI.getHTMLWebDriver(driver, sMethodName);
		oIContext.setAttribute(getDriverContextCode(), hDriver);
		driver.get(TestDataInstance.getDefaultUrl());
		return hDriver;
	}

	public static IHTMLWebDriver preparePrequisitesUTOM() throws UnknownHostException, MalformedURLException {
		LOGGER.debug("AppUtil.preparePrequisites: BrokerDashboard verifyTheVesselData finished");
		WebDriver driver = null;
		ITestContext oIContext = Reporter.getCurrentTestResult().getTestContext();
		driver = ConfiguredBrowserInstance.getDriver("EDGE");
		String sMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		IHTMLWebDriver hDriver = ToolAPI.getHTMLWebDriver(driver, sMethodName);
		oIContext.setAttribute(getDriverContextCode(), hDriver);
		driver.get(RunConfig.getEnv());
		return hDriver;
	}

	public static IHTMLWebDriver preparePrequisites(boolean bNeedBrowser)
			throws UnknownHostException, MalformedURLException {
		LOGGER.debug("AppUtil.preparePrequisites: BrokerDashboard verifyTheVesselData finished");
		WebDriver driver = null;
		ITestContext oIContext = Reporter.getCurrentTestResult().getTestContext();
		if (bNeedBrowser) {
			driver = ConfiguredBrowserInstance.getDriver(RunConfig.getProperty(Constants.BROWSER));
			driver.get(RunConfig.getEnv());
		}
		String sMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		IHTMLWebDriver hDriver = ToolAPI.getHTMLWebDriver(driver, sMethodName);
		oIContext.setAttribute(getDriverContextCode(), hDriver);

		return hDriver;
	}

	public static String getDriverContextCode() {
		LOGGER.debug("AppUtil.getDriverContextCode: BrokerDashboard verifyTheVesselData finished");
		String sThreadId = "" + Thread.currentThread().getId();
		return HDRIVER + sThreadId;
	}

}
