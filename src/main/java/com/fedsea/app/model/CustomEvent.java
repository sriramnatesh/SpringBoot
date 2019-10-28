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
@Table(name = "customevent")
@JsonIgnoreProperties
public class CustomEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customeventId;
	private String customeventName;
	private Date customeventStartDate;
    private Date customeventEndDate;
	private Integer noOfParticipants;
	private String customeventCountry;
	private String customeventState;
	private String customeventplace;
	private String comments;
	private String eventFrequency;
	private Long userId;
	
	public Long getCustomeventId() {
		return customeventId;
	}
	public void setCustomeventId(Long customeventId) {
		this.customeventId = customeventId;
	}
	public String getCustomeventName() {
		return customeventName;
	}
	public void setCustomeventName(String customeventName) {
		this.customeventName = customeventName;
	}
	public Date getCustomeventStartDate() {
		return customeventStartDate;
	}
	public void setCustomeventStartDate(Date customeventStartDate) {
		this.customeventStartDate = customeventStartDate;
	}
	public Date getCustomeventEndDate() {
		return customeventEndDate;
	}
	public void setCustomeventEndDate(Date customeventEndDate) {
		this.customeventEndDate = customeventEndDate;
	}
	public Integer getNoOfParticipants() {
		return noOfParticipants;
	}
	public void setNoOfParticipants(Integer noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
	}
	public String getCustomeventCountry() {
		return customeventCountry;
	}
	public void setCustomeventCountry(String customeventCountry) {
		this.customeventCountry = customeventCountry;
	}
	public String getCustomeventState() {
		return customeventState;
	}
	public void setCustomeventState(String customeventState) {
		this.customeventState = customeventState;
	}
	public String getCustomeventplace() {
		return customeventplace;
	}
	public void setCustomeventplace(String customeventplace) {
		this.customeventplace = customeventplace;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	
}
