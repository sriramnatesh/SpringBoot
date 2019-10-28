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
import com.fedsea.app.model.CustomEvent;
import com.fedsea.app.model.Festivals;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.ChatRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IChatService;
import com.fedsea.app.service.ICustomEventService;
import com.fedsea.app.service.IFestivalService;

@RestController
@RequestMapping("/api/remember")
public class CustomEventController {

	private static final String CUSTOM_EVENT_ADD_SUCCESSFULLY = "Custom Event added successfully.";

	private static final String CUSTOM_EVENT_REMOVED_SUCCESSFULLY = "Custom Event removed successfully.";

	
	private static final String NO_USER_EXISTS = "No User Exists";


	private static final String INVALIDATE_USER_ID = "User id is not valid";


	private static final String NO_CUSTOM_EVENT_EXISTS = "No Custom Event Exists";
	
	private static final String CUSTOM_EVENT_EXISTS = "Custom Event Exists";

	@Autowired
	private UserRepository userRepository;


	@Autowired
	public ICustomEventService customEventService;

	/*
	 * =========================Api Method For Add Custom Event
	 * =============================
	 */
	@RequestMapping(value = "/cevent/user/add",method = RequestMethod.POST)
	public ApiResponseDto addFestival(@RequestBody CustomEvent customEvent) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (customEvent.getUserId() != 0) {
			Optional<User> dbUser = userRepository.findById(customEvent.getUserId());
			if (dbUser.isPresent()) {
				customEventService.addCustomEvent(customEvent);
				if (customEvent.getCustomeventId() != null) {
					apiResponseDtoBuilder.withMessage(CUSTOM_EVENT_ADD_SUCCESSFULLY).withStatus(HttpStatus.OK).withData(customEvent);
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
	 * =========================Api Method For removes Custom Event
	 * =============================
	 */
	@RequestMapping(value = "/cevent/{userId}/{ceventId}/remove", method = RequestMethod.DELETE)
	public ApiResponseDto deleteFestival(@PathVariable Long userId, @PathVariable Long ceventId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				CustomEvent event = customEventService.findByUserIdAndId(userId, ceventId);
				if (event != null) {
					if (event.getCustomeventId() != null) {
						customEventService.deleteCustomEvent(ceventId);
						apiResponseDtoBuilder.withMessage(CUSTOM_EVENT_REMOVED_SUCCESSFULLY).withStatus(HttpStatus.OK)
								.withData(event);
					} else {
						apiResponseDtoBuilder.withMessage(NO_CUSTOM_EVENT_EXISTS).withStatus(HttpStatus.BAD_REQUEST);
					}

				} else
					apiResponseDtoBuilder.withMessage(NO_CUSTOM_EVENT_EXISTS).withStatus(HttpStatus.BAD_REQUEST);

			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
	
	
	/*
	 * =========================Api Method For Get Custom Event
	 * =============================
	 */
	@RequestMapping(value = "/cevent/{userId}/get",method = RequestMethod.GET)
	public ApiResponseDto getFestival(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != null) {
			
			
			List<CustomEvent> cevents=customEventService.findByUserId(userId);
			
			// boolean ans = festivals.isEmpty(); 
		        if (cevents.isEmpty() != true) { 
		        	 apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage(CUSTOM_EVENT_EXISTS).withData(cevents);
		        	 System.out.print("FESTIVALS EXIST");
		        } else {
		        	apiResponseDtoBuilder.withMessage(NO_CUSTOM_EVENT_EXISTS).withStatus(HttpStatus.NO_CONTENT); 
		        	System.out.print(" NO FESTIVALS EXIST");
		        }
			
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
	
}
