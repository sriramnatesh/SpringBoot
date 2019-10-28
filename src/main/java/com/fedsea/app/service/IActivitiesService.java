
package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Activities;

@Service
public interface IActivitiesService {

	void addActivities(Activities activities);

	void removeActivities(Long id);
	
	Activities findByUserIdAndId(Long userId,Long id);
	
	List<Activities> findByUserId(Long userId);

}
