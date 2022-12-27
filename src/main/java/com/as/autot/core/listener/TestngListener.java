package com.as.autot.core.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.as.autot.core.BaseTest;
import com.as.autot.core.common.Constants;
import com.as.autot.core.extentreport.ExtentManagerConfig;
import com.as.autot.core.extentreport.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
/**@author Sambodhan D. (Designer and Developer) June 2022*/

public class TestngListener extends BaseTest implements ITestListener {//
	public static final String sTagSt = "<Mark>", sTagEn = "</Mark>";;
	
	private static final Logger LOGGER =  Logger.getLogger(TestngListener.class);
	
    static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onStart(ITestContext oContext) {
    	ITestResult iTestResult = Reporter.getCurrentTestResult();
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManagerConfig.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
    	String sMethod = iTestResult.getMethod().getMethodName();
    	ExtentTestManager.startTest(iTestResult.getMethod().getDescription());
    	LOGGER.debug("LSTN : onTestStart");
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    	String sMethod = iTestResult.getMethod().getMethodName();    	
    	this.getExtentTest(iTestResult).log(Status.PASS, sMethod+ ": Test passed");
    	LOGGER.debug("LSTN : onTestSuccess");
    }
    @Override

    public void onTestFailure(ITestResult iTestResult) {
        String sMethod = iTestResult.getMethod().getMethodName();
    	this.getExtentTest(iTestResult).log(Status.FAIL, sMethod+"Test Failed");
    	LOGGER.debug("LSTN : onTestFailure");
        
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String sMethod = iTestResult.getMethod().getMethodName();
    	this.getExtentTest(iTestResult).log(Status.SKIP, sMethod+"Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    	LOGGER.debug("========LSTN onTestFailedButWithinSuccessPercentage: BrokerDashboard verifyTheVesselData finished");
    }
    
	private ExtentTest getExtentTest(ITestResult iTestResult) {
		ITestContext itestContext = iTestResult.getTestContext();
		String sThreadID = "" + Thread.currentThread().getId();
		return (ExtentTest) itestContext.getAttribute(Constants.EXTENTTEST+sThreadID);
	}
}
