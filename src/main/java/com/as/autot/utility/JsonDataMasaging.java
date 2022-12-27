package com.as.autot.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.as.autot.business.BusinessException;
import com.as.autot.core.common.Constants;
import com.as.autot.utility.DataHandler.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import net.minidev.json.JSONArray;

/**
 * This Class is defined for massaging json object
 * 
 * @author Anuj Tripathi
 *
 */
public class JsonDataMasaging {
	
	private static final Logger LOGGER =  Logger.getLogger(JsonDataMasaging.class);

	public enum BusinessRule {
		getCurrentDate, getStringFromDate, getDate, getRandomText, getTextWithTs, getStringFromList,
		getIntegerwithinRange
	}

	/**
	 * This method will evaluate all business rules in json file and return updated
	 * json content
	 * 
	 * @param jsonFilePath
	 * @return
	 * @throws IOException
	 */
	public static String getJsonMassaged(String jsonFilePath) throws IOException {
		byte[] jsonData = Files.readAllBytes(Paths.get(Constants.TEST_DATA_REPOSITORY + jsonFilePath));
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> treeMap = objectMapper.readValue(jsonData, Map.class);
		HashMap<String, String> keys = new HashMap<String, String>();
		String jsonPath = "$";
		getNodeElementMap(treeMap, keys, jsonPath);
		return getNodeElementUpdatedWithBR(keys, jsonFilePath);
	}

	/**
	 * This Method will update all business rules defined in keys map and return
	 * updated json content
	 * 
	 * @param keys
	 * @param jsonFilePath
	 * @return
	 * @throws IOException
	 */
	private static String getNodeElementUpdatedWithBR(HashMap<String, String> keys, String jsonFilePath)
			throws IOException {
		byte[] jsonData = Files.readAllBytes(Paths.get(Constants.TEST_DATA_REPOSITORY + jsonFilePath));
		String jsonString = new String(jsonData, Charset.defaultCharset());
		DocumentContext doc = JsonPath.parse(jsonString);
		keys.forEach((key, value) -> {
			String dynamicValue = evaluvateBrData(value);
			doc.set(key, dynamicValue);
		});
		return doc.jsonString();
	}

	/**
	 * This method will evaluate data on the basis of business rule as defined and
	 * return desired value
	 * 
	 * @param data
	 * @return
	 */
	private static String evaluvateBrData(String data) {
		List<String> dataArray = Arrays.asList(data.split("__"));
		String returnValue = data;
		if (null != dataArray && dataArray.size() >= 2) {
			String businessRule = dataArray.get(1);
			try {
				BusinessRule.valueOf(businessRule);
				switch (BusinessRule.valueOf(businessRule)) {
				case getCurrentDate:
					returnValue = DataHandler.getCurrentDate();
					break;
				case getDate:
					returnValue = DataHandler.getDate(dataArray.get(2), Integer.valueOf(dataArray.get(3)),
							TimeUnit.valueOf(dataArray.get(4))).toString();
					break;
				case getRandomText:
					returnValue = DataHandler.getRandomText(dataArray.get(2));
					break;
				case getTextWithTs:
					returnValue = DataHandler.getTextWithTs(dataArray.get(2));
					break;
				case getStringFromList:
					List<String> listValue = new ArrayList<>();
					for (int i = 2; i < dataArray.size(); i++) {
						listValue.add(dataArray.get(i));
					}
					returnValue = DataHandler.getStringFromList(dataArray);
					break;
				case getIntegerwithinRange:
					returnValue = DataHandler
							.getIntegerwithinRange(Integer.valueOf(dataArray.get(2)), Integer.valueOf(dataArray.get(3)))
							.toString();
					break;
				default:
					returnValue = data;
				}
			} catch (Exception e) {
				LOGGER.error("Inside evaluvateBrData " + businessRule + " <> " + e.getMessage());
			}
		}
		return returnValue;
	}

	/**
	 * This method will extract all business-rules (BR__) defined in provided
	 * treeMap from a json
	 * 
	 * @param treeMap
	 * @param keys
	 * @param jsonPath
	 */
	private static void getNodeElementMap(Map<String, Object> treeMap, HashMap<String, String> keys,
			final String jsonPath) {
		treeMap.forEach((key, value) -> {
			if (value instanceof LinkedHashMap) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (LinkedHashMap<String, Object>) value;

				getNodeElementMap(map, keys, jsonPath + "." + key.toString());
			} else if (value instanceof ArrayList) {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				ArrayList<LinkedHashMap> arrayList = (ArrayList<LinkedHashMap>) value;
				for (int i = 0; i < arrayList.size(); i++) {
					Object obj = arrayList.get(i);
					if (obj instanceof LinkedHashMap) {
						@SuppressWarnings("unchecked")
						Map<String, Object> map = arrayList.get(i);
						getNodeElementMap(map, keys, jsonPath + "." + key.toString() + "[" + i + "]");
					} else if (obj instanceof String || obj instanceof Boolean) {
						String jPath = jsonPath + "." + key.toString() + "[" + i + "]";
						if (null != value && value.toString().startsWith("BR__")) {
							keys.put(jPath, obj.toString());
						}
					} else {
						LOGGER.debug(key + " : " + value);
					}
				}
			} else if (value instanceof String || value instanceof Boolean) {
				String jPath = jsonPath + "." + key.toString();
				keys.put(jPath, value.toString());
			} else {
				LOGGER.debug(key + " : " + value);
			}
		});
	}
	
	public static Object extractJsonPathValue(String jsonString, String jsonPath) {
		Object data = JsonPath.read(jsonString, jsonPath);
		 if(data instanceof String) {
			 String val = (String) data;
			 return val.toString();
		}else if(data instanceof Integer) {
			Integer val = (Integer) data;
			 return val.toString();
		}else if(data instanceof Boolean) {
			Boolean val = (Boolean) data;
			 return val.toString();
		}else if(data instanceof JSONArray) {
			JSONArray val = (JSONArray) data;
			 return val.toJSONString();
		}else if(null!=data) {
			 return data.toString();
		}else {
			return data;
		}
	}	
	
	
	public static boolean isNodePresent(String jsonString, String jsonPath) {
		try {
			JsonPath.read(jsonString, jsonPath);
			return true;
		}catch(PathNotFoundException e) {
			return false;
		}
	}
	
	public static Integer getSizeOfArray(String jsonString, String jsonPath) throws BusinessException {
		Object data = JsonPath.read(jsonString, jsonPath);
		if(data instanceof JSONArray) {
			JSONArray array = (JSONArray) data;
			 return array.size();
		}else  {
			 throw new BusinessException("Object @["+jsonPath+"] is not an Array", false);
		}
	}

	public static void main(String[] args) throws IOException {
		getJsonMassaged("quote.json");
	}
}