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
import com.fedsea.app.model.Activities;
import com.fedsea.app.model.Festivals;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IActivitiesService;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {


	@Autowired
	private IActivitiesService activitiesService;
		
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * This method designed to add a new activities for provided user.
	 * 
	 * @param userId
	 * @param activities
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addNewActivities(@PathVariable Long userId, @RequestBody Activities activities) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		activities.setUserId(userId);
		activitiesService.addActivities(activities);
		if (activities.getId() != null) {
			apiResponseDtoBuilder.withMessage("activities added successfully.").withStatus(HttpStatus.OK).withData(activities);
		} else {
			apiResponseDtoBuilder.withMessage("UnExpected error occured.").withStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * This method designed to delete activities by activitiesId.
	 * 
	 * @param userId
	 * @param activitiesId
	 * @return
	 */
	
	@RequestMapping(value = "/user/{userId}/activities/{activitiesId}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ApiResponseDto deleteActivities(@PathVariable Long userId,@PathVariable Long activitiesId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Activities activities=activitiesService.findByUserIdAndId(userId, activitiesId);
				
				if(activities!= null) {
					if (activities.getId() != null) {
						activitiesService.removeActivities(activitiesId);
						apiResponseDtoBuilder.withMessage("Activity Removed Succesfully").withStatus(HttpStatus.OK);
					} else {
						
					}
				}else apiResponseDtoBuilder.withMessage("Activity Does not Exist").withStatus(HttpStatus.BAD_REQUEST);

			} else {
				apiResponseDtoBuilder.withMessage("NO_USER_EXISTS").withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage("INVALIDATE_USER_ID").withStatus(HttpStatus.NO_CONTENT);
		}
	return apiResponseDtoBuilder.build();
	}

	
	/**
	 * This method designed to get activities by userId.
	 * 
	 * @param userId
	 * @param 
	 * @return
	 */
	
	@RequestMapping(value = "/user/{userId}/activities/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getActivities(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
			List<Activities> activitiess=activitiesService.findByUserId(userId);
				
			if(!activitiess.isEmpty()) {
				apiResponseDtoBuilder.withMessage("Activity Exist").withStatus(HttpStatus.OK).withData(activitiess);

			}else apiResponseDtoBuilder.withMessage("Activity Does not Exist").withStatus(HttpStatus.BAD_REQUEST);

			} else {
				apiResponseDtoBuilder.withMessage("NO_USER_EXISTS").withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage("INVALIDATE_USER_ID").withStatus(HttpStatus.NO_CONTENT);
		}
	return apiResponseDtoBuilder.build();
	}


}
