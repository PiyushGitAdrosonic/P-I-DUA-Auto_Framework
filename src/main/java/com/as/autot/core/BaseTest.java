package com.as.autot.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**@author Sambodhan D. (Designer and Developer)
 * 09-Aug-2022
 *  */
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.as.autot.business.AppUtil;
import com.as.autot.core.common.Constants;
import com.as.autot.core.common.Util;
import com.as.autot.core.extentreport.ExtentTestManager;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.aventstack.extentreports.ExtentTest;
/**@author Sambodhan D. (Designer and Developer) June 2022*/

public class BaseTest {

	private static final Logger LOGGER =  Logger.getLogger(BaseTest.class);
	
	boolean isTestNGExecuting = false;

	public BaseTest() {
		isTestNGExecuting = AppUtil.isTestNGExecuting();
		LOGGER.debug("========BT isTestNGExecuting[" + isTestNGExecuting + "]");
	}

	protected ExtentTest getExtentTest() {
		LOGGER.debug("========BT getExtentTest");
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		String sThreadID = "" + Thread.currentThread().getId();
		return (ExtentTest) itestContext.getAttribute(Constants.EXTENTTEST + sThreadID);
	}


	@BeforeMethod(alwaysRun = true)
	protected synchronized void beforeMethod(ITestContext context) {
		ITestResult itResult = Reporter.getCurrentTestResult();
		LOGGER.debug("========BT beforeMethod");
		String sMethod = itResult.getMethod().getMethodName();
//		ExtentTestManager.startTest(sMethod, itResult.getMethod().getDescription());
		Util.setSoftAssert(sMethod);
	}

	@AfterMethod(alwaysRun = true)
	synchronized void afterMethod(Method method, ITestResult itResult, ITestContext oContext) {
		String sMethod = itResult.getMethod().getMethodName();
		if (itResult.isSuccess())
			LOGGER.debug("========BT testing ");
		LOGGER.debug("========BT afterMethod");
		ExtentTestManager.endTest();
		System.gc();
		
		try {
			if(null!=getDriver()){
				getDriver().quit();
			}
		}catch(Exception e) {}
	}

	@AfterSuite(alwaysRun = true)
	synchronized void afterTestSuite() {

	}

	public IHTMLWebDriver getDriver(ITestContext oContext) {
		LOGGER.debug("========BT getDriver1");
		return (IHTMLWebDriver) oContext.getAttribute(AppUtil.getDriverContextCode());
	}

	public IHTMLWebDriver getDriver() {
		LOGGER.debug("========BT getDriver2");
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		return getDriver(itestContext);
	}






	public void FileWrite(String absolutePath, String sInput) throws Exception {
		try {
			LOGGER.debug("======FileWrite============ File:[" + absolutePath + "]");
			File f = new File(absolutePath);

			// Checking if the specified file exists or not
			if (f.exists()) {

				LOGGER.debug("!!!!!!!!!!!==Does  Exists");
				FileWriter myWriter = new FileWriter(absolutePath);
				myWriter.write(sInput);
				myWriter.close();
				LOGGER.debug("Successfully wrote to the file.");
			} else {
				LOGGER.debug("Does not Exists");
			}
		} catch (IOException e) {
			LOGGER.debug("An error occurred.");
			e.printStackTrace();
			throw new Exception ("BaseTest.FileWrite failed!"+e.getMessage());
		}
	}

}
