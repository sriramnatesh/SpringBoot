package com.fedsea.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Festivals;
import com.fedsea.app.repository.FestivalRepository;

@Service
public class FestivalServiceImpl implements IFestivalService{

	
	@Autowired
	public FestivalRepository festivalRepository;
	
	@Override
	public void addFestival(Festivals festival) {
		// TODO Auto-generated method stub
		festivalRepository.save(festival);
	}

	@Override
	public List<Festivals> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return festivalRepository.findByUserId(userId);
	}

	@Override
	public Festivals findBYUserIdAndId(Long userId, Long id) {
		// TODO Auto-generated method stub
		return festivalRepository.findByUserIdAndFestivalId(userId, id);
	}

	@Override
	public void deleteFestival(Long festivalId) {
		festivalRepository.deleteById(festivalId);
	}
	
	
}
