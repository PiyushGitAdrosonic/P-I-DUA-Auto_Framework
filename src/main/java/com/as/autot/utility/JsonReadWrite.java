package com.as.autot.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import com.as.autot.core.or.OrList;
import com.google.gson.Gson;

/**
 * @Designed and Developed By: Sambodhan D.
 */

public class JsonReadWrite {
	
	private static final Logger LOGGER =  Logger.getLogger(JsonReadWrite.class);
	
	public OrList readGivenJsonToOrList(String sCompletePathFile) {
		OrList olist = null;
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(sCompletePathFile));
			olist = gson.fromJson(reader, OrList.class);
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return olist;
	}
	
	
	public void writeToGivenJson(String sInput, String sCompletePathFile) {
		try (FileWriter file = new FileWriter(sCompletePathFile)) {
			file.write(sInput);
			file.flush();
			LOGGER.debug("Try is completed! [" + sCompletePathFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 File file = new File(sCompletePathFile);
	     LOGGER.debug(file.exists()+" : at["+sCompletePathFile);
	}



}
