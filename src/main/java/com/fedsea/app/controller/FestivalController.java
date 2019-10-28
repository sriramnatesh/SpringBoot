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
import com.fedsea.app.model.Festivals;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.ChatRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IChatService;
import com.fedsea.app.service.IFestivalService;

@RestController
@RequestMapping("/api/remember")
public class FestivalController {

	private static final String FESTIVAL_ADD_SUCCESSFULLY = "Festival added successfully.";

	private static final String FESTIVAL_REMOVED_SUCCESSFULLY = "Festival removed successfully.";

	
	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String NO_RECEIVER_EXISTS = "No Receiver Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String CHAT_BURN_SUCCESSFULLY = "Chat burn successfully";

	private static final String NO_FESTIVAL_EXISTS = "No Festival Exists";
	
	private static final String FESTIVAL_EXISTS = "No Festival Exists";

	@Autowired
	private UserRepository userRepository;


	@Autowired
	public IFestivalService festivalService;

	/*
	 * =========================Api Method For Add Festival
	 * =============================
	 */
	@RequestMapping(value = "/festival/user/add",method = RequestMethod.POST)
	public ApiResponseDto addFestival(@RequestBody Festivals festival) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (festival.getUserId() != 0) {
			Optional<User> dbUser = userRepository.findById(festival.getUserId());
			if (dbUser.isPresent()) {
				festivalService.addFestival(festival);
				if (festival.getFestivalId() != null) {
					apiResponseDtoBuilder.withMessage(FESTIVAL_ADD_SUCCESSFULLY).withStatus(HttpStatus.OK).withData(festival);
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
	 * =========================Api Method For removes Festival
	 * =============================
	 */
	@RequestMapping(value = "/festival/{userId}/{festId}/remove",method = RequestMethod.DELETE)
	public ApiResponseDto deleteFestival(@PathVariable Long userId,@PathVariable Long festId ) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Festivals fests=festivalService.findBYUserIdAndId(userId, festId);
				
				if(fests != null) {
					if (fests.getFestivalId() != null) {
						festivalService.deleteFestival(festId);
						apiResponseDtoBuilder.withMessage(FESTIVAL_REMOVED_SUCCESSFULLY).withStatus(HttpStatus.OK).withData(fests);
					} else {
						apiResponseDtoBuilder.withMessage(NO_FESTIVAL_EXISTS).withStatus(HttpStatus.BAD_REQUEST);
					}
				}else {
					apiResponseDtoBuilder.withMessage(NO_FESTIVAL_EXISTS).withStatus(HttpStatus.BAD_REQUEST);
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
	 * =========================Api Method For get Festival
	 * =============================
	 */
	@RequestMapping(value = "/festival/{userId}/get",method = RequestMethod.GET)
	public ApiResponseDto getFestival(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != null) {
			
			List<Festivals> festivals=festivalService.findByUserId(userId);
			
			// boolean ans = festivals.isEmpty(); 
		        if (festivals.isEmpty() != true) { 
		        	 apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage("FESTIVAL_EXISTS").withData(festivals);
		        	 System.out.print("FESTIVALS EXIST");
		        } else {
		        	apiResponseDtoBuilder.withMessage(NO_FESTIVAL_EXISTS).withStatus(HttpStatus.NO_CONTENT); 
		        	System.out.print(" NO FESTIVALS EXIST");
		        }
			
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}
	
}
