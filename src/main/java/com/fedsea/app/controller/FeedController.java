package com.fedsea.app.controller;

import java.util.List;
import java.util.Optional;

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
import com.fedsea.app.model.Feed;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.FeedRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IFeedService;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {

	private static final String FEED_ADDED_SUCCESSFULLY = "Feed added successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String NO_FEEDS_FOUND = "No feeds found";

	@Autowired
	private IFeedService feedService;

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private UserRepository userRepository;

//	===============================Api Method For Add Feed======================================
	@RequestMapping(value = "/v1/user/{userId}/feed", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto sendFriendRequest(@PathVariable Long userId, @RequestBody Feed feed) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				feedService.addFeed(userId, feed);
				if (feed.getId() != null) {
					apiResponseDtoBuilder.withMessage(FEED_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK).withData(feed);
				} else {
					apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST);
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}

//	===========================Api Method For Get Feeds by user Id===========================
	@RequestMapping(value = "/v1/user/{userId}/feeds", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getEvents(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				List<Feed> listFeeds = feedRepository.findByUserId(userId);
				if (!listFeeds.isEmpty()) {
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(listFeeds);
				} else {
					apiResponseDtoBuilder.withMessage(NO_FEEDS_FOUND).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
}
