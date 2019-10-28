package com.fedsea.app.service;

import org.springframework.stereotype.Service;

import com.fedsea.app.dto.LikeDto;
import com.fedsea.app.dto.LikesResponse;

@Service
public interface ILikeService {

	LikesResponse getLikes(long postId);

	void addLike(LikeDto likeDto, long postId);
	
	LikesResponse getPageLikes(long pageId);
	
	void addPageLike(LikeDto likeDto, long pageId);

}
