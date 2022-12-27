package com.as.autot.tool.wrapper.api;

import java.time.Duration;
/**@author Sambodhan D. (Designer and Developer) */
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.as.autot.business.AppUtil;
import com.as.autot.business.BusinessException;
import com.as.autot.core.common.Constants;
import com.as.autot.core.common.Util;
import com.as.autot.core.or.OrStruct;
import com.aventstack.extentreports.Status;
/**@author Sambodhan D. (Designer and Developer) June 2022*/
public class HTMLWebElement implements IHTMLWebElement {

	IHTMLWebDriver hDriver = null;
	private WebDriver driver = null;
	boolean bTestNGExecuting = false;

	private static final Logger LOGGER =  Logger.getLogger(HTMLWebElement.class);
	SoftAssert mysa = null;

	public HTMLWebElement(IHTMLWebDriver pDriver) {
		hDriver = pDriver;
		driver = hDriver.getDriver();
		mysa = hDriver.getSoftAssert();
		bTestNGExecuting = AppUtil.isTestNGExecuting();
	}

	public boolean isTestNGExecuting() {
		return bTestNGExecuting;
	}

	public WebElement getWebElement(OrStruct pOrObj) throws Exception {
		By byOfOrObj = pOrObj.getBy();
		WebElement element = driver.findElement(byOfOrObj);
		return element;

	}

	public ArrayList<WebElement> getWebElements(OrStruct pOrObj) {
		By byOfOrObj = pOrObj.getBy();
		ArrayList<WebElement> listElement = (ArrayList<WebElement>) driver.findElements(byOfOrObj);
		return listElement;

	}

	@Override
	public void setComboBoxIndex(OrStruct WebElement, int iIndexToBeSelected) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectFirstOptionStartsWith(OrStruct WebElement, String sInitialStartsWith) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setComboBoxValue(OrStruct myElementNode, String value) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHardAssert_TCFailsAndStops(String sMessage, String sFileName, Exception e) throws Exception {
		hDriver.logFailureAndTakeScreenshot(sMessage, sFileName);
		Assert.fail(sMessage + ReporterNewLine + e.getMessage());

	}

	@Override
	public void logExtentInfo(String sMessage, boolean bHighLightMessage, boolean bTakeScreenshot) {
		if (bTestNGExecuting) {
			hDriver.logExtentInfo(sMessage, bHighLightMessage, bTakeScreenshot);
		}

	}

	@Override
	public void setHardAssert_TCFailsAndStops(String sMessage, String sFileName) throws Exception {
		if (bTestNGExecuting) {
			hDriver.logFailureAndTakeScreenshot(sMessage, sFileName);
			Assert.fail(sMessage);
		}
	}

	@Override
	public void assertTrue(boolean isHardAssert, String sMessage, String sFileName) throws Exception {
		if (bTestNGExecuting) {
			String sStatus = isHardAssert == true ? Util.PASS : Util.FAIL;
			sMessage = sStatus + sMessage;
			mysa.assertTrue(isHardAssert, sMessage);
			if (!isHardAssert) {
				hDriver.logFailureAndTakeScreenshot(sMessage, sFileName);
			} else {
				hDriver.log(sMessage, isHardAssert);
				Reporter.log(sMessage + ReporterNewLine);
			}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void setExtentInfo(String sMessage) throws Exception {
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		//TODO

	}

	@Override
	public void assertFalse(boolean bBooleanCondition, String sMessage, String sFileName) throws Exception {
		if (bTestNGExecuting)
			assertTrue(!bBooleanCondition, sMessage, sFileName);
	}

	@Override
	public void setSoftAssert_TCFailsButContinuesExecution(String sMessage, String sFileName) throws Exception {
		if (bTestNGExecuting) {
			hDriver.logFailureAndTakeScreenshot(sMessage, sFileName);
			LOGGER.debug("Temp");
			mysa.fail(sMessage);
		}
	}

	@Override
	public void doubleClick(OrStruct myElementNode) throws Exception {
		WebElement oWebElem = getWebElement(myElementNode);
		Actions action = new Actions(driver);
		action.moveToElement(oWebElem).doubleClick().build().perform();
	}

	@Override
	public boolean isSelected(OrStruct myElementNode) throws Exception {
		WebElement oWebElem = getWebElement(myElementNode);
		return oWebElem.isSelected();
	}

	@Override
	public void setCheckbox(OrStruct myElementNode, boolean bSetChecked) {
		try {
			WebElement oWebElem = getWebElement(myElementNode);
			if (bSetChecked != oWebElem.isSelected()) {
				oWebElem.click();
			}
		} catch (Exception e) {
			String sMessage = "ElementNotFound:SetText Failed: " + Util.getExceptionDesc(e);
			LOGGER.info(sMessage);
		}

	}

	public boolean isAvailableOnPage(OrStruct pElem) {
		WebElement curElement = null;
		try {
			curElement = waitForElementToBeClickable(pElem, Constants.ISAVAILABLEONPAGELIMIT);
		} catch (Exception e) {
		}
		if (null != curElement)
			return true;
		return false;
	}

	public boolean isAvailableOnPage(OrStruct pElem, int iTime) throws BusinessException {
		WebElement curElement = null;
		try {
			curElement = waitForElementToBeClickable(pElem, iTime);
		} catch (Exception e) {
			throw new BusinessException("["+pElem.getName()+"] is not available on the page", false);
		}
		if (null != curElement) {
			return true;
		}else return false;
	}

	@Override
	public void clickJavaScript(OrStruct pElem) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", pElem);
	}

	@Override
	public void click(OrStruct pElem) {
		try {
			WebElement oWebElem = getWebElement(pElem);
			oWebElem.click();
			hDriver.log("["+ pElem.getName() +"] is clicked",Status.INFO);
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", pElem);
			hDriver.log("["+ pElem.getName() +" is clicked via JS",Status.INFO);
		}
	}

	@Override
	public void clickOnceClickable(OrStruct pElem) throws Exception {
		waitForElementToBeClickable(pElem, Constants.ELEMENTCLICKABLELIMIT);
		WebElement oWebElem = getWebElement(pElem);
		oWebElem.click();
	}

	@Override
	public void selectOptionWithValue(OrStruct pElem, String sValue) throws Exception {
		WebElement oWebElem = getWebElement(pElem);
		Select ddl = new Select(oWebElem);
		ddl.selectByValue(sValue);
	}

	@Override
	public void selectOptionWithVisibleText(OrStruct pElem, String sValue) throws Exception {
		WebElement oWebElem = getWebElement(pElem);
		Select ddl = new Select(oWebElem);
		ddl.selectByVisibleText(sValue);
	}

	@Override
	public void selectMultiOptionsForIndexsInArray(OrStruct pElem, int[] iIndexes) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void highLightElement(OrStruct objElement) throws Exception {
		WebElement oWebElem = getWebElement(objElement);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", oWebElem);
	}

	@Override
	public void highLightElement(WebElement oWebElem) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", oWebElem);
	}

	public WebElement waitForElementToBeClickable(OrStruct pElem, int waitInSeconds) throws BusinessException {
		WebElement oWebElem=null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(pElem.getBy()));

			oWebElem = getWebElement(pElem);
			return oWebElem;
		}catch(TimeoutException e) {
			//e.printStackTrace();
			throw new BusinessException(pElem.getName()+" is either not available on the page or not clickable as it's timeout", false);
		}catch(Exception e) {
			//e.printStackTrace();
			throw new BusinessException(pElem.getName()+" is either not available on the page or not clickable", false);
		}
		
	}
	@Override
	public WebElement waitForElementToBeClickable(OrStruct pElem) throws Exception {
		return waitForElementToBeClickable(pElem, pElem.getwaitInSec());
	}

	public void waitTillElementIsInvisible(By objBy, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
	}

	@Override
	public void waitTillElementIsInvisible(OrStruct pElem, int waitInSeconds) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		WebElement oWebElem = getWebElement(pElem);
		wait.until(ExpectedConditions.invisibilityOf(oWebElem));
	}

	@Override
	public void waitTillElementIsVisible(OrStruct pElem, int waitInSeconds) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		WebElement oWebElem = getWebElement(pElem);
		wait.until(ExpectedConditions.visibilityOf(oWebElem));
	}

	@Override
	public void selectOptionWithArrayValues(OrStruct pElem, String[] sIndexes) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setComboBoxVisibleText(OrStruct pElem, String sTextToBeSelected) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectOptionWithIndex(OrStruct pElem, int iIndex) throws Exception {
		WebElement oWebElem = getWebElement(pElem);
		Select ddl = new Select(oWebElem);
		ddl.selectByIndex(iIndex);
	}

	@Override
	public void deselectAll(OrStruct pElem) throws Exception {
		// TODO Auto-generated method stub

	}

	public SoftAssert getSoftAssert() {
		return mysa;
	}

	@Override
	public void clearAndSetText(OrStruct pElem, String strToEnter) throws BusinessException {
		try {
		WebElement oWebElem = getWebElement(pElem);
		oWebElem.clear();
		oWebElem.sendKeys(strToEnter);
		}catch(Exception e) {
			throw new BusinessException("Unable to type in field ["+pElem.getName()+"] on the page", false);
		}
	}

	@Override
	public String getAttribute(OrStruct pElem, String sAttri) throws Exception {
		WebElement oWebElem = getWebElement(pElem);
		return oWebElem.getAttribute(sAttri);

	}

	@Override
	public void setText(OrStruct pElem, String strToEnter) throws BusinessException {
		try {
			WebElement oWebElem = getWebElement(pElem);
			oWebElem.sendKeys(strToEnter);
		}catch(Exception e) {
			throw new BusinessException("Unable to type at ["+pElem.getName()+"] field", false);
		}
	}

	@Override
	public String getText(OrStruct myElementNode) throws BusinessException {
		try {
			WebElement oWebElem = getWebElement(myElementNode);
			return oWebElem.getText();
		}catch(Exception e) {
			throw new BusinessException("Unable to get text field ["+myElementNode.getName()+"] on the page", false);
		}
	}

	@Override
	public String getText(By objBY) {
		return driver.findElement(objBY).getText();
	}

	@Override
	public boolean isDisplayed(OrStruct pElem) {
		boolean isVisible = false;
		try {
			WebElement oWebElem = getWebElement(pElem);
			Dimension d = oWebElem.getSize();
			isVisible = (d.getHeight() > 0 && d.getWidth() > 0);
		} catch (Exception e) {
			isVisible = false;
		}
		return isVisible;
	}

	@Override
	public void assertEquals(boolean bActual, boolean bExpected, String message, String sFileName) {
		if (bTestNGExecuting) {
			String sStatus = bActual == bExpected ? Util.PASS : Util.FAIL;

			String sMessage = sStatus + " Validation for '" + message + "'.";
			mysa.assertEquals(bActual, bExpected, sMessage);
			// Reporter.log(sMessage+ReporterNewLine);
			if (sStatus.contains("FAIL")) {
				hDriver.logFailureAndTakeScreenshot(sMessage, sFileName);
			} else {
				sMessage = sMessage + "Actual:[" + bActual + "] Expected:[" + bExpected + "]";
				LOGGER.info(sMessage);
				Reporter.log(sMessage + ReporterNewLine);
			}
		}
	}

	@Override
	public void assertEquals(String sActual, String sExpected, String message, String sFileName) {
		if (bTestNGExecuting) {
			String sStatus = (sActual.equals(sExpected)) ? Util.PASS : Util.FAIL;

			String sMessage = sStatus + " Validation for '" + message + "'.";
			mysa.assertEquals(sActual, sExpected, sMessage);
			if (sStatus.contains("FAIL")) {
				hDriver.logFailureAndTakeScreenshot(sMessage + "Actual:[" + sActual + "] Expected:[" + sExpected + "]",
						sFileName);
			} else {
				sMessage = sMessage + "Actual:[" + sActual + "] Expected:[" + sExpected + "]";
				LOGGER.info(sMessage);
				Reporter.log(sMessage + ReporterNewLine);
			}
		}

	}

	@Override
	public void assertEquals(Float fActual, Float fExpected, String message, String sFileName) throws Exception {
		if (bTestNGExecuting) {
			String sStatus = (fActual.equals(fExpected)) ? Util.PASS : Util.FAIL;

			String sMessage = sStatus + " Validation for '" + message + "'.";
			mysa.assertEquals(fActual, fExpected, sMessage);
			if (sStatus.contains("FAIL")) {
				hDriver.logFailureAndTakeScreenshot(
						sMessage + "Actual:[" + fActual + "] Expected:[" + fExpected + "]" + ReporterNewLine,
						sFileName);
			} else {
				sMessage = sMessage + "Actual:[" + fActual + "] Expected:[" + fExpected + "]";
				LOGGER.info(sMessage);
				Reporter.log(sMessage + ReporterNewLine);
			}
		}
	}

	@Override
	public void verifyFieldsText(OrStruct pValElem, String psExpVal, String sFieldName) throws Exception {
		// validate and log the validation of the VPN
		boolean bCheck = false;
		String sFinalValStr = "";
		String sActValue = getText(pValElem).trim();
		bCheck = null == sActValue || null == psExpVal ? false : sActValue.contains(psExpVal);

		sFinalValStr = " Validation for " + sFieldName + ": Actual status=[" + sActValue + "] Exp[" + psExpVal + "]";
		LOGGER.info(sFinalValStr);
		assertTrue(bCheck, sFinalValStr, sFieldName);

	}

	@Override
	public void verifyExpectedlMessage(OrStruct pElem, String psExpected, String psCallingMethod) throws Exception {
		// validate and log the validation of the VPN
		boolean bCheck = false;
		String sFinalValStr = "";
		String sActValue = getText(pElem).trim();
		bCheck = null == sActValue || null == psExpected ? false : sActValue.contains(psExpected);

		sFinalValStr = " Validation for " + psCallingMethod + ": Actual status=[" + sActValue + "] Exp[" + psExpected
				+ "]";
		LOGGER.info(sFinalValStr);
		assertTrue(bCheck, sFinalValStr, psCallingMethod);

	}

	@Override
	public void clickWithMultipleWaysForNavigation(OrStruct pValElem, String sElementName) throws Exception {

		int count = 0;
		String sTemp = "";
		WebElement oWebElem = null;
		while (isAvailableOnPage(pValElem, 5) & count < 6) {
			switch (count++) {
			case 0:
				click(pValElem);
				sTemp = "From Case 0, Selenium CLick works";
				break;
			case 1:
				Actions action = new Actions(driver);
				oWebElem = getWebElement(pValElem);
				action.moveToElement(oWebElem).build().perform();
				oWebElem.sendKeys(Keys.ENTER);
				sTemp = "From Case 1, Send Keys work keys.enter";
				break;
			case 2:
				Actions action1 = new Actions(driver);
				oWebElem = getWebElement(pValElem);
				action1.moveToElement(oWebElem).click().build().perform();
				sTemp = "From Case 2, Mouse single click work, Action class";
				break;
			case 3:
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				oWebElem = getWebElement(pValElem);
				executor.executeScript("arguments[0].click();", oWebElem);
				sTemp = "From Case 3, JavaScript click works";
				break;
			case 4:
				Actions action2 = new Actions(driver);
				oWebElem = getWebElement(pValElem);
				action2.moveToElement(oWebElem).doubleClick().build().perform();
				sTemp = "From Case 4, Mouse double click works";
				break;
			default:
				String sErrorMsg = Util.FAIL + " Unable to click on " + sElementName;
				Reporter.log(sErrorMsg);
				sTemp = sErrorMsg;
				setHardAssert_TCFailsAndStops("Method Name: clickWithMultipleWaysForNavigation", sElementName,
						new Exception(sErrorMsg));
				count = 10;
				break;
			}
			// Use for avoiding Review MVC clicks
			hDriver.ThreadSleep(3);
		}

		LOGGER.info(sTemp + " " + sElementName);
	}

	@Override
	public boolean isEnabled(OrStruct pValElem) {
		boolean bCheck = false;
		try {
			WebElement oWebElem = getWebElement(pValElem);
			if (oWebElem.isEnabled()) {
				bCheck = true;
			}
		} catch (Exception e) {
			bCheck = false;
		}
		return bCheck;
	}

	@Override
	public ArrayList<WebElement> findSafeElements(By objBy) {
		ArrayList<WebElement> listElements = null;
		try {
			listElements = (ArrayList<WebElement>) driver.findElements(objBy);
		} catch (Exception e) {
			LOGGER.error("Provided [" + objBy.toString() + "] does not Exist");
		}
		return listElements;

	}

//	@Override
//	public WebElement findSafeElement(By objBy) {
//		try {
//			WebElement oWebElem = getWebElement(objBy);
//			return driver.findElement(oWebElem);
//		} catch (Exception e) {
//			LOGGER.error("Provided [" + objBy.toString() + "] does not Exist");
//			return null;
//		}
//
//	}

	@Override
	public void keyStroke(OrStruct objWB, CharSequence keyType) throws Exception {
		WebElement oWebElem = getWebElement(objWB);
		oWebElem.sendKeys(keyType);

	}

	@Override
	public void assertHardEquals(String sActual, String sExpected, String message) {
		if (bTestNGExecuting) {
			String sStatus = (sActual.equals(sExpected)) ? Util.PASS : Util.FAIL;

			String sMessage = sStatus + " Validation for '" + message + "'.";
			if (sStatus.contains("FAIL")) {
				hDriver.log(sMessage + "Actual:[" + sActual + "] Expected:[" + sExpected + "]", false);
				Assert.fail(sMessage + "Actual:[" + sActual + "] Expected:[" + sExpected + "]");
			} else {
				sMessage = sMessage + "Actual:[" + sActual + "] Expected:[" + sExpected + "]";
				LOGGER.info(sMessage);
				Reporter.log(sMessage + ReporterNewLine);
			}
		}
	}

	// Log verification status as INFO
	public void assertTrueReportDecision(boolean bBooleanCondition, String sMessage, String sScreenshotFileName)
			throws Exception {
		if (bTestNGExecuting) {
			String sStatus = bBooleanCondition == true ? Util.PASS : Util.FAIL;
			sMessage = sStatus + sMessage;
			mysa.assertTrue(true, sMessage);
			if (!bBooleanCondition) {
				hDriver.logFailureAndTakeScreenshotDecision(sMessage, sScreenshotFileName, false);
			} else {
				Reporter.log(sMessage + ReporterNewLine);
			}
		}
	}


	@Override
	public OrStruct findSafeElement(By objBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrStruct findSafeElement(OrStruct objWB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAndSetText(WebElement oWebElem, String strToEnter) throws BusinessException {
		try {
		oWebElem.clear();
		oWebElem.sendKeys(strToEnter);
		}catch(Exception e) {
			throw new BusinessException("Unable to type in field ["+oWebElem.toString()+"] on the page", false);
		}
	}
	
	@Override
	public void setComboBoxValue(WebElement oWebElem, String value) throws Exception {

		oWebElem.click();
		oWebElem.sendKeys(Keys.BACK_SPACE);

		oWebElem.sendKeys(value);
		hDriver.ThreadSleep(2);
		oWebElem.sendKeys(Keys.RETURN);

	}
	
	@Override
	public void click(WebElement oWebElem) {
		try {
			oWebElem.click();	
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", oWebElem);
		}
	}
	
	@Override
	public void setTextAndEnter(OrStruct pElem, String strToEnter) throws BusinessException {
		try {
		WebElement oWebElem = getWebElement(pElem);
		oWebElem.clear();
		oWebElem.sendKeys(strToEnter);
		hDriver.ThreadSleep(1);
		oWebElem.sendKeys(Keys.ENTER);
		hDriver.ThreadSleep(2);
		}catch(Exception e) {
			throw new BusinessException("Unable to type in field ["+pElem.getName()+"] on the page", false);
		}
	}

}