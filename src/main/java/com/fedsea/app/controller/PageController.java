package com.fedsea.app.controller;

import java.util.List;

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
import com.fedsea.app.model.Pages;
import com.fedsea.app.model.Post;
import com.fedsea.app.service.IPageService;
import com.fedsea.app.service.IPostService;

@RestController
@RequestMapping("/api/page")
public class PageController {

	@Autowired
	private IPageService pageService;

	/**
	 * This method designed to add a new page for provided user.
	 * 
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/page", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addNewPost(@PathVariable Long userId, @RequestBody Pages page) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		page.setUserId(userId);
		pageService.addPage(page);
		if (page.getId() != null) {
			apiResponseDtoBuilder.withMessage("page added successfully.").withStatus(HttpStatus.OK).withData(page);
		} else {
			apiResponseDtoBuilder.withMessage("UnExpected error occured.").withStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This method designed to update given page by Page Id.
	 * 
	 * @param userId
	 * @param pageId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/page/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto editPost(@PathVariable Long userId, @PathVariable Long pageId, @RequestBody Pages page) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (pageService.getPage(pageId) != null) {
			pageService.updatePage(pageId, page);
			apiResponseDtoBuilder.withMessage("page updated successfully").withStatus(HttpStatus.OK).withData(page);
		} else {
			apiResponseDtoBuilder.withMessage("page doesn't exists with given page ID.").withStatus(HttpStatus.OK)
					.withData(page);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This method designed to delete page by pageId.
	 * 
	 * @param userId
	 * @param pageId
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ApiResponseDto deletePost(@PathVariable Long userId, @PathVariable Long postId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		pageService.deletePage(postId);
		apiResponseDtoBuilder.withMessage("Page deleted Successfully.").withStatus(HttpStatus.OK);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This method designed to add a new post for provided user.
	 * 
	 * @param userId
	 * @param postId
	 * @param post
	 * @return
	 */
//	@RequestMapping(value = "/user/{userId}/post", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	public ApiResponseDto getUserPosts(@PathVariable(required = true) Long userId) {
//		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
//		List<Post> posts = postService.getPosts(userId);
//		if (posts != null && !posts.isEmpty()) {
//			apiResponseDtoBuilder.withStatus(HttpStatus.OK).withData(posts);
//		} else {
//			apiResponseDtoBuilder.withMessage("No Posts found").withStatus(HttpStatus.NO_CONTENT);
//		}
//		return apiResponseDtoBuilder.build();
//	}


}
