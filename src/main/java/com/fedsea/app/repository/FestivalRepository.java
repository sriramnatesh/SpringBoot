package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Festivals;

@Repository
public interface FestivalRepository extends JpaRepository<Festivals, Long> {
	
	
	List<Festivals> findByUserId(Long userId);
	
	Festivals findByUserIdAndFestivalId(Long userId,Long festivalId);

}
