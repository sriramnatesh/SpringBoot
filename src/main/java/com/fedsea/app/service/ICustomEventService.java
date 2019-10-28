package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.CustomEvent;

@Service
public interface ICustomEventService {
	
	void addCustomEvent(CustomEvent customevent);

	List<CustomEvent> findByUserId(Long userId);
	
	CustomEvent findByUserIdAndId(Long userId,Long id);
	
	
	public void deleteCustomEvent(Long customEventId);
}
