package com.fedsea.app.service;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Feed;

@Service
public interface IFeedService {

	void addFeed(Long userId, Feed feed);

}
