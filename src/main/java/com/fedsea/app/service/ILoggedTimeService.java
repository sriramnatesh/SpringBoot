package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.LoggedTime;

@Service

public interface ILoggedTimeService {
	
	//void addLoggedTime(String username,Date loggedtime,String uniqid);

	//LoggedTime findByUniqid(String uniqid);
	List<String> findDistinctByUniqid(String uniqid);

	
	
	void save(LoggedTime loggedTime);
}
