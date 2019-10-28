package com.fedsea.app.dto;

public class LoginUser {

	private String username;
	private String password;
	private String loginType;
	private String uniqid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginType() { return loginType; }
	  
    public void setLoginType(String loginType) { this.loginType = loginType; }
	 
	public String getUniqid() {
		return uniqid;
	}

	public void setUniqid(String uniqid) {
		this.uniqid = uniqid;
	}

}
