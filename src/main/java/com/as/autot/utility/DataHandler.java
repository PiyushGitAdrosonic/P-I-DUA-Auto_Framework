package com.as.autot.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.as.autot.business.BusinessException;

import nl.flotsam.xeger.Xeger;



/**
 * This class has set of methods which are used to evaluate different business rules
 * 
 * @author Anuj Tripathi
 *
 */


public class DataHandler {

	private static final Logger LOGGER =  Logger.getLogger(DataHandler.class);

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/YYYY";
	public static final String DEFAULT_TEXT_PATTERN = "[A-Za-z0-9]";

	public enum TimeUnit {
		SECOND, MINUTE, HOUR, DAY, MONTH, YEAR
	}


	
	/**
	 * This method will return system date in DEFAULT DATE FORMAT 
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDate(DEFAULT_DATE_FORMAT);
	}

	/**
	 * This method will return system date in provided date format 
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrentDate(String dateFormat) {
		Date currentDate = new Date();
		return getStringFromDate(currentDate, dateFormat);
	}

	
	/**
	 * This method will return date for any Date object in default date format
	 *  
	 * @param date
	 * @return
	 */
	public static String getStringFromDate(Date date) {
		return getStringFromDate(date, DEFAULT_DATE_FORMAT);
	}

	
	/**
	 * This method will return date for any Date object and date format
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String getStringFromDate(Date date, String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}

	/**
	 * This method will return Date object from date string in dafault date format
	 * 
	 * @param dateString
	 * @return
	 * @throws BusinessException
	 */
	public static Date getDateFromString(String dateString) throws BusinessException {
		return getDateFromString(dateString, DEFAULT_DATE_FORMAT);
	}

	/**
	 * This method will return Date object from date string in provided date format
	 * @param dateString
	 * @param dateFormat
	 * @return
	 * @throws BusinessException
	 */
	public static Date getDateFromString(String dateString, String dateFormat) throws BusinessException {

		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException e) {
			throw new BusinessException(
					"Unable to parse date [" + dateString + "] with provided format [" + dateFormat + "]", false);
		}
		return date;
	}

	/**
	 * This method will return Date object from any date with time difference
	 * Example : Current Date + 7 Days, (16/12/2022) - 2 Years
	 * 
	 * @param dateString
	 * @param difference
	 * @param unit
	 * @return
	 * @throws BusinessException
	 */
	public static Date getDate(String dateString, int difference, TimeUnit unit) throws BusinessException {
		Date date = getDateFromString(dateString);
		return getDate(date, difference, unit);
	}

	/**
	 * This method will return Date object from Date with time difference
	 * Example : Current Date + 7 Days, (16/12/2022) - 2 Years
	 * 
	 * @param date
	 * @param difference
	 * @param unit
	 * @return
	 * @throws BusinessException
	 */

	public static Date getDate(Date date, int difference, TimeUnit unit) throws BusinessException {

		Calendar calender = Calendar.getInstance();
		calender.setTime(date);

		switch (unit) {
		case SECOND:
			calender.add(Calendar.SECOND, difference);
			break;
		case MINUTE:
			calender.add(Calendar.MINUTE, difference);
			break;
		case HOUR:
			calender.add(Calendar.YEAR, difference);
			break;
		case DAY:
			calender.add(Calendar.DAY_OF_YEAR, difference);
			break;
		case MONTH:
			calender.add(Calendar.MONTH, difference);
			break;
		case YEAR:
			calender.add(Calendar.YEAR, difference);
			break;
		default:
			throw new BusinessException("Please provide valid Time Unit", false);
		}
		return calender.getTime();
	}

	/**
	 * This method will generate random text for defined pattern
	 * Length of return string is also rande bound with min and max value
	 * 
	 * @param pattern
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static String getRandomText(String pattern, int minLength, int maxLength) {
		String regex = pattern + "{" + minLength + "," + maxLength + "}";
		return getRandomText(regex);
	}

	/**
	 * This method will generate random text for defined pattern
	 * 
	 * @param regex
	 * @return
	 */
	public static String getRandomText(String regex) {
		Xeger generator = new Xeger(regex);
		return generator.generate();
	}

	/**
	 * This method will return text appended with current time-stamp
	 * @param text
	 * @return
	 */
	public static String getTextWithTs(String text) {
		Long time = new Date().getTime();
		if (null != text) {
			text = text + time;
		} else {
			text = time.toString();
		}
		return text;
	}

	/**
	 * This method will return random Integer value with in min, max boundary
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static Integer getIntegerwithinRange(int min, int max) {
		Random random = new Random();
		return random.ints(min, max).findAny().getAsInt();
	}
	
	/**
	 * This method will return random String value from List
	 * @param dataList
	 * @return
	 */
	public static String getStringFromList(List<String> dataList) {
		if(null!=dataList && dataList.size()>0) {
			Integer index = getIntegerwithinRange(0, dataList.size()-1);
			return dataList.get(index);
		}else 
			return new String();
	}
	
	/**
	 * This method will return random Integer value from List
	 * 
	 * @param dataList
	 * @return
	 */
	public static Integer getIntegerFromList(List<Integer> dataList) {
		if(null!=dataList && dataList.size()>0) {
			Integer index = getIntegerwithinRange(0, dataList.size()-1);
			return dataList.get(index);
		}else 
			return 0;
	}
	
	/**
	 * This method will return random Object value from List
	 * This is more generic approach.
	 * Return value need to cast in desired Object
	 * 
	 * @param dataList
	 * @return
	 */
	public static Object getDataFromList(List<Object> dataList) {
		if(null!=dataList && dataList.size()>0) {
			Integer index = getIntegerwithinRange(0, dataList.size()-1);
			return dataList.get(index);
		}else 
			return new Object();
	}
	

	/**
	 * This is main method for Testing purpose
	 * @param args
	 * @throws BusinessException
	 */
	public static void main(String[] args) throws BusinessException {

		LOGGER.debug(getCurrentDate());
		LOGGER.debug(getDate(new Date(), 700, TimeUnit.DAY));
		LOGGER.debug(getRandomText( DEFAULT_TEXT_PATTERN,8, 8));
		LOGGER.debug(getTextWithTs(getRandomText( DEFAULT_TEXT_PATTERN, 8, 8) + "_"));
		LOGGER.debug("getIntegerwithinRange :: " + getIntegerwithinRange(100, 220));
		
		List<Object> list = new ArrayList<Object>();
		list.add("INDIA");
		list.add("SPAIN");
		list.add("USA");
		list.add("CHINA");
		list.add("ALBANIA");
		list.add("AFGANISTAN");
		list.add("CANADA");
		
		//LOGGER.debug("getStringFromList :: " + getStringFromList(list));
		LOGGER.debug("getStringFromList :: " + getDataFromList(list));
		LOGGER.debug("getDataFromList :: " + getDataFromList(null));
		
	}
		
}
