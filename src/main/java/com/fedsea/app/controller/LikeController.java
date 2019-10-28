package com.fedsea.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.dto.LikeDto;
import com.fedsea.app.dto.LikesResponse;
import com.fedsea.app.service.ILikeService;

@RestController
@RequestMapping("/api/like")
public class LikeController {

	@Autowired
	private ILikeService likeService;

	/*
	 * ======================= Api Method For Like Post
	 * =============================
	 */
	@RequestMapping(value = "/v1/post/{postId}/like", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto likePost(@PathVariable Long postId, @RequestBody LikeDto likeDto) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		likeService.addLike(likeDto, postId);
		LikesResponse likes = likeService.getLikes(postId);
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(likes);
		return apiResponseDtoBuilder.build();
	}
	
	
	/*
	 * ======================= Api Method For UnLike Post
	 * =============================
	 */
	@RequestMapping(value = "/v1/post/{postId}/unlike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto unLikePost(@PathVariable Long postId, @RequestBody LikeDto likeDto) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		likeDto.setReactionType("unlike");
		likeService.addLike(likeDto, postId);
		LikesResponse likes = likeService.getLikes(postId);
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(likes);
		return apiResponseDtoBuilder.build();
	}
	
	/*
	 * ======================= Api Method For Like Page
	 * =============================
	 */
	@RequestMapping(value = "/v1/page/{pageId}/like", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto likePage(@PathVariable Long pageId, @RequestBody LikeDto likeDto) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		likeService.addPageLike(likeDto, pageId);
		LikesResponse likes = likeService.getPageLikes(pageId);
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(likes);
		return apiResponseDtoBuilder.build();
	}

	/*
	 * ======================= Api Method For UnLike Page
	 * =============================
	 */
	@RequestMapping(value = "/v1/page/{pageId}/unlike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto unLikePage(@PathVariable Long pageId, @RequestBody LikeDto likeDto) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		likeDto.setReactionType("unlike");
		likeService.addPageLike(likeDto, pageId);
		LikesResponse likes = likeService.getPageLikes(pageId);
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(likes);
		return apiResponseDtoBuilder.build();
	}

}