package com.fedsea.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.dto.LikeDto;
import com.fedsea.app.dto.LikesResponse;
import com.fedsea.app.dto.ReactionDto;
import com.fedsea.app.model.Like;
import com.fedsea.app.model.PageLike;
import com.fedsea.app.repository.LikeRepository;
import com.fedsea.app.repository.PageLikeRepository;

@Service
public class LikeServiceImp implements ILikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private PageLikeRepository pageLikeRepository;

	@Override
	public LikesResponse getLikes(long postId) {
		LikesResponse likesResponse = new LikesResponse();
		int totalLikes = likeRepository.countByPostId(postId);
		int totalUnLikes = likeRepository.countByReactionTypeAndPostId("unlike", postId);
		int like = likeRepository.countByReactionTypeAndPostId("like", postId);
		int angel = likeRepository.countByReactionTypeAndPostId("angel", postId);
		int love = likeRepository.countByReactionTypeAndPostId("love", postId);
		int haHaHa = likeRepository.countByReactionTypeAndPostId("hahaha", postId);

		ReactionDto reactionDto = new ReactionDto();
		reactionDto.setLike(like);
		reactionDto.setAngel(angel);
		reactionDto.setLove(love);
		reactionDto.setHahaha(haHaHa);
		likesResponse.setReactions(reactionDto);
		likesResponse.setTotalLikes(totalLikes);
		likesResponse.setTotalUnLikes(totalUnLikes);
		return likesResponse;
	}

	@Override
	public void addLike(LikeDto likeDto, long postId) {
		Like like = new Like();
		like.setPostId(postId);
		like.setUserId(likeDto.getUserId());
		like.setReactionType(likeDto.getReactionType());
		Like checkAlready = likeRepository.findByUserIdAndPostId(likeDto.getUserId(), postId);
		if (checkAlready != null) {
			like.setId(checkAlready.getId());
			likeRepository.save(like);
		} else {
			likeRepository.save(like);
		}
	}
		
	@Override
	public LikesResponse getPageLikes(long pageId) {
		LikesResponse likesResponse = new LikesResponse();
		int totalLikes = pageLikeRepository.countByPageId(pageId);
		int totalUnLikes = pageLikeRepository.countByReactionTypeAndPageId("unlike", pageId);
		int like = pageLikeRepository.countByReactionTypeAndPageId("like", pageId);
		/*
		 * int angel = likeRepository.countByReactionTypeAndPostId("angel", postId); int
		 * love = likeRepository.countByReactionTypeAndPostId("love", postId); int
		 * haHaHa = likeRepository.countByReactionTypeAndPostId("hahaha", postId);
		 */

		ReactionDto reactionDto = new ReactionDto();
		reactionDto.setLike(like);
		/*
		 * reactionDto.setAngel(angel); reactionDto.setLove(love);
		 * reactionDto.setHahaha(haHaHa);
		 */
		likesResponse.setReactions(reactionDto);
		likesResponse.setTotalLikes(totalLikes);
		likesResponse.setTotalUnLikes(totalUnLikes);
		return likesResponse;
	}

	@Override
	public void addPageLike(LikeDto likeDto, long pageId) {
		PageLike plike = new PageLike();
		plike.setPageId(pageId);
		plike.setUserId(likeDto.getUserId());
		plike.setReactionType(likeDto.getReactionType());
		PageLike checkAlready = pageLikeRepository.findByUserIdAndPageId(likeDto.getUserId(), pageId);
		if (checkAlready != null) {
			plike.setId(checkAlready.getId());
			pageLikeRepository.save(plike);
		} else {
			pageLikeRepository.save(plike);
		}
	}
	

}
