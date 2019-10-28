package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fedsea.app.model.Activities;
import com.fedsea.app.model.Chat;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

	Activities findByLatAndLon(String lat,String lon);

	Activities findByUserIdAndId(Long userId,Long id);
	
	List<Activities> findByUserId(Long userId);
	
	List<Activities> findByDescription(String desc);
	
	List<Activities> findByDescriptionAndUserId(String desc,Long userId);

}
