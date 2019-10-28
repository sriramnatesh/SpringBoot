package com.fedsea.app.dto;

public class UserNotification {
	private String message;
	private String subject;
	private String mode;
	private String userid;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String notification) {
		this.mode = notification;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
