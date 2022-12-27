package com.as.autot.tool.wrapper.api;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfiguredBrowserInstance {

	private static final Logger LOGGER =  Logger.getLogger(ConfiguredBrowserInstance.class);
	
	public static WebDriver getDriver(String browserName) throws UnknownHostException, MalformedURLException {
		WebDriver driver=null;

		switch (browserName.toUpperCase()) {	
		case "EDGE":
			driver = getEDGEDriver();	
			break;
		case "FIREFOX":
			driver = getFireFoxDriver();
			break;
		case "CHR_GRID":
			driver = getChromeGrid();
			break;
		case "CHROME"://jump to default option
		default:
			driver = getChromeDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;	

	}

	public static WebDriver getEDGEDriver()
	{
		WebDriverManager.edgedriver().setup();
		EdgeOptions edgeOptions = new EdgeOptions();
		WebDriver driver = new EdgeDriver(edgeOptions);
		return driver;
	}


	public static WebDriver getFireFoxDriver()
	{
		FirefoxOptions chromeOptions = new FirefoxOptions();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new FirefoxDriver(chromeOptions);
		return driver;
	}

	public static WebDriver getChromeDriver()
	{
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("auth-server-whitelist='tom/UTOM/Underwriting'");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(chromeOptions);
		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		
//		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("tom/UTOM");
//		((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("janie", "Shipowners22"));
		
		
		
		return driver;
	}

	public static WebDriver getChromeGrid() throws UnknownHostException, MalformedURLException {
		WebDriver driver=null;
		LOGGER.debug("==========Grid CHROME==========");
//		DesiredCapabilities cap = new DesiredCapabilities();
//		System.setProperty("webdriver.chrome.driver", Constants.DriverPath + "\\chromedriver.exe");
//		cap.setBrowserName("chrome");
//
//		// driver = new RemoteWebDriver(new URL("http://" +  System.getProperty("grid_Host") + ":4444/wd/hub"), cap);
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
//		driver.manage().window().maximize();
//		LOGGER.debug("Here======RemoteDriver set Chrome Driver");
		return driver;
	}


}
