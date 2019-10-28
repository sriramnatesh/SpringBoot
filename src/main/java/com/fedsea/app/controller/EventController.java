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
import com.fedsea.app.model.Event;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.EventRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IEventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

	private static final String EVENT_ADDED_SUCCESSFULLY = "Event added successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String INVALIDATE_EVENT_ID = "Event id is not valid";

	private static final String NO_EVENTS_AVAILABLE = "No Events Available";

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private IEventService eventService;

	@Autowired
	private UserRepository userRepository;

//	========================Api Method For Add Event=============================
	@RequestMapping(value = "/user/{userId}/event", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addEvent(@PathVariable Long userId, @RequestBody Event event) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				event.setUserId(userId);
				eventService.addEvent(event);
				if (event.getEventId() != null) {
					apiResponseDtoBuilder.withMessage(EVENT_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK)
							.withData(event);
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

//	======================Api Method For Update Event Information==========================
	@RequestMapping(value = "/user/{userId}/event/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto updateUserInformation(@PathVariable Long userId, @PathVariable Long eventId,
			@RequestBody Event event) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			if (userId != 0) {
				Optional<User> dbUser = userRepository.findById(userId);
				if (dbUser.isPresent()) {
					Event dbEvent = eventRepository.findByEventId(eventId);
					if (dbEvent != null) {
						event.setEventId(eventId);
						event.setUserId(userId);
						eventRepository.save(event);
						apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(dbEvent);
					} else {
						apiResponseDtoBuilder.withMessage(INVALIDATE_EVENT_ID).withStatus(HttpStatus.NO_CONTENT);
					}
				} else {
					apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
		}

		return apiResponseDtoBuilder.build();
	}

//	===========================Api Method For Get Events by user Id===========================
	@RequestMapping(value = "/user/{userId}/events", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getEvents(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				List<Event> listEvents = eventRepository.findByUserId(userId);
				if (!listEvents.isEmpty()) {
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(listEvents);
				} else {
					apiResponseDtoBuilder.withMessage(NO_EVENTS_AVAILABLE).withStatus(HttpStatus.NO_CONTENT);
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
