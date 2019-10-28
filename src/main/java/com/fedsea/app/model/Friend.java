package com.fedsea.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fedsea.app.config.CustomDateAndTimeDeserialize;
import com.fedsea.app.config.CustomJsonDateSerializer;

@Entity
@Table(name = "friends")
@JsonIgnoreProperties
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414906438400877089L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long senderId;
	
	/* private Long receiverId; */

//	0-Pending,1-Accepted,2-Rejected,3-Not Responded
	private Integer status = 0;

	private Long requestId;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date requestedDate = new Date();

	@Column(nullable = true, updatable = true)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date acceptedDate;

	@Column(nullable = true, updatable = true)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date rejectedDate;

	@Column(nullable = true, updatable = true)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date friendDate;

	@Column(nullable = true, updatable = true)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date unfriendDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	/*
	 * public Long getReceiverId() { return receiverId; }
	 * 
	 * public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
	 */

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public Date getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	public Date getFriendDate() {
		return friendDate;
	}

	public void setFriendDate(Date friendDate) {
		this.friendDate = friendDate;
	}

	public Date getUnfriendDate() {
		return unfriendDate;
	}

	public void setUnfriendDate(Date unfriendDate) {
		this.unfriendDate = unfriendDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
