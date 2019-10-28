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
import com.fedsea.app.model.Chat;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.ChatRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	private static final String CHAT_ADD_SUCCESSFULLY = "Chat added successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String NO_RECEIVER_EXISTS = "No Receiver Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String CHAT_BURN_SUCCESSFULLY = "Chat burn successfully";

	private static final String NO_CHAT_EXISTS = "No Chat Exists";

	@Autowired
	private IChatService chatService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChatRepository chatRepository;

	/*
	 * =========================Api Method For Add Chat
	 * =============================
	 */
	@RequestMapping(value = "/user/{userId}/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addChat(@PathVariable Long userId, @RequestBody Chat chat) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				chatService.addChat(userId, chat);
				if (chat.getChatId() != null) {
					apiResponseDtoBuilder.withMessage(CHAT_ADD_SUCCESSFULLY).withStatus(HttpStatus.OK).withData(chat);
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
	 * ============================Api Method For Burn Chat After Read
	 * Chat=========================
	 */
	@RequestMapping(value = "/user/{userId}/burn", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto burnChat(@PathVariable Long userId, @RequestBody Chat chat) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Chat dbChat = chatRepository.findByChatId(chat.getChatId());
				if (dbChat != null) {
					chatService.burnChat(userId, dbChat);
					apiResponseDtoBuilder.withMessage(CHAT_BURN_SUCCESSFULLY).withStatus(HttpStatus.OK)
							.withData(dbChat);
				} else {
					apiResponseDtoBuilder.withMessage(NO_CHAT_EXISTS).withStatus(HttpStatus.BAD_REQUEST);
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
	 * ================================ Api Method For Get Chats For Sender and
	 * Receiver=================================
	 */
	@RequestMapping(value = "/user/{userId}/receiver/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getChats(@PathVariable Long userId, @PathVariable Long receiverId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbSender = userRepository.findById(userId);
			Optional<User> dbReceiver = userRepository.findById(receiverId);
			if (dbSender.isPresent()) {
				if (dbReceiver.isPresent()) {
					List<Chat> chatList = chatRepository.findBySenderIdAndReceiverId(userId, receiverId);
					if (!chatList.isEmpty()) {
						apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(chatList);
					} else {
						apiResponseDtoBuilder.withMessage(NO_CHAT_EXISTS).withStatus(HttpStatus.BAD_REQUEST);
					}
				} else {
					apiResponseDtoBuilder.withMessage(NO_RECEIVER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
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
