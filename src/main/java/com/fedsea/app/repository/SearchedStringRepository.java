package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fedsea.app.model.Activities;
import com.fedsea.app.model.Chat;
import com.fedsea.app.model.SearchedString;

public interface SearchedStringRepository extends JpaRepository<SearchedString, Long> {


	List<SearchedString> findByUserId(Long userId);
	
	  
    @Query(value = "\n" +
	  "SELECT searched_String ,searched_Time FROM searchstring WHERE user_Id =?1 AND searched_String LIKE %?2%"
	  , nativeQuery = true )List<Object>  getAllsearchStringLike(Long userId,String username);
	  
    @Query(value = "\n" +
	  "SELECT searched_String ,searched_Time FROM searchstring WHERE user_Id =?1 "
	  , nativeQuery = true )List<Object>  getAllsearchbyUserId(Long userId);
	  
	   
    @Transactional
    Long deleteByUserId(Long userId);
	
}
