package com.as.autot.core.or;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
/**@author Sambodhan D. (Designer and Developer) June 2022*/
public class OrStruct {
	
	private static final Logger LOGGER =  Logger.getLogger(OrStruct.class);
	
	String name, locType, locValue, text, elementType;
	public static final String PROP_SEPARATOR=":";
	ArrayList<String> props = null;
	int waitInSec=0;
	final String LOCATOR_REPLACE="<REPL>";

	public String getText() {
		return text;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public static String getPropSeparator() {
		return PROP_SEPARATOR;
	}
	public void setText(String Text) {
		this.text = Text;
	}
	public OrStruct(String Name, String locType, String locValue, int waitInSec) {
		super();
		this.name = Name;
		this.locType = locType;
		this.locValue = locValue;
		this.waitInSec = waitInSec;
	}
	public OrStruct(String Name, String locType, String locValue, String Text, int waitInSec) {
	super();
	this.name = Name;
	this.locType = locType;
	this.locValue = locValue;
	this.text = Text;
	this.waitInSec = waitInSec;
}
	
	public OrStruct(String Name, String locType, String locValue, String Text, int waitInSec, String elementType) {
	super();
	this.name = Name;
	this.locType = locType;
	this.locValue = locValue;
	this.text = Text;
	this.elementType = elementType;
	this.waitInSec = waitInSec;
}
	//ew OrStruct("Vaishali", "Intelligence", "Excellent", 3, "someType",props);
	public OrStruct(String Name, String locType, String locValue, String Text, int waitInSec, String elementType, ArrayList<String> propsNew) {
	super();
	this.name = Name;
	this.locType = locType;
	this.locValue = locValue;
	this.text = Text;
	this.elementType = elementType;
	this.waitInSec = waitInSec;
	this.props=propsNew;
}



	@Override
	public String toString() {
		return "OrStruct [Name=" + name + ", locType=" + locType + ", locValue=" + locValue + ", Text=" + text
				+ ", elementType=" + elementType + ", props=" + props + ", waitInSec=" + waitInSec + "]";
	}
	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public String getLocValue() {
		return locValue;
	}

	public void setLocValue(String locValue) {
		this.locValue = locValue;
	}

	public int getwaitInSec() {
		return waitInSec;
	}

	public void setwaitInSec(int waitInSec) {
		this.waitInSec = waitInSec;
	}

	public void replaceDynamicValueInXpath(String sValue) {
		locValue=locValue.replace(LOCATOR_REPLACE, sValue);
		LOGGER.debug("Test here!");
	}
	
	public OrStruct replaceDynamicValue(String sValue) {
		String localValue = locValue;
		String value=localValue.replace(LOCATOR_REPLACE, sValue);
		OrStruct updatedOr = this;
		updatedOr.setLocValue(value);
		return updatedOr;
	}
	
	public ArrayList<String> getProps() {
		return props;
	}
	public void addCustomProps(String sPropName, String sPropValue) {
		this.props.add(prepareProp(sPropName, sPropValue));
		}
	
	private String prepareProp(String sPropName, String sPropValue) {
		return sPropName+PROP_SEPARATOR+ sPropValue;
	}
	
	public void setProps(ArrayList<String> props) {
		this.props = props;
	}
	
	public String getProp(String propName) {
		for (Iterator<String> iterator = props.iterator(); iterator.hasNext();) {
			String propDetail = (String) iterator.next();
			if(propDetail.contains(propName+PROP_SEPARATOR)) {
				return propDetail.substring(propDetail.indexOf(PROP_SEPARATOR)+1);
			}
		}
		return null;
	}


	public By getBy() {
		String sBy = this.getLocType().toUpperCase();
		By toReturn=null;
		switch(sBy) {
		case "ID" : toReturn = By.id(this.getLocValue());break;
		case "XPATH" : toReturn = By.xpath(this.getLocValue());break;
		case "CSS" : toReturn = By.cssSelector(this.getLocValue());break;
		case "TAGNAME" : toReturn = By.tagName(this.getLocValue());break;
		case "LINKTEXT" : toReturn = By.linkText(this.getLocValue());break;
		case "PARTIALLINK" : toReturn = By.partialLinkText(this.getLocValue());break;
		case "NAME" : toReturn = By.name(this.getLocValue());break;
		case "CLASSNAME" : toReturn = By.className(this.getLocValue());break;
		case "LINK" : toReturn = By.linkText(this.getLocValue());break;
		default: LOGGER.debug("The element="+this.getName()+" has incorrect type="+this.getLocType());
		}
		return toReturn;
		
	}

}
