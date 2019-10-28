package com.fedsea.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Feed;
import com.fedsea.app.repository.FeedRepository;

@Service
public class FeedServiceImpl implements IFeedService {
	
	@Autowired
	private FeedRepository feedRepository;

	@Override
	public void addFeed(Long userId, Feed feed) {
		feed.setUserId(userId);
		feedRepository.save(feed);
	}

}
