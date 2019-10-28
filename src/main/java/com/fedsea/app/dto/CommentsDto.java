package com.fedsea.app.dto;

import com.fedsea.app.model.User;

public class CommentsDto {
	private String comment;
	private User userDto;
	private Long commentId;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUserDto() {
		return userDto;
	}

	public void setUserDto(User userDto) {
		this.userDto = userDto;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

}
