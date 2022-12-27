package com.as.autot.tool.wrapper.api;

/**@author Sambodhan D. (Designer and Developer)
9-Aug-2022

This class will give the driver isntance based either Selenium or Appium.
*/
import org.openqa.selenium.WebDriver;


public class ToolAPI {
	public static IHTMLWebDriver getHTMLWebDriver(WebDriver pDriver, String sTestMethodName) {
		IHTMLWebDriver obj = new HTMLWebDriver(pDriver, sTestMethodName);
		return obj;
	}
	
	public static IHTMLWebElement getHTMLWebElement(IHTMLWebDriver pDriver) {
		return new HTMLWebElement(pDriver);
	}

}
