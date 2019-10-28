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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fedsea.app.config.CustomDateAndTimeDeserialize;
import com.fedsea.app.config.CustomJsonDateSerializer;

@Entity
@Table(name = "chats")
@JsonIgnoreProperties
public class Chat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414906438400877089L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long chatId;
	@Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
	private boolean isRead;
	private String message;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	private Date messageDate = new Date();
	private Long senderId;
	private Long receiverId;
	@Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
	private boolean isBurned;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public boolean isBurned() {
		return isBurned;
	}

	public void setBurned(boolean isBurned) {
		this.isBurned = isBurned;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
