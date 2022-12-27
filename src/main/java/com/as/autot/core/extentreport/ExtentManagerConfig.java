package com.as.autot.core.extentreport;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import com.as.autot.core.common.Constants;
import com.as.autot.core.common.TestDataInstance;
import com.as.autot.core.common.Util;
import com.as.autot.utility.DataHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/** @author Sambodhan D. (Designer and Developer) June 2022 */
public class ExtentManagerConfig {
	private static String NAMESEPARATOR = "_";
	public static final ExtentReports extentReports = new ExtentReports();
	
	private static final Logger LOGGER =  Logger.getLogger(ExtentManagerConfig.class);

	public synchronized static ExtentReports createExtentReports() {
		String sTimeStamp = Util.getTimeStamp();
		String ExtentName = Constants.EXTENTREPORTNAME + NAMESEPARATOR + sTimeStamp + NAMESEPARATOR
				+ Constants.EXTENTVER;
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/" + ExtentName + ".html");
		Reporter.getCurrentTestResult().getTestContext().setAttribute(Constants.EXTENTTIMESTAMP, sTimeStamp);
		String sPath = reporter.getFile().getAbsolutePath();
		LOGGER.debug("The extent report path: " + sPath);
		reporter.config().setReportName("AdrosonicTest");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Project Name", TestDataInstance.properties.getProperty("PROJECT.NAME"));
		extentReports.setSystemInfo("Environment", TestDataInstance.properties.getProperty("DEFAULT.ENV"));
		extentReports.setSystemInfo("Author", System.getProperty("user.name"));
		extentReports.setSystemInfo("Date", DataHandler.getCurrentDate());
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		//extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
		try {
			extentReports.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {}
		return extentReports;
	}
	
	public static void main(String[] args) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		String formattedDate = df.format(new Date());
		LOGGER.debug(formattedDate);
	}
}
