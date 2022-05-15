package com.hackathon.paymenttracker.namepronunciation.model;

public class EmployeeCrudResponse {
	private String statusCode;
    public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;

}
