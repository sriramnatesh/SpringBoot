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
@Table(name = "loggedtime")
@JsonIgnoreProperties
public class LoggedTime implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private Date loggedtime;
	private String uniqid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getLoggedtime() {
		return loggedtime;
	}
	public void setLoggedtime(Date loggedtime) {
		this.loggedtime = loggedtime;
	}
	public String getUniqid() {
		return uniqid;
	}
	public void setUniqid(String uniqid) {
		this.uniqid = uniqid;
	}
	
	

}
