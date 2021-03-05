package com.stockapp.stockapp.util;


public class ErrorResponse {

	private String errormsg;
	
	private String statusText;

	public ErrorResponse(String errormsg, String statusText) {
		super();
		this.errormsg = errormsg;
		this.statusText = statusText;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	
}
