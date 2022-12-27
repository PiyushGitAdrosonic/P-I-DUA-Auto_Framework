package com.as.autot.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.as.autot.business.AppUtil;
import com.as.autot.business.Structure;
import com.as.autot.core.common.Constants;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.extentreport.ExtentTestManager;
import com.as.autot.core.or.OrList;
import com.as.autot.core.or.OrStruct;
import com.as.autot.tool.wrapper.api.IHTMLWebDriver;
import com.as.autot.tool.wrapper.api.IHTMLWebElement;
import com.as.autot.tool.wrapper.api.ToolAPI;
import com.as.autot.utility.JsonReadWrite;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



/**@author Sambodhan D. (Designer and Developer) June 2022*/

public class BasePage {

	private static final Logger LOGGER =  Logger.getLogger(BasePage.class);
	
	protected WebDriver driver = null;
	protected HashMap<String, OrStruct> orMap = null;
	protected IHTMLWebDriver hDriver = null;
	private ExtentTest testLog = null;
	protected TestDataInstance testData = null;
	private boolean loggedIn = false;
	protected IHTMLWebElement elem = null;

	boolean bTestNGExecuting = false;

	public boolean isTestNGExecuting() {
		return bTestNGExecuting;
	}

	public final String TITLE = "title";

	public IHTMLWebElement getElem() {
		return elem;
	}

	public BasePage() {
		String className = this.getClass().getName();
		LOGGER.debug("The curre[" + className + "]");
		orMap = new HashMap<String, OrStruct>();
		bTestNGExecuting = AppUtil.isTestNGExecuting();
		//LOGGER.debug("The bTestNGExecuting[" + bTestNGExecuting + "]");

	}

	protected boolean isLoggedIn() {
		return loggedIn;
	}

	protected void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	protected String getOR_PNI(String sClassName) {
		String aPath = Constants.OR_PNI;
		return aPath+sClassName+".json";
	}
	
	protected String getOR_UTOM(String sClassName) {
		String aPath = Constants.OR_UTOM ;
		return aPath+sClassName+".json";
	}
	
	protected void initObjectRepository(BasePage page) {
		String aPath = Constants.OR_PNI+page.getClass().getSimpleName()+".json";
		OrList orList = new JsonReadWrite().readGivenJsonToOrList(aPath);
		
		HashMap<String, OrStruct> objects = new HashMap<String, OrStruct>();
		for (Iterator<OrStruct> iterator = orList.getOrList().iterator(); iterator.hasNext();) {
				OrStruct orStruct = (OrStruct) iterator.next();
				objects.put(orStruct.getName(), orStruct);
			}
		
		Field[] fields = page.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Structure.class)) {
            	Structure set = field.getAnnotation(Structure.class);
                field.setAccessible(true);
                try {
                    field.set(page, objects.get(set.name()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
	}
	
	public BasePage(TestDataInstance testDataI, IHTMLWebDriver pDriver) {
		
		hDriver = pDriver;
		driver = pDriver.getDriver();
		testData = testDataI;
		elem = ToolAPI.getHTMLWebElement(hDriver);
		orMap = new HashMap<String, OrStruct>();
		testLog = ExtentTestManager.getTest();
		bTestNGExecuting = AppUtil.isTestNGExecuting();
		//LOGGER.debug("The bTestNGExecuting[" + bTestNGExecuting + "]");
		
//		objHeader = new HeaderPage(hDriver,testData,elem);
	}

	public TestDataInstance getTestData() {
		return testData;
	}

	protected void navigateTo(String sURL) throws Exception {
		driver.get(sURL);
	}

	protected OrStruct getElement(String elementName) {
		return orMap.get(elementName);
	}

	public void log(Status status, String sMessage) {
		if (bTestNGExecuting) {
			hDriver.log(sMessage, status);
		}
	}
	
	public void log(Status status, String sMessage, boolean isScreenshot) {
		if (bTestNGExecuting) {
			hDriver.log(sMessage, status, isScreenshot);
		}
	}

	public void logSuccessTakeScreenshot(String sMessage, String sFileName) {
		if (bTestNGExecuting)
			hDriver.logPassWithScreenshot(sMessage, sFileName);
	}
	

	public void logFailureTakeScreenshot(String sMessage, String sFileName) {

		if (bTestNGExecuting)
			hDriver.logFailureAndTakeScreenshot(sMessage, sFileName);
	}

	public void listToMap(ArrayList<OrStruct> orList) {
		for (Iterator<OrStruct> iterator = orList.iterator(); iterator.hasNext();) {
//		 for (OrStruct currOrStruct : inpORList) {{
			OrStruct orStruct = (OrStruct) iterator.next();
			orMap.put(orStruct.getName(), orStruct);

		}
	}



//	protected BasePage verifyThePageTitle(String sExpPageTitle) throws Exception{
//		hDriver.waitForPageLoad();
//		String sActTitle = driver.findElement(byTitlePage).getAttribute("text").trim();
//		boolean bTitleValidation=sActTitle.contains(sExpPageTitle);
//
//		//if(!bTitleValidation) {
//			String sFinalStr="VerifyThePageTitle validation: Expected Title["+sExpPageTitle+"] Actual Title["+sActTitle+"]";
//			elem.assertTrue(bTitleValidation, sFinalStr);
//			
//		//}
//
//		return this;
//	}
//	public HeaderPage Header(){
//	return objHeader;
//}

}
