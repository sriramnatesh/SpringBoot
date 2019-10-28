package com.fedsea.app.dto;

public class RecentlogindetsDto {
	
	private Long id;
	private String fullName;
	private String username;
	private String profileimageurl ;
	
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

	/*
	 * public MultipartFile getProfileImageFile() { return profileImageFile; }
	 * public void setProfileImageFile(MultipartFile profileImageFile) {
	 * this.profileImageFile = profileImageFile; }
	 */

	public String getProfileimageurl() {
		return profileimageurl;
	}
	public void setProfile_image_url(String profile_image_url) {
		this.profileimageurl = profile_image_url;
	}

	public RecentlogindetsDto(String fullName, String username, String profile_image_url) {
		this.fullName = fullName;
		this.username = username;
		this.profileimageurl = profile_image_url;
	}

}
