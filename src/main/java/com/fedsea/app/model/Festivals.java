package com.fedsea.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fedsea.app.config.CustomDateAndTimeDeserialize;
import com.fedsea.app.config.CustomJsonDateSerializer;

@Entity
@Table(name = "festivals")
@JsonIgnoreProperties
public class Festivals implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long festivalId;
	private String festivalName;
	/*
	 * @JsonSerialize(using = CustomJsonDateSerializer.class)
	 * 
	 * @JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	 */	private Date festivalStartDate;
	/*
	 * @JsonSerialize(using = CustomJsonDateSerializer.class)
	 * 
	 * @JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	 */	
	 private Date festivalEndDate;
	private Integer noOfParticipants;
	private String festivalCountry;
	private String festivalState;
	private String festivalplace;
	private String comments;
	private String eventFrequency;
	private Long userId;
	
	public Long getFestivalId() {
		return festivalId;
	}
	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	public Date getFestivalStartDate() {
		return festivalStartDate;
	}
	public void setFestivalStartDate(Date festivalStartDate) {
		this.festivalStartDate = festivalStartDate;
	}
	public Date getFestivalEndDate() {
		return festivalEndDate;
	}
	public void setFestivalEndDate(Date festivalEndDate) {
		this.festivalEndDate = festivalEndDate;
	}
	public Integer getNoOfParticipants() {
		return noOfParticipants;
	}
	public void setNoOfParticipants(Integer noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
	}
	public String getFestivalCountry() {
		return festivalCountry;
	}
	public void setFestivalCountry(String festivalCountry) {
		this.festivalCountry = festivalCountry;
	}
	public String getFestivalState() {
		return festivalState;
	}
	public void setFestivalState(String festivalState) {
		this.festivalState = festivalState;
	}
	public String getFestivalplace() {
		return festivalplace;
	}
	public void setFestivalplace(String festivalplace) {
		this.festivalplace = festivalplace;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getEventFrequency() {
		return eventFrequency;
	}
	public void setEventFrequency(String eventFrequency) {
		this.eventFrequency = eventFrequency;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
