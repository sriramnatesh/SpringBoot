package com.fedsea.app.model;

import java.io.Serializable;
import java.util.ArrayList;
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


public class MyStoryImageFiles implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4749929525572273607L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imageurl;
	

}
