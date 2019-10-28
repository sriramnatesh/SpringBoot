package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Festivals;

@Service
public interface IFestivalService {
	
	void addFestival(Festivals festival);

	List<Festivals> findByUserId(Long userId);
	
	Festivals findBYUserIdAndId(Long userId,Long id);
	
	
	public void deleteFestival(Long festivalId);
}
