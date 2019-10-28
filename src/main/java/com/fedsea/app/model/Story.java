package com.fedsea.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fedsea.app.classconvert.StringListConverter;
import com.fedsea.app.config.CustomDateAndTimeDeserialize;
import com.fedsea.app.config.CustomJsonDateSerializer;

@Entity
@Table(name = "stories")
@JsonIgnoreProperties
public class Story implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7029955735370958314L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	private Long userId;
	
	private String comment;
	
	private String storyType;
	
	@Convert(converter = StringListConverter.class)
	private List<String> imageUrl;
	
	@Convert(converter = StringListConverter.class)
	private String[] videoUrl;
	
	private String tags;
	
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date storyDate = new Date();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStoryType() {
		return storyType;
	}

	public void setStoryType(String storyType) {
		this.storyType = storyType;
	}

	public List<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getStoryDate() {
		return storyDate;
	}

	public void setStoryDate(Date storyDate) {
		this.storyDate = storyDate;
	}

}
