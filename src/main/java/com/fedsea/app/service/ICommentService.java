package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.dto.CommentsDto;
import com.fedsea.app.model.Comment;

@Service
public interface ICommentService {

	void addComment(Comment comment, long postId);

	List<CommentsDto> getComments(long postId);

	CommentsDto getComment(long commentId);

	void updateComment(Comment comment, long postId, long commentId);

	void deleteComment(Long commentId);

}
