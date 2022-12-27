package com.as.autot.tool.wrapper.api;
/**@author Sambodhan D. (Designer and Developer) June 2022*/
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;


public interface IHTMLWebDriver {
	final String sWebBreak="<BR> ";
	final String DOT = ".";
	final String BACKSLASH ="\\";
	final String LOCAL_PATH_ADJUST ="..\\"; // WRT extent report
	public void initiateBrowser();
	public void navigateTo(String newURL);
	public void tearDown();
	public void close();
	public void quit();
	
	public WebDriver getDriver();
	public SoftAssert getSoftAssert();
	public List<WebElement> findElements(By pByOption);
	public WebElement findElement(By pByOption);
	//public String getAShotScreenshot(WebDriver driver, String screenshotName) throws Exception;
	public boolean isAvailableOnPage(HTMLWebElement elementNode);
	public void ClearBrowserCache();
	void waitForPageLoad();
	public void resetSoftAssert();
	//void resetSoftAssertAndTestData(TestDataInstance pTDI);
	void logFailureAndTakeScreenshot(String sMessage, String sShortFileName);
	void logWithScreenshot(String sNonEmptyOrMessage, String sShortFileName, boolean bPASSOrFAIL);
	void logPassWithScreenshot(String sNonEmptyOrMessage, String sShortFileName);
	void logFailureAndTakeScreenshot(String sMessage, String sShortFileName, boolean bHardAssert);
	void logFailureAndTakeScreenshotDecision(String sNonEmptyOrMessage,  String sShortFileName, boolean bStatus);
	
	public void ThreadSleep(int iSec);
	

	void SeleniumScreenshot(String fileWithPath) throws Exception;
	public void logExtentInfo(String sMessage, boolean bHighLightMessage, boolean bTakeScreenshot);
	public void logExtentInfo(String sMessage, boolean bHighLightMessage);
	boolean isTestCaseCompleted();
	void setTestCaseCompleted(boolean isCompleted);

	void log(String sNonEmptyOrMessage, boolean bPASSOrFAIL);
	void log(String sNonEmptyOrMessage, Status logStatus);
	void testClosure();
	
	
	public void log(String sMessage, Status status, boolean isScreenshot);

	String getAuthToken(WebDriver driver, String[] scopeNames);
	
	

}
