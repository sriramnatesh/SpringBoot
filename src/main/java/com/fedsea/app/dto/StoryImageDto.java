package com.fedsea.app.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Convert;

import com.fedsea.app.classconvert.StringListConverter;


public class StoryImageDto {
	
	private Long userId;
	
	private String comments;

	@Convert(converter = StringListConverter.class)
	private List<String> imageUrl;
	
	@Convert(converter = StringListConverter.class)
	private String[] videoUrl;
	
	private String Tags;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String[] getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String[] videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}



}
