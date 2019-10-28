package com.fedsea.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Post;
import com.fedsea.app.repository.PostRepository;

@Service
public class PostServiceImp implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public void addPost(Post post) {
		postRepository.save(post);
	}

	@Override
	public void updatePost(long postId, Post post) {
		post.setId(postId);
		postRepository.save(post);
	}

	@Override
	public void deletePost(long postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public List<Post> getPosts(long userId) {
		List<Post> posts = postRepository.findByUserId(userId);
		if (posts == null || posts.isEmpty()) {
			return Collections.emptyList();
		}
		return posts;
	}

	@Override
	public Post getPost(long postId) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			return post.get();
		} else {
			return null;
		}
	}
}
