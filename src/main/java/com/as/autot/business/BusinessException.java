package com.as.autot.business;

import org.apache.log4j.Logger;

/**
 * This business exception class will be thrown from all checked exceptions in core framework package.
 *  So that only business exception can be easily handled in test package.
 * 
 * @author Anuj Tripathi
 *
 */
public class BusinessException extends Exception{

	protected final Logger LOGGER = Logger.getLogger(this.getClass());
	

	private static final long serialVersionUID = 1L;
	
	private String bMsg;
	private boolean isPass;
	
	public BusinessException(String bMsg, boolean isPass) {
		super();
		this.bMsg = bMsg;
		this.isPass = isPass;
		logException();
		errorAnalyser();
		
	}

	private void errorAnalyser() {
		// TODO : It will perform failure/error analysis
		// And identify root cause of failure/error
		// And build report which will help to add preventive action 
	}

	private void logException() {
		//this.printStackTrace();
		LOGGER.error(this.getMessage());
	}

	public String getbMsg() {
		return bMsg;
	}

	public void setbMsg(String bMsg) {
		this.bMsg = bMsg;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
}
