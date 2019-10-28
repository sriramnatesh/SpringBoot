package com.fedsea.app.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
	private String type;
	private String mobileNumber;
	private String email;
	private String fullName;
	private String username;
	private String password;
	private String dob;
	private MultipartFile profileImageFile;
	private MultipartFile coverImageFile;
	private String tags;
	private String location;
	private String bio;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public MultipartFile getProfileImageFile() {
		return profileImageFile;
	}

	public void setProfileImageFile(MultipartFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}

	public MultipartFile getCoverImageFile() {
		return coverImageFile;
	}

	public void setCoverImageFile(MultipartFile coverImageFile) {
		this.coverImageFile = coverImageFile;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
