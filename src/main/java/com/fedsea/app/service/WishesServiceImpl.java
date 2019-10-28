
package com.fedsea.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.dto.WishesDto;
import com.fedsea.app.model.Wishes;
import com.fedsea.app.repository.WishesRepository;

@Service
public class WishesServiceImpl implements IWishesService {

	@Autowired
	private WishesRepository wishesRepository;

	@Override
	public List<Wishes> findByWishesby(String username) {
		// TODO Auto-generated method stub
		return wishesRepository.findByWishesby(username);
	}

	@Override
	public List<Wishes> findByUsername(String username) {
		// TODO Auto-generated method stub
		return wishesRepository.findByUsername(username);
	}

	@Override
	public void addWishes(WishesDto newwishes) {
		
		Wishes wishes=new Wishes();
		wishes.setWishes(newwishes.getWishes());
		wishes.setWishesby(newwishes.getWishesfrom());
		wishes.setRelationto(newwishes.getRelationto());
		wishes.setUsername(newwishes.getWishesto());
		wishes.setWisheddate(newwishes.getWisheddate());
		wishes.setUpdatedon(newwishes.getUpdatedon());
		wishes.setRead(false);
		wishesRepository.save(wishes);
		
	}

	@Override
	public List<Wishes> findByUsernameAndRelationtoAndWishesby(String username, String relationto, String wishesby) {
		// TODO Auto-generated method stub
		return wishesRepository.findByUsernameAndRelationtoAndWishesby(username, relationto, wishesby);
	}

	@Override
	public long deleteByIdAndUsernameAndRelationtoAndWishesby(Long id,String username, String relationto, String wishesby) {
		return wishesRepository.deleteByIdAndUsernameAndRelationtoAndWishesby(id,username, relationto, wishesby);
	}


	


}
