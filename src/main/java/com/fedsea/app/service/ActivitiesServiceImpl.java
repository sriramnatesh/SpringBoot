
package com.fedsea.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Activities;
import com.fedsea.app.model.Chat;
import com.fedsea.app.repository.ActivitiesRepository;
import com.fedsea.app.repository.ChatRepository;

@Service
public class ActivitiesServiceImpl implements IActivitiesService {


	@Autowired
	private ActivitiesRepository activityRepository;

	@Override
	public void addActivities(Activities activities) {
	
		activityRepository.save(activities);
	}

	@Override
	public void removeActivities(Long id) {
		// TODO Auto-generated method stub
		activityRepository.deleteById(id);
	}

	@Override
	public Activities findByUserIdAndId(Long userId, Long id) {
		// TODO Auto-generated method stub
		return activityRepository.findByUserIdAndId(userId, id);
	}

	@Override
	public List<Activities> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return activityRepository.findByUserId(userId);
	}

	
	

}
