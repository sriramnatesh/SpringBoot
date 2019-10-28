package com.fedsea.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.fedsea.app.dto.CommentsDto;
import com.fedsea.app.model.Comment;
import com.fedsea.app.service.ICommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	/**
	 * This API designed to add a new comment to POST.
	 * 
	 * @param postId
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/v1/post/{postId}/comment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addComment(@PathVariable Long postId, @Valid @RequestBody Comment comment) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		commentService.addComment(comment, postId);
		apiResponseDtoBuilder.withMessage("You commented on post successfully.").withStatus(HttpStatus.OK);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This API designed to update existing comment.
	 * 
	 * @param postId
	 * @param commentId
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/v1/post/{postId}/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto editComment(@PathVariable Long postId, @PathVariable Long commentId,
			@Valid @RequestBody Comment comment) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (commentService.getComment(commentId) != null) {
			commentService.updateComment(comment, postId, commentId);
			apiResponseDtoBuilder.withMessage("Comment updated successfully.").withData(comment)
					.withStatus(HttpStatus.OK);
		} else {
			apiResponseDtoBuilder.withMessage("Comment with given commentId does not exists")
					.withStatus(HttpStatus.NO_CONTENT);
		}

		return apiResponseDtoBuilder.build();
	}

	/**
	 * This API designed to get all the comment for given post id, it will fetch
	 * all comments data and return it to client application
	 * 
	 * @param postId
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/v1/post/{postId}/comments", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getComments(@PathVariable Long postId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		List<CommentsDto> comments = commentService.getComments(postId);
		apiResponseDtoBuilder.withStatus(HttpStatus.OK).withData(comments);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This API designed to get all the comment for given post id, it will fetch
	 * all comments data and return it to client application
	 * 
	 * @param postId
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/v1/post/{postId}/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ApiResponseDto commentingOnPost(@PathVariable Long postId, @PathVariable Long commentId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (commentService.getComment(commentId) != null) {
			commentService.deleteComment(commentId);
			apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage("Comment deleted successfully.");
		} else {
			apiResponseDtoBuilder.withMessage("Comment with given commentId does not exists")
					.withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
}
