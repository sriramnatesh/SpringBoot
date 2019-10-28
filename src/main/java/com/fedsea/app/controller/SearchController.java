package com.fedsea.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.service.ISearchedStringService;
import com.fedsea.app.service.IUserService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISearchedStringService searchStringService;
	
	private static final String INVALIDATE_USER_ID = "User id/Search String is not valid";

	
	/*
	 * =========================Api Method For  Search Username 
	 * =============================
	 */
	@RequestMapping(value = "/friends/{userId}/user/{userName}/get",method = RequestMethod.GET)
	public ApiResponseDto getSearchStrings(@PathVariable String userName,@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userName != null && userId != null ) {
			
			List<Object> users=userService.getAllUsernameLike(userName);
			
			if(!users.isEmpty()) {
				apiResponseDtoBuilder.withMessage("Users Searched_SUCCESSFULLY").withStatus(HttpStatus.OK).withData(users);	
				searchStringService.addSearchString(userId,userName);
			}else apiResponseDtoBuilder.withMessage("NO users with given Name").withStatus(HttpStatus.NO_CONTENT);

		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
	
	
	/*
	 * =========================Api Method For Add Searched History Strings
	 * =============================
	 */
	@RequestMapping(value = "/friends/{userId}/searchString/{userName}/get",method = RequestMethod.GET)
	public ApiResponseDto getSearchStringHistory(@PathVariable String userName,@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userName != null && userId != null ) {
			
			List<Object> historyStrings=searchStringService.getAllsearchStringLike(userId, userName);
			
			if(!historyStrings.isEmpty()) {
				apiResponseDtoBuilder.withMessage("Users Searched_String/s").withStatus(HttpStatus.OK).withData(historyStrings);	
				/* searchStringService.addSearchString(userId,userName); */
			}else apiResponseDtoBuilder.withMessage("NO users with given Name").withStatus(HttpStatus.NO_CONTENT);

		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}

	/*
	 * =========================Api Method For Add Searched History By UserId
	 * =============================
	 */
	@RequestMapping(value = "/friends/{userId}/searchString/get",method = RequestMethod.GET)
	public ApiResponseDto getSearchStringHistoryUserId(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != null ) {
			
			List<Object> historyStrings=searchStringService.getAllsearchbyUserId(userId);
			
			if(!historyStrings.isEmpty()) {
				apiResponseDtoBuilder.withMessage("Users Searched_String/s").withStatus(HttpStatus.OK).withData(historyStrings);	
				/* searchStringService.addSearchString(userId,userName); */
			}else apiResponseDtoBuilder.withMessage("NO users with given Name").withStatus(HttpStatus.NO_CONTENT);

		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
	
		
	/*
	 * =========================Api Method For delete Searched History by UserId
	 * =============================
	 */
	@RequestMapping(value = "/friends/{userId}/searchString/delete",method = RequestMethod.DELETE)
	public ApiResponseDto deleteSearchStringHistoryUser(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if ( userId != null ) {
			
			List<Object> historyStrings=searchStringService.getAllsearchbyUserId(userId);
			
			if(!historyStrings.isEmpty()) {
				searchStringService.deleteSearchHistory(userId);
				apiResponseDtoBuilder.withMessage("Users Searched history deleted").withStatus(HttpStatus.OK);	
				/* searchStringService.addSearchString(userId,userName); */
			}else apiResponseDtoBuilder.withMessage("NO search result by user").withStatus(HttpStatus.NO_CONTENT);

		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
	
	
	

}
