package com.as.autot.core.common;
/**@author Sambodhan D. (Designer and Developer) June 2022*/

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class RunConfig {
	public static final String ENV = "ENV";
	private static final String LOGIN_EMAIL = "LOGIN_EMAIL";
	private static final String LOGIN_PWD = "LOGIN_PWD", USERNAME_DISPLAY="USERNAME_DISPLAY";
	
	private static Properties properties = new Properties();
	private static String FilePath= Constants.RUNCONFIGPROP;
	private static boolean bPropertiesLoaded=false;

	private static final Logger LOGGER =  Logger.getLogger(RunConfig.class);
	
	synchronized static void loadProperties() {
		if(!bPropertiesLoaded) {
			try {
				bPropertiesLoaded=true;				
				String absolutePath = FilePath;
			    FileReader reader=new FileReader(absolutePath);			      
			    properties=new Properties();  
			    properties.load(reader); 
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	static {
		loadProperties();
	}
	public static String getProperty(String psPropertyName) {
		return properties.getProperty(psPropertyName);
	}
	
	public static boolean isEnabled(String psPropertyName) {
		String sAns=properties.getProperty(psPropertyName);
		
		return (null!=sAns&&sAns.contains(Constants.TRUE));
	}
	public static String getEnv() {
		return properties.getProperty(ENV);
	}
	public static String getLoginEmail() {
		return properties.getProperty(LOGIN_EMAIL);
	}
	public static String getLoginPwd() {
		return properties.getProperty(LOGIN_PWD);
	}

	public static String getUsernameDisplay() {
		return USERNAME_DISPLAY;
	}
	
	public static String getORDefault() {
		return getProperty("OR_DEFAULT");
	}
}
