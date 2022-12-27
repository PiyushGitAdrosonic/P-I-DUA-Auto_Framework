package com.as.autot.core.or;

import java.util.ArrayList;
/**@author Sambodhan D. (Designer and Developer) June 2022*/

import com.google.gson.Gson;

public class OrList {
	private ArrayList<OrStruct> orList = new  ArrayList<OrStruct> ();
	
	
	public ArrayList<OrStruct> getOrList() {
		return orList;
	}

	public OrStruct getItem(int iIndex) {
		return orList.get(iIndex);
	}

	public void setOrList(ArrayList<OrStruct> orList) {
		this.orList = orList;
	}
	
	public void addElement(OrStruct element) {
		orList.add(element);
	}

	public String toJson() {
		return new Gson().toJson(orList);
	}
}
