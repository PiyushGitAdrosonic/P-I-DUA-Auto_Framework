package com.as.autot.tool.wrapper.api;
/**@author Sambodhan D. (Designer and Developer) June 2022*/

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.as.autot.business.BusinessException;
import com.as.autot.core.or.OrStruct;

public interface IHTMLWebElement {
	final static String ReporterNewLine = "<BR>";

	void assertTrue(boolean bBooleanCondition, String sMessage, String sFileName) throws Exception;
	void assertTrueReportDecision(boolean bBooleanCondition, String sMessage, String sFileName) throws Exception; // log verification status as Info
	void assertFalse(boolean bBooleanCondition, String sMessage, String sFileName) throws Exception;
	void assertEquals(String sActual, String SExpected, String message, String sFileName);
	void assertEquals(Float sActual, Float SExpected, String message, String sFileName) throws Exception;
	public void assertEquals( boolean actual, boolean expected, String message, String sFileName);
	void clickWithMultipleWaysForNavigation(OrStruct pValElem,String sElementName) throws Exception;
	void clickOnceClickable(OrStruct pElem) throws Exception;
	public void deselectAll(OrStruct myElementNode) throws Exception;

	public boolean isSelected(OrStruct myElementNode) throws Exception;
	//public void setCheckbox(IHTMLOrStruct myElementNode, boolean bSetChecked);
	public void verifyExpectedlMessage(OrStruct myElementNode, String psExpected, String psCallingMethod) throws Exception;	
	public void clearAndSetText(OrStruct pElem, String strToEnter) throws Exception;
	
	public void click(OrStruct pElem) throws Exception;
	public void selectOptionWithValue(OrStruct pElem, String sValue) throws Exception;
	public void selectOptionWithVisibleText(OrStruct pElem, String sValue) throws Exception;
	
	public void selectMultiOptionsForIndexsInArray(OrStruct pElem, int iIndexes[]) throws Exception;
	public WebElement waitForElementToBeClickable(OrStruct pElem, int waitInSeconds) throws BusinessException;
	public WebElement waitForElementToBeClickable(OrStruct pElem) throws Exception;
	public void waitTillElementIsInvisible(By objBy,int waitInSeconds);
	public void waitTillElementIsVisible(OrStruct pElem, int waitInSeconds) throws Exception;
	public void waitTillElementIsInvisible(OrStruct pElem, int waitInSeconds) throws Exception;
	void assertHardEquals(String sActual, String SExpected, String message);
	
	public void selectOptionWithArrayValues(OrStruct pElem, String sIndexes[]) throws Exception;
	public String getText(OrStruct pElem ) throws BusinessException;
	public void setCheckbox(OrStruct pElem, boolean bSetChecked);
	public boolean isAvailableOnPage(OrStruct pElem);
	public boolean isAvailableOnPage(OrStruct pElem,int iTime) throws BusinessException;
	public void selectOptionWithIndex(OrStruct pElem, int iIndex) throws Exception;
	
	
	void highLightElement(OrStruct objElement) throws Exception;
	void highLightElement(WebElement objElement) throws Exception;
	String getAttribute(OrStruct pElem, String sAttri) throws Exception;

//	void waitForAngularToFinishActivity();
	public boolean isDisplayed(OrStruct pElem);
	void verifyFieldsText(OrStruct pValElem, String psExpVal, String sFieldName ) throws Exception ;
	

	
	boolean isEnabled(OrStruct pValElem);
	
	void clickJavaScript(OrStruct pElem);
	public String getText(By objBY);
	
	OrStruct findSafeElement(By objBy);
	OrStruct findSafeElement(OrStruct objWB);
	
	void keyStroke(OrStruct objWB,CharSequence arg) throws Exception;
	ArrayList<WebElement> findSafeElements(By objBy);
	void logExtentInfo(String sMessage, boolean bHighLightMessage, boolean bTakeScreenshot);
	public ArrayList<WebElement> getWebElements(OrStruct vesselRowsOfPopup);
	public void setComboBoxIndex(OrStruct myElementNode, int iIndexToBeSelected) throws Exception;
	public void selectFirstOptionStartsWith(OrStruct myElementNode, String sInitialStartsWith) throws Exception;
	public void setComboBoxValue(OrStruct myElementNode, String value) throws Exception;
	public void setHardAssert_TCFailsAndStops(String sMessage, String sFileName, Exception e) throws Exception;
	public void setText(OrStruct pElem, String strToEnter) throws BusinessException;
	public void setHardAssert_TCFailsAndStops(String sMessage, String sFileName) throws Exception;
	public void setExtentInfo(String sMessage)throws Exception;
	
	public void setSoftAssert_TCFailsButContinuesExecution(String sMessage, String sFileName) throws Exception;
	public void setComboBoxVisibleText(OrStruct myElementNode, String sTextToBeSelected) throws Exception;
	public void doubleClick(OrStruct myElementNode) throws Exception;
	public void clearAndSetText(WebElement oWebElem, String strToEnter) throws Exception;
	
	public void setComboBoxValue(WebElement oWebElem, String value) throws Exception;
	void click(WebElement oWebElem);
	void setTextAndEnter(OrStruct pElem, String strToEnter) throws BusinessException;
	
}
