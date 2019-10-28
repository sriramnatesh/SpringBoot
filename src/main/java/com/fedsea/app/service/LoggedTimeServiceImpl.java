package com.fedsea.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.LoggedTime;
import com.fedsea.app.repository.LoggedTimeRepository;

@Service
public class LoggedTimeServiceImpl implements ILoggedTimeService {

	
	@Autowired
	private LoggedTimeRepository loggedTimeRepository;

	/*
	 * @Override public LoggedTime findByUniqid(String uniqid) {
	 * 
	 * return loggedTimeRepository.findByUniqid(uniqid); }
	 */

	public List<String> findDistinctByUniqid(String uniqid) {
		// TODO Auto-generated method stub
		return loggedTimeRepository.getAllUniqid(uniqid);
	}

	@Override
	public void save(LoggedTime loggedTime) {
		// TODO Auto-generated method stub
		 loggedTimeRepository.save(loggedTime);
	}


	


}
