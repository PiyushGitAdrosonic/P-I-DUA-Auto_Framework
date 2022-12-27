package com.as.autot.core.common;
/**@author Sambodhan D. (Designer and Developer) June 2022*/


public class Constants {
	public static final String NEWLINE = "\n", IgnoreKeyword = "IGN", TRUE="TRUE", FALSE="FALSE", EXTENTTEST="EXTENTTEST", BROWSER="BROWSER";
	
		
	public static final int COMMONWAIT=60;
	public static final int ISAVAILABLEONPAGELIMIT = COMMONWAIT;
	public static final int ELEMENTCLICKABLELIMIT = COMMONWAIT;
	public static final int INVISIBILITYOFELEMENT = COMMONWAIT;
	public static final int IMPLICITYWAITTIME = 20;
	public static final int VISIBILITYOFELEMENT = COMMONWAIT;
	public static final String TESTEXETOOL = "TESTEXETOOL";
	public static final String TESTEXETOOL_TESTNG = "TESTNG";
//	public static final String TESTEXETOOL_VALUE = System.getProperty(TESTEXETOOL);
	public static final String TESTEXETOOL_VALUE = "TESTNG";
	
	
	public static final String Separator = ":";
	public static final int PRODUCTQUANTITY = 1;
	public static final String BASEPATH = System.getProperty("user.dir");

	
//	public static final String ScreenshotPath = BASEPATH+ "\\screenshot\\";
	public static final String ScreenshotPath = "C:\\PNI_Automation"+ "\\screenshot\\";
	public static final String RUNCONFIGPROP = BASEPATH+ "\\src\\main\\resources\\runconfig.properties";
	public static final String DATACONFIGPROP = BASEPATH+ "\\src\\main\\resources\\TestDataTestcaseMap.properties";
	public static final String OR_PNI = BASEPATH+ "\\src\\test\\resources\\pom\\";
	public static final String OR_UTOM = BASEPATH+ "\\src\\main\\java\\com\\as\\autot\\core\\pom\\or\\utom\\";
	
	public static final String TEST_DATA_REPOSITORY = BASEPATH + "\\src\\test\\resources\\data\\";

	public static final String TESTDATA = BASEPATH + "\\Testdata\\";

	// public static final String ScreenshotPath =
	// System.getProperty("user.dir")+"\\screenshots";
	public static final String SCREENSHOTEXTN = ".jpg";


	public static final String EXTENTREPORTOUTPUTPATH = BASEPATH+ "\\ExtentReports\\";
	public static final String EXTENTREPORTNAME = "ExetntReport", EXTENTTIMESTAMP="EXTENTTIMESTAMP";
	public static final String EXTENTPROJ= "Shipowners";
	public static final String EXTENTUSER= "AutoTestersName";
	public static final String EXTENTVER= "1";
		

	// Parallel Execution section
	public static final int MAXPARALLELUSERLOGIN = 10;
	public static final String SOFTASSERT = "SOFTASSERT";


	
	public static String getCurrentProjectPath() {
		return BASEPATH;
	}

}
