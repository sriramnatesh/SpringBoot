package com.fedsea.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.dto.LikeDto;
import com.fedsea.app.dto.LikesResponse;
import com.fedsea.app.dto.ReactionDto;
import com.fedsea.app.model.Like;
import com.fedsea.app.model.PageLike;
import com.fedsea.app.model.SearchedString;
import com.fedsea.app.repository.LikeRepository;
import com.fedsea.app.repository.PageLikeRepository;
import com.fedsea.app.repository.SearchedStringRepository;

@Service
public class SearchedStringServiceImp implements ISearchedStringService {

	@Autowired
	private SearchedStringRepository searchrepository;
	
	@Override
	public List<SearchedString> findByUserId(Long userId) {
		return searchrepository.findByUserId(userId);
	}

	@Override
	public List<Object> getAllsearchStringLike(Long userId, String username) {
		return searchrepository.getAllsearchStringLike(userId, username);
	}

	@Override
	public void addSearchString(Long userId,String strings) {
		SearchedString saveString=new SearchedString();
		saveString.setUserId(userId);
		saveString.setSearchedString(strings);
		searchrepository.save(saveString);
	}

	@Override
	public List<Object> getAllsearchbyUserId(Long userId) {
		// TODO Auto-generated method stub
		return searchrepository.getAllsearchbyUserId(userId);
	}

	@Override
	public void deleteSearchHistory(Long userId) {
		searchrepository.deleteByUserId(userId);
	}


}
