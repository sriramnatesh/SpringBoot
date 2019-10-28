
package com.fedsea.app.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.fedsea.app.dto.WishesDto;
import com.fedsea.app.model.Wishes;

@Service
public interface IWishesService {

	List<Wishes> findByWishesby(String wishesby);
	
	List<Wishes> findByUsername(String username);
	
	List<Wishes> findByUsernameAndRelationtoAndWishesby(String username ,String relationto,String wishesby );

	//List<Wishes> findByUsernameAndRelationtoAndWishesby(String username,String wishesby,String relationto );
	
	public void addWishes(WishesDto wishes);
	
	long deleteByIdAndUsernameAndRelationtoAndWishesby(Long id,String username,String relationto,String wishesby);
	
}
