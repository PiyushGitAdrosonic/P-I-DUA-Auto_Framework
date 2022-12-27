package com.as.autot.core.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.log4j.Logger;


public class MarkupHelper {
	
	protected static final Logger LOGGER = Logger.getLogger(MarkupHelper.class);
	
    /**
     * It will generate mark-up text for screenshot attachment for extent report
     * @param sImageFilePath
     * @param sImagealt
     * @param sErrorMessage
     * @return
     */
    public static synchronized String createScreenshotURL(String sImageFilePath, String sImagealt, String sErrorMessage) {
    	String aTagStart ="<a href=\""+sImageFilePath+ "\">";
    	String sStyleToCreateThumbNail=" style=\"object-fit: contain;\" ";
    	String imgTagStart = "<img src= \""+sImageFilePath+"\" "+sStyleToCreateThumbNail+"alt=\""+sImagealt+"\" width=\"50\" height=\"50\">"+sErrorMessage+"</a>";
    	//<a href="https:b.com/"><img src= "\img_snow.jpg" alt="GeeksforGeeks logo" width="50" height="50">  </a>
    	LOGGER.debug("===["+aTagStart+imgTagStart+"===]");
    	return aTagStart+imgTagStart;
    }
    
    
    /**
     * It will generate mark-up text for embedded base64 screenshot for extent report
     * @param base64Image
     * @param sErrorMessage
     * @return
     * @throws IOException
     */
    public static synchronized String createBase64ScreenshotURL(String base64Image, String sErrorMessage) throws IOException {
    	String aTagStart ="<a href=\"data:image/png;base64,"+base64Image+ "\" data-featherlight=\"image\">";
    	String sStyleToCreateThumbNail=" style=\"width: 60px; height: 60px; object-fit: contain;\" ";
    	String imgTagStart = "<img src= \"data:image/png;base64,"+ConvertFileToBase64(Constants.BASEPATH+"\\images\\screenshotIcon.png")
    	+"\" "+sStyleToCreateThumbNail+"alt=\""+sErrorMessage+"\" width=\"50\" height=\"50\">"+sErrorMessage+"</a>";    	
    	return aTagStart+imgTagStart;
    }
    
    /**
     * It convert file to its base 64 of content text
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String ConvertFileToBase64(String filePath) throws IOException {
    	byte[] byteData = Files.readAllBytes(Paths.get(filePath));
        return Base64.getEncoder().encodeToString(byteData);
    }
    
    public static void main(String[] args) throws IOException {
    	ConvertFileToBase64(Constants.BASEPATH+"\\images\\screenshotIcon.png");
	}
}
