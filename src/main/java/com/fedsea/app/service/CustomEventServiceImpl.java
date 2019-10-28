package com.fedsea.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.CustomEvent;
import com.fedsea.app.model.Festivals;
import com.fedsea.app.repository.CustomEventRepository;
import com.fedsea.app.repository.FestivalRepository;

@Service
public class CustomEventServiceImpl implements ICustomEventService{

	
	@Autowired
	public CustomEventRepository customEventRepository;
	
	
	@Override
	public void addCustomEvent(CustomEvent customEvent) {
		// TODO Auto-generated method stub
		customEventRepository.save(customEvent);
	}

	@Override
	public List<CustomEvent> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return customEventRepository.findByUserId(userId);
	}

	@Override
	public CustomEvent findByUserIdAndId(Long userId, Long customEventId) {
		// TODO Auto-generated method stub
		return customEventRepository.findByUserIdAndCustomeventId(userId, customEventId);
									
	}

	@Override
	public void deleteCustomEvent(Long customEventId) {
		customEventRepository.deleteById(customEventId);
	}


}
