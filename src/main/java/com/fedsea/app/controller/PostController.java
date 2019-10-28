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
import com.fedsea.app.model.Post;
import com.fedsea.app.service.IPostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private IPostService postService;

	/**
	 * This method designed to add a new post for provided user.
	 * 
	 * @param userId
	 * @param postId
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/post", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addNewPost(@PathVariable Long userId, @RequestBody Post post) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		post.setUserId(userId);
		postService.addPost(post);
		if (post.getId() != null) {
			apiResponseDtoBuilder.withMessage("Post added successfully.").withStatus(HttpStatus.OK).withData(post);
		} else {
			apiResponseDtoBuilder.withMessage("UnExpected error occured.").withStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This method designed to update given post by Post Id.
	 * 
	 * @param userId
	 * @param postId
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto editPost(@PathVariable Long userId, @PathVariable Long postId, @RequestBody Post post) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (postService.getPost(postId) != null) {
			postService.updatePost(postId, post);
			apiResponseDtoBuilder.withMessage("Post updated successfully").withStatus(HttpStatus.OK).withData(post);
		} else {
			apiResponseDtoBuilder.withMessage("Post doesn't exists with given Post ID.").withStatus(HttpStatus.OK)
					.withData(post);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This method designed to delete post by postId.
	 * 
	 * @param userId
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ApiResponseDto deletePost(@PathVariable Long userId, @PathVariable Long postId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		postService.deletePost(postId);
		apiResponseDtoBuilder.withMessage("Post deleted Successfully.").withStatus(HttpStatus.OK);
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
	@RequestMapping(value = "/user/{userId}/post", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getUserPosts(@PathVariable(required = true) Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		List<Post> posts = postService.getPosts(userId);
		if (posts != null && !posts.isEmpty()) {
			apiResponseDtoBuilder.withStatus(HttpStatus.OK).withData(posts);
		} else {
			apiResponseDtoBuilder.withMessage("No Posts found").withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
}
