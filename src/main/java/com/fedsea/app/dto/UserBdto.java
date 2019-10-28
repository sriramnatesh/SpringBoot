package com.fedsea.app.dto;

import java.io.Serializable;
import java.util.Map;

public class UserBdto  implements Serializable {
	
	
 	
	public UserBdto(Long id, String fullName, String username, String profileImageFile) {
		 
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.profileImageFile = profileImageFile;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -370498187945412134L;
	private Long id;
	private String fullName;
	private String username;
	private String profileImageFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileImageFile() {
		return profileImageFile;
	}

	public void setProfileImageFile(String profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
    

}
