package com.fedsea.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "wishes")
@JsonIgnoreProperties
public class Wishes implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4749929525572273607L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String wishes;
	
	private String wishesby;
	
	private String relationto;
	
	private String username;
	
	private Date wisheddate;
	
	private boolean isRead;
	
	private Date updatedon;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public String getWishes() {
		return wishes;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	public String getWishesby() {
		return wishesby;
	}

	public void setWishesby(String wishesby) {
		this.wishesby = wishesby;
	}

	public String getRelationto() {
		return relationto;
	}

	public void setRelationto(String relationto) {
		this.relationto = relationto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getWisheddate() {
		return wisheddate;
	}

	public void setWisheddate(Date wisheddate) {
		this.wisheddate = wisheddate;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}



}
