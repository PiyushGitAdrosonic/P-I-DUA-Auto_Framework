package com.as.autot.tool.wrapper.api;

/**@author Sambodhan D. (Designer and Developer) June 2022*/

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.as.autot.core.common.Constants;
import com.as.autot.core.common.MarkupHelper;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.common.Util;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class HTMLWebDriver implements IHTMLWebDriver {
	
	private static final Logger LOGGER =  Logger.getLogger(HTMLWebDriver.class);

	SoftAssert sa = new SoftAssert();
	String sTestCode = null;
	private WebDriver driver = null;
	TestDataInstance dataInstance = null;
	private boolean isCompleted = false;

	public HTMLWebDriver(WebDriver pDriver, String sTestMethodName) {
		driver = pDriver;
	}

	public void setTestDataInstance(TestDataInstance pTDI) {
		dataInstance = pTDI;
	}

	@Override
	public void initiateBrowser() {
		driver = ConfiguredBrowserInstance.getChromeDriver();
		driver.manage().window().maximize();

	}

	public WebElement findElement(By pByOption) {
		return driver.findElement(pByOption);
	}

	public List<WebElement> findElements(By pByOption) {
		return driver.findElements(pByOption);
	}

	@Override
	public void navigateTo(String newURL) {
		driver.get(newURL);
	}

	@Override
	public void tearDown() {
		driver.close();
		driver.quit();

	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public void ThreadSleep(int iSec) {
		try {
			Thread.sleep(1000 * iSec);
		} catch (Exception e) {

		}
	}

	@Override
	public SoftAssert getSoftAssert() {
		return sa;
	}

	@Override
	public boolean isAvailableOnPage(HTMLWebElement elementNode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ClearBrowserCache() {
		driver.manage().deleteAllCookies();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // wait 5 seconds to clear cookies.

	}

	@Override
	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver pDriver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(pageLoadCondition);
	}

	@Override
	public void resetSoftAssert() {
		sa = new SoftAssert();
	}

	public void resetSoftAssertAndTestData(TestDataInstance pTDI) {
		dataInstance = pTDI;
		sa = new SoftAssert();
	}

	@Override
	public void SeleniumScreenshot(String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath + Constants.SCREENSHOTEXTN);
		FileUtils.copyFile(SrcFile, DestFile);
	}


	@Override
	public void logWithScreenshot(String sErrorMessage, String sShortFileName, boolean bPASSOrFAIL) {
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();

		try {
			String sScreenCapture = seleniumSimpleScreenshot(driver, sShortFileName);
			String sThreadID = "" + Thread.currentThread().getId();
			ExtentTest testLog = (ExtentTest) itestContext.getAttribute(Constants.EXTENTTEST + sThreadID);
			testLog.log(bPASSOrFAIL == true ? Status.PASS : Status.FAIL,
					MarkupHelper.createScreenshotURL(sScreenCapture, sErrorMessage, sErrorMessage));
			Reporter.log("Complete logWithScreensot is commented, revisit.");
		} catch (Exception e) {
			String sMessage = "ERROR: [" + sErrorMessage + "] [" + Util.getExceptionDesc(e) + "]";
			Reporter.log(sMessage);
			e.printStackTrace();
		}
	}

	@Override
	public void log(String sNonEmptyOrMessage, Status logStatus) {
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		try {
			String sThreadID = "" + Thread.currentThread().getId();
			ExtentTest testLog = (ExtentTest) itestContext.getAttribute(Constants.EXTENTTEST + sThreadID);
			testLog.log(logStatus, sNonEmptyOrMessage);
			if (logStatus == Status.FAIL)
				getSoftAssert().fail(sNonEmptyOrMessage);
			Reporter.log(sNonEmptyOrMessage);

		} catch (Exception e) {
			String sMessage = "ERROR: [" + sNonEmptyOrMessage + "] [" + Util.getExceptionDesc(e) + "]";
			Reporter.log(sMessage);
			e.printStackTrace();
		}
	}

	@Override
	public void log(String sNonEmptyOrMessage, boolean bPASSOrFAIL) {
		if (bPASSOrFAIL)
			log(sNonEmptyOrMessage, Status.PASS);
		else
			log(sNonEmptyOrMessage, Status.FAIL);
	}

	@Override
	public void logFailureAndTakeScreenshot(String sMessage, String sShortFileName) {
		logWithScreenshot(sMessage, sShortFileName, false);

	}

	@Override
	public void logFailureAndTakeScreenshot(String sNonEmptyOrMessage, String sFileName, boolean bIsHardAssert) {
		getSoftAssert().fail(sNonEmptyOrMessage);
		logFailureAndTakeScreenshot(sNonEmptyOrMessage, sFileName);
		if (bIsHardAssert) {
			Assert.assertTrue(!bIsHardAssert);
		}
	}

	@Override
	public void logPassWithScreenshot(String sNonEmptyOrMessage, String sShortFileName) {
		logWithScreenshot(sNonEmptyOrMessage, sShortFileName, true);

	}

	// log info status in extent report
	@Override
	public void logFailureAndTakeScreenshotDecision(String sNonEmptyOrMessage, String sShortFileName, boolean bStatus) {
		// TODO: SD, need to implement properly
		getSoftAssert().fail(sNonEmptyOrMessage);

		try {

			Reporter.log("Complete logFailureAndTakeScreensot is commented, revisit.");

		} catch (Exception e) {
			String sMessage = "ERROR: [" + sNonEmptyOrMessage + "] [" + Util.getExceptionDesc(e) + "]";
			Reporter.log(sMessage);
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private String seleniumSimpleScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File softScreenshot = scrShot.getScreenshotAs(OutputType.FILE);
		String fileWithPath = Constants.ScreenshotPath + screenshotName + Constants.SCREENSHOTEXTN;
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(softScreenshot, DestFile);
		return fileWithPath;
	}

	@Override
	public void logExtentInfo(String sMessage, boolean bHighLightMessage) {
		logExtentInfo(sMessage, bHighLightMessage, false);

	}

	@Override
	public void logExtentInfo(String sMessage, boolean bHighLightMessage, boolean bTakeScreenshot) {
		try {
			if (bHighLightMessage)
				log("logExtentInfo: Need to add code to highlight the message!", Status.INFO);

			if (bTakeScreenshot) {
				String sScreenCapture =  seleniumSimpleScreenshot(driver, sMessage);
				log(MarkupHelper.createScreenshotURL(sScreenCapture, sMessage, sMessage), Status.INFO);
			} else {
				log("logExtentInfo: Need to take screenshot!", Status.FAIL);
			}

			LOGGER.info(sMessage);

		} catch (Exception e) {
			String sMessage1 = "ERROR: logExtentInfo Failed for [" + sMessage + "]";
			Reporter.log(sMessage1);
			log(sMessage1, Status.FAIL);
			e.printStackTrace();
		}

	}

	@Override
	public boolean isTestCaseCompleted() {
		return isCompleted;
	}

	@Override
	public void setTestCaseCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public void testClosure() {
		getSoftAssert().assertAll();
		try {
			if(null!=driver){
				driver.quit();
			}
		}catch(Exception e) {}
		
		
	}

	

	@Override
	public void log(String sNonEmptyOrMessage, Status logStatus, boolean isScreenshot) {
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			String base64Image = scrShot.getScreenshotAs(OutputType.BASE64);
			
			String sThreadID = "" + Thread.currentThread().getId();
			ExtentTest testLog = (ExtentTest) itestContext.getAttribute(Constants.EXTENTTEST + sThreadID);
			
			if(isScreenshot) {
				testLog.log(logStatus,MarkupHelper.createBase64ScreenshotURL(base64Image, sNonEmptyOrMessage));
			}else {
				testLog.log(logStatus, sNonEmptyOrMessage);
			}
			if (logStatus == Status.FAIL)
				getSoftAssert().fail(sNonEmptyOrMessage);
			Reporter.log(sNonEmptyOrMessage);
		} catch (Exception e) {
			String sMessage = "ERROR: [" + sNonEmptyOrMessage + "] [" + Util.getExceptionDesc(e) + "]";
			Reporter.log(sMessage);
			e.printStackTrace();
		}
	}
	
	@Override
	public String getAuthToken(WebDriver driver, String[] scopeNames) {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		Integer temp = ((Number) jsExecutor.executeScript("return window.sessionStorage.length")).intValue();
		LOGGER.debug("Number of keys in session storage - " + temp);
		String tokenvalue = null;
		for (int t = 0; t < temp; t++) {
			String key = (String) jsExecutor.executeScript(String.format("return window.sessionStorage.key('%s');", t));
			LOGGER.debug(t + "=" + key);

			boolean matching = true;
		
			for (String scopeName : scopeNames) {
				if (!key.contains(scopeName)) {
					matching = false;
					break;
				}
			}
			if (matching == true) {
				tokenvalue = (String) jsExecutor
						.executeScript(String.format("return window.sessionStorage.getItem('%s')", key));
				break;
			}
		}
		String[] strValues = tokenvalue.split(",");
		HashMap<String, String> Valuesmap = new HashMap<String, String>();
		for (String Values : strValues) {
			String[] kv = Values.split(":");
			String k = kv[0].replace("\"", "");
			String v = kv[1].replace("\"", "");
			Valuesmap.put(k, v);
		}
		String strSessionToken = Valuesmap.get("secret");
		LOGGER.debug("strSessionToken :: "+ strSessionToken);
		return strSessionToken;
	}	
}
