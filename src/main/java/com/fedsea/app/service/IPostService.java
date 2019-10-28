package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Post;

@Service
public interface IPostService {

	void addPost(Post post);

	void updatePost(long postId, Post post);

	void deletePost(long postId);

	Post getPost(long postId);

	List<Post> getPosts(long userId);

}
