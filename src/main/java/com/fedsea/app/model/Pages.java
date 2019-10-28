package com.fedsea.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "page")
@JsonIgnoreProperties
public class Pages implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long userId;
	private int pageType;
	private String pageName;
	private String pageCategory;
	private String address;
	private int addressVisibility;
	
	private String content;
	private String shareWith;

		
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
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageCategory() {
		return pageCategory;
	}
	public void setPageCategory(String pageCategory) {
		this.pageCategory = pageCategory;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAddressVisibility() {
		return addressVisibility;
	}
	public void setAddressVisibility(int addressVisibility) {
		this.addressVisibility = addressVisibility;
	}
	public String getContent() {
		return content;
	}
	public String getShareWith() {
		return shareWith;
	}
	public void setShareWith(String shareWith) {
		this.shareWith = shareWith;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
