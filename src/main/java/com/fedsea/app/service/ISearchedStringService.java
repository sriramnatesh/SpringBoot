package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Activities;
import com.fedsea.app.model.Friend;
import com.fedsea.app.model.SearchedString;

@Service
public interface ISearchedStringService {

	List<SearchedString> findByUserId(Long userId);
	
	List<Object>  getAllsearchStringLike(Long userId,String username);
	
	
	void addSearchString(Long userId,String strings);

	List<Object>  getAllsearchbyUserId(Long userId);
	
	void deleteSearchHistory(Long userId);
	
}
