package com.fedsea.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.dto.CommentsDto;
import com.fedsea.app.model.Comment;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.CommentRepository;
import com.fedsea.app.repository.UserRepository;

@Service
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addComment(Comment comment, long postId) {
		comment.setPostId(postId);
		commentRepository.save(comment);
	}

	@Override
	public void updateComment(Comment comment, long postId, long commentId) {
		comment.setPostId(postId);
		comment.setId(commentId);
		commentRepository.save(comment);
	}

	@Override
	public CommentsDto getComment(long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		CommentsDto commentDto = null;
		if (comment.isPresent()) {
			commentDto = new CommentsDto();
			commentDto.setComment(comment.get().getComment());
			commentDto.setCommentId(comment.get().getId());
			commentDto.setUserDto(getuser(comment.get()));
		}
		return commentDto;
	}

	@Override
	public List<CommentsDto> getComments(long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
		List<CommentsDto> commentsDtos = new ArrayList<>();
		for (Comment comment : comments) {
			CommentsDto commentDto = new CommentsDto();
			commentDto.setComment(comment.getComment());
			commentDto.setUserDto(getuser(comment));
			commentDto.setCommentId(comment.getId());
			commentsDtos.add(commentDto);
		}
		return commentsDtos;
	}

	private User getuser(Comment comment) {
		Optional<User> user = userRepository.findById(comment.getCommentBy());
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}
