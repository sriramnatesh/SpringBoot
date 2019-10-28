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
import com.fedsea.app.dto.ConnectDto;
import com.fedsea.app.model.Friend;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.FriendRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IFriendService;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

	private static final String REQUEST_SEND_SUCCESSFULLY = "Request send successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String NO_REQUEST_PENDING = "No Request Pending";

	@Autowired
	private IFriendService friendService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendRepository friendRepository;

	/*
	 * ==================================== Api Method For Send Friend
	 * Request==============================
	 */
	@RequestMapping(value = "/user/{userId}/request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto sendFriendRequest(@PathVariable Long userId, @RequestBody Friend friend) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				friendService.sendRequest(userId, friend);
				if (friend.getId() != null) {
					apiResponseDtoBuilder.withMessage(REQUEST_SEND_SUCCESSFULLY).withStatus(HttpStatus.OK)
							.withData(friend);
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

	/*
	 * =========================== Api Method For Connect to Requested
	 * Friend===================================
	 */
	@RequestMapping(value = "/user/{userId}/friend", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto connect(@PathVariable Long userId, @RequestBody ConnectDto connectDto) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Friend dbFriend = friendRepository.findBySenderIdAndRequestId(userId, connectDto.getRequestId());
				if (dbFriend != null) {
					friendService.connect(dbFriend);
					apiResponseDtoBuilder.withMessage(REQUEST_SEND_SUCCESSFULLY).withStatus(HttpStatus.OK)
							.withData(dbFriend);
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

	/*
	 * ========================= Api Method For UnConnect to Connected
	 * Friend========================
	 */
	@RequestMapping(value = "/user/{userId}/unfriend", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto unConnect(@PathVariable Long userId, @RequestBody Friend friend) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Friend dbFriend = friendRepository.findBySenderIdAndRequestId(userId, friend.getRequestId());
				if (dbFriend != null) {
					friendService.unConnect(dbFriend);
					apiResponseDtoBuilder.withMessage(REQUEST_SEND_SUCCESSFULLY).withStatus(HttpStatus.OK)
							.withData(dbFriend);
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

//	===========================Api Method For Get Friend Request List by user Id===========================
	@RequestMapping(value = "/user/{userId}/friends", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getEvents(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				List<Friend> listFriendRequest = friendRepository.findByRequestId(userId);
				if (!listFriendRequest.isEmpty()) {
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(listFriendRequest);
				} else {
					apiResponseDtoBuilder.withMessage(NO_REQUEST_PENDING).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}

	@RequestMapping(value = "/user/{userId}/getallfriends", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getAllfriends(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				List<Long> listFriends = friendRepository.findAllFriends(userId);
				
				
				if (!listFriends.isEmpty()) {
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(listFriends);
				} else {
					apiResponseDtoBuilder.withMessage(NO_REQUEST_PENDING).withStatus(HttpStatus.NO_CONTENT);
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
