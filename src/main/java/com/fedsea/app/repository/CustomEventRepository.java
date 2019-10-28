package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.CustomEvent;
import com.fedsea.app.model.Festivals;

@Repository
public interface CustomEventRepository extends JpaRepository<CustomEvent, Long> {
	
	
	List<CustomEvent> findByUserId(Long userId);
	
	CustomEvent findByUserIdAndCustomeventId(Long userId,Long customEventId);

}
