package com.mvc.model;



import java.util.Date;


public class CustomErrorDetails {
	private String message;
	private Date timestamp;
	private String errorDetails;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public CustomErrorDetails() {
		// TODO Auto-generated constructor stub
	}

	public CustomErrorDetails(String message, Date timestamp, String errorDetails) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.errorDetails = errorDetails;
	}
	
	
}
