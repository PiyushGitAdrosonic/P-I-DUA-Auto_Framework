package com.as.autot.core.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
/**@author Sambodhan D. (Designer and Developer) June 2022*/
public class Util {
	public static final String PASS="<p style=\"color:blue;\">PASS: ", FAIL="<p style=\"color:red;\">FAIL: ";
	static String arrCurrencyStorage[]= {"$"};
	protected static Logger LOGGER = Logger.getLogger(Util.class);


	public static float convertStringToFloat(String psFloatValue) {
		String sFloatValue = new DecimalFormat("0.000").format(Float.parseFloat(psFloatValue));
		Float fFloatActual = Float.parseFloat(sFloatValue);
		return fFloatActual;
	}

	public static Float convertStrToNumber(String sNumber) {		
		return Float.parseFloat(sNumber);
	}

	public static double convertTo2DecimanFormat(double pdInputValue) {
		DecimalFormat df2 = new DecimalFormat(".##");
		return Double.parseDouble(df2.format(pdInputValue));
	}
	
	public String getHTMLColourStatusMessage(boolean bBooleanCondition, String sMessage) throws Exception {
		String sStatus = bBooleanCondition == true ? PASS : FAIL;
		sMessage = sStatus + sMessage;
		return sMessage;
	}
	public static int getDotCount(String sNumber) {
		Pattern p = Pattern.compile("[.]");
		Matcher matcher = p.matcher(sNumber);
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}

	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}



	public static String getRandomNumber() throws Exception {
		String sName = "";
		SimpleDateFormat sdf = new SimpleDateFormat("ddmmSSS");
		Random r = new Random();
		int i = r.nextInt(9000);
		sName =  sdf.format(new Date()) + i;

		return sName;
	}

	public static Float getTotalSummaryValue(List<WebElement> orderTotalSummary) throws Exception {
		Float fActualTotal=0.000f; 

		for(WebElement objWB : orderTotalSummary) {
			if(objWB.getText().trim().length()!=0) {
				Float fTemp =	getFormatedPrice(objWB.getText().trim());
				fActualTotal = fActualTotal + fTemp;
			}
		}

		return fActualTotal;
	}

	public static HashMap<String, String> returnNewCopyOfHashMap(HashMap<String, String> oExistingHMap) {
		if (null==oExistingHMap) return null;
		HashMap<String, String> newHMap = new HashMap<String, String>();
		for (String sKey : oExistingHMap.values()) { 
			newHMap.put(sKey, oExistingHMap.get(sKey));
		}
		return newHMap;


	}

	public static String removeCurrrencySymbol(String text) {
		String sToReturn=null;
		for(int i=0;i<arrCurrencyStorage.length;i++) {
			if(text.contains(arrCurrencyStorage[i])) {
				text=text.replace(arrCurrencyStorage[i], "").trim();
			}
		}
		sToReturn=text;
		return sToReturn;
	}

	public static ArrayList<String> tokenizeString(String sTokenizeString, String sSeparator) {
		ArrayList<String> aList = new ArrayList<String> ();
		StringTokenizer stk = new StringTokenizer(sTokenizeString, sSeparator);
		while(stk.hasMoreTokens()) {
			aList.add(stk.nextToken());
		}
		return aList;
	}

	public static String getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
		return sdf.format(new Date());
	}

	public static String getTodaysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		return sdf.format(new Date());
	}	
	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

	}	
	public static String getScreenTagForTestNGReporter(String sFileToStringAsScreenShot, String sMessageName) {
		//String sHeight="34", sWidth="50";	
		//"<a href=\""+filePath+"\"><img src=\""+filePath+"\" alt=\"CapturedScreen\" height=\""+sHeight+"\" width=\""+sWidth+"  border=\"0\"></a><BR>";
		String sLink = "<a href=\"" + sFileToStringAsScreenShot+"\"  target=\"_blank\">"+sMessageName+"</a>";
		// "<a href=\""+sFolderForScreenShot+"/"+getTimeStamp()+Constants.SCREENSHOTEXTN+"\" alt=\"Click here for screenshot\" ></a><BR>";
		return sLink;
	}

	public static String getExceptionDesc(Exception e) {
		int exceptionLength = e.toString().length()>300?300:e.toString().length();
		String strExceptionDesc = e.toString().substring(0, exceptionLength);
		return strExceptionDesc;
	}

	public static String getExceptionDesc(String sExceptionDesc) {
		int iLen=sExceptionDesc.length();
		int exceptionLength = iLen>300?300:iLen;
		String strExceptionDesc = sExceptionDesc.substring(0, exceptionLength);
		return strExceptionDesc;
	}

	public static Float getFormatedPrice(String sNumber) throws Exception {
		float returnValue = 0;
		if(null==sNumber) throw new Exception ("The provided value is incorrect ["+sNumber+"]");

		String sValue=sNumber.replaceAll("[^0-9.,-]", "");
		// price contains comma as well as dot 
		if(sValue.contains(".") && sValue.contains(",")) {
			if(sValue.lastIndexOf(".")> sValue.lastIndexOf(",")) {
				returnValue = getDotTypeValue(sValue);
			}
			else if (sValue.lastIndexOf(".") < sValue.lastIndexOf(",")) {	
				returnValue = getCommaTypeValue(sValue);
			}
		}else if(sValue.contains(".")) {
			returnValue = getDotTypeValue(sValue);

		}else if(sValue.contains(",")) {
			returnValue = getCommaTypeValue(sValue);
		}else {
			LOGGER.debug("ERROR no comma or Dots are received");
			throw new Exception ("The provided value is incorrect ["+sNumber+"]");
		}
		return returnValue;
	}


	private static String convertMultipleDots(String sUpdate) {
		int iLastIndexDot=sUpdate.lastIndexOf(".");
		String sFirstH = sUpdate.substring(0,iLastIndexDot);
		String sSecoH = sUpdate.substring(iLastIndexDot+1);
		sFirstH=sFirstH.replaceAll("[^0-9]", "");
		String sNewStr = sFirstH+"."+sSecoH;		
		return sNewStr;		
	}

	public static String prepareCompareString(String sExp, String sAct ) {				
		return " Expected["+sExp+"] Actual["+sAct+"]<P>";		
	}


	private static float getDotTypeValue(String sNumber) {
		//4343,343,435.90 OR 23.22.33.44
		sNumber = sNumber.replace(",", "");
		if(getDotCount(sNumber)>1)
			sNumber =convertMultipleDots(sNumber);
		return convertStrToNumber(sNumber);	
	}

	private static float getCommaTypeValue(String sNumber) {
		//444.443.33,00
		sNumber = sNumber.replace(".", "");
		sNumber = sNumber.replace(",", ".");
		if(getDotCount(sNumber)>1)
			sNumber =convertMultipleDots(sNumber);
		return convertStrToNumber(sNumber);		
	}	

	public static synchronized void setSoftAssert(String testName) {
		ITestContext itestContext = Reporter.getCurrentTestResult().getTestContext();
		SoftAssert sa = new SoftAssert();
		itestContext.setAttribute(Constants.SOFTASSERT, sa);
	}
	public static synchronized SoftAssert getSoftAssert(String testName) {
		SoftAssert sa = (SoftAssert) Reporter.getCurrentTestResult().getTestContext().getAttribute(Constants.SOFTASSERT);
		return sa;
	}
}
