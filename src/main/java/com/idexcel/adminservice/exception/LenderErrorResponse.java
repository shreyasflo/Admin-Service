package com.idexcel.adminservice.exception;

public class LenderErrorResponse {
	
	private int status;
	private String message;
	private long timeStamp;
	public LenderErrorResponse(int status, String message, long timeStamp) {
		
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	
}
