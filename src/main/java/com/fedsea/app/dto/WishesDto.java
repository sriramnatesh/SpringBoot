package com.fedsea.app.dto;

import java.util.Date;


public class WishesDto {
	
	private Long id;
	private String wishesfrom;
	
	private String wishesto;
	
	private String wishes;
	
	private String relationto;
	
	private Date wisheddate;
	
	private Date updatedon;

	public String getWishesfrom() {
		return wishesfrom;
	}

	public void setWishesfrom(String wishesfrom) {
		this.wishesfrom = wishesfrom;
	}

	public String getWishesto() {
		return wishesto;
	}

	public void setWishesto(String wishesto) {
		this.wishesto = wishesto;
	}

	public String getRelationto() {
		return relationto;
	}

	public void setRelationto(String relationto) {
		this.relationto = relationto;
	}

	public String getWishes() {
		return wishes;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	public Date getWisheddate() {
		return wisheddate;
	}

	public void setWisheddate(Date wisheddate) {
		this.wisheddate = wisheddate;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
