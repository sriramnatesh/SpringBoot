package com.fedsea.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.UserBdto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.model.Friend;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IFriendService;
import com.fedsea.app.service.IUserService;

@RestController
@RequestMapping("/api/remember")
public class BirthdayAndFestivalController {

	
	@Autowired
	private IFriendService friendService;

	@Autowired
	private IUserService userService;
	/*
	 * ==================================== Api Method birthdays details of friend
	 * Request==============================
	 */
	@RequestMapping(value = "/{userId}/{dateTo}/birthdays", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseDto displayBirthdays(@PathVariable Long userId,@PathVariable Long dateTo)throws AuthenticationException {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();

		try {
			System.out.print("Login ID"+userId +dateTo );
			//get a friend list from the table ,check their birthdays ,make ascending order from current date
		//	friendService.findBySenderIdAndStatus(userId,1);
			System.out.print(friendService.findByRequestIdAndSenderIdAndStatus(userId,1));
			List<Long> userList=friendService.findByRequestIdAndSenderIdAndStatus(userId,1);
			List<Object> userbirthdays=userService.findAllWithCustomObject(userList);
			apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage("Some message").withData(userbirthdays);
			//get friends birth days on the given date 
			
		}catch(Exception e) {
		
			//Error update
			apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage("Some Exception Error");

		}
		
		
		return apiResponseDtoBuilder.build();
	}

	
	/*
	 * ==================================== Api Method birthdays details of friend
	 * Request==============================
	 */
	@RequestMapping(value = "/festivals", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto displayFestivals( @RequestBody Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();

		
		
		return apiResponseDtoBuilder.build();
	}
	
	
}
