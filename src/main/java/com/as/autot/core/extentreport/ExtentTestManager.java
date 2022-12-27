package com.as.autot.core.extentreport;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.as.autot.core.common.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
/**@author Sambodhan D. (Designer and Developer) June 2022*/
public class ExtentTestManager {
	static Map<String, ExtentTest> extentTestMap = new HashMap<>();
	public static ExtentReports extent = null;
	
	private static final Logger LOGGER =  Logger.getLogger(ExtentTestManager.class);
	
	static  {
		if(null==extent) {
			LOGGER.debug("========ETM ExtentManagerConfig.createExtentReports created!");
		extent=ExtentManagerConfig.createExtentReports();
		}
	}

//	public static synchronized ExtentTest getTest(String testName) {
//		LOGGER.debug("========ETM getTest");
//		return extentTestMap.get(testName+  Thread.currentThread().getId());
//	}
	public static synchronized ExtentTest getTest() {
		ITestResult itResult = Reporter.getCurrentTestResult();
		String sMethod = itResult.getMethod().getMethodName();
		//LOGGER.debug("========ETM getTest ExtentTest["+(extentTestMap.get(sMethod+  Thread.currentThread().getId())+"]"));
		return extentTestMap.get(sMethod+  Thread.currentThread().getId());
	}
	public static synchronized ExtentTest startTest(String desc) {
		String sId = ""+(Thread.currentThread().getId());
		ITestResult itResult = Reporter.getCurrentTestResult();
		String sMethod = itResult.getMethod().getMethodName();
		LOGGER.debug("========ETM startTest test["+sMethod+"] desc["+desc+"");
		ExtentTest test = extent.createTest(sMethod, desc);
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		//Set the extent Test to make it available to the running Test flow
		itestContext.setAttribute(Constants.EXTENTTEST+sId, test);
		LOGGER.debug("========ETM startTest key["+sMethod+ Thread.currentThread().getId()+"]");
		extentTestMap.put(sMethod+ sId, test);
		return test;
	}
	public static synchronized void endTest() {
		LOGGER.debug("========ETM endTest");
		if(null!=extent)
		extent.flush();
	}
	
}
