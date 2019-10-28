package com.fedsea.app.dto;

import java.util.List;

public class StoryDto {

	private Long id;
	private Long user_Id;
	private String comments;
	/*
	 * private List<String> image_Url; private List<String> video_Url;
	 */
	
	
	private String story_Type;
	private String feed_Type;
	private String Tags;
	private String story_date;
	
	
	public StoryDto(Long id, Long user_Id, String comments,// List<String> image_Url, List<String> video_Url,
			String story_Type, String feed_Type, String tags, String story_date) {
		super();
		this.id = id;
		this.user_Id = user_Id;
		this.comments = comments;
		/*
		 * this.image_Url = image_Url; this.video_Url = video_Url;
		 */
		this.story_Type = story_Type;
		this.feed_Type = feed_Type;
		Tags = tags;
		this.story_date = story_date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(Long user_Id) {
		this.user_Id = user_Id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	/*
	 * public List<String> getImage_Url() { return image_Url; } public void
	 * setImage_Url(List<String> image_Url) { this.image_Url = image_Url; } public
	 * List<String> getVideo_Url() { return video_Url; } public void
	 * setVideo_Url(List<String> video_Url) { this.video_Url = video_Url; }
	 */
	public String getStory_Type() {
		return story_Type;
	}
	public void setStory_Type(String story_Type) {
		this.story_Type = story_Type;
	}
	public String getFeed_Type() {
		return feed_Type;
	}
	public void setFeed_Type(String feed_Type) {
		this.feed_Type = feed_Type;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public String getStory_date() {
		return story_date;
	}
	public void setStory_date(String story_date) {
		this.story_date = story_date;
	}
	
	
	
	
}
