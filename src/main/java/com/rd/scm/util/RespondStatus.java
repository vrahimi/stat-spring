package com.rd.scm.util;

//This class is used to return result in json format
public class RespondStatus {

	public static final int STATUS_OK = 0;
	public static final int STATUS_ERROR = 1;
	public static final int STATUS_WARN = 2;

	private int statusCode = STATUS_OK;
	private String errorText = "";
	
	public void setRespondStatus(int statusCode, String errorText) {
		this.statusCode = statusCode;
		this.errorText = errorText;
	}
	
	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
