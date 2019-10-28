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
@Table(name = "groups")
@JsonIgnoreProperties
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414906438400877089L;
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long creatorId;
	private String name;
	private String title;
	private String description;

	/* 0-Created,1-Approved,2-Rejected */
//	@Column(nullable = false, columnDefinition = "int(1) default 0")
	private int status;
	private String groupDP;

	/*
	 * @Column(nullable = false, updatable = false)
	 * 
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 
	 * @CreatedDate
	 * 
	 * @JsonSerialize(using = CustomJsonDateSerializer.class)
	 * 
	 * @JsonDeserialize(using = CustomDateAndTimeDeserialize.class)
	 */private Date createdDate = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGroupDP() {
		return groupDP;
	}

	public void setGroupDP(String groupDP) {
		this.groupDP = groupDP;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
