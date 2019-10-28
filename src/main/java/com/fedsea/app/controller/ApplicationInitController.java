package com.fedsea.app.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.model.LoggedTime;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.dto.RecentlogindetsDto;
import com.fedsea.app.service.ILoggedTimeService;




@RestController
@RequestMapping("/api/device")
public class ApplicationInitController {
	

	@Autowired
	private ILoggedTimeService loggedTimeService;

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * ==================================== Api Method recent logins from devices
	 * Request==============================
	 */
	
	@RequestMapping(value = "/uniqid", method = RequestMethod.POST)
	public ApiResponseDto getuniqid(@RequestBody String uniqid) throws AuthenticationException {

	//	System.out.println(uniqid);
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			 ObjectMapper mapper = new ObjectMapper();
			 LoggedTime ltime = mapper.readValue(uniqid, LoggedTime.class);
			 System.out.println("uniqid Value = " + ltime.getUniqid());
			 System.out.println(loggedTimeService.findDistinctByUniqid(ltime.getUniqid()));
		//	 userRepository.retrieveUsernameAsDTO("sijo1");
			// userRepository.getAllUsernameAndProfile_image_url(loggedTimeService.findDistinctByUniqid(ltime.getUniqid()));
			 List<Object> list = userRepository.getAllUsernameAndProfile_image_url(loggedTimeService.findDistinctByUniqid(ltime.getUniqid()));
		
			
			  for (int i=0; i<list.size(); i++){ Object[] row = (Object[]) list.get(i);
			  System.out.println(Arrays.toString(row)); }
			// @SuppressWarnings("unchecked")
		//	 List<RecentlogindetsDto> classlist = (List<RecentlogindetsDto>)(List<?>)getObjects(list);
			 
			/*
			 * @SuppressWarnings("unchecked") List<RecentlogindetsDto> llist
			 * =(List<RecentlogindetsDto>)(List<?>)list; //
			 * 
			 */ 
			 apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage("available").withData(list);
					

		} catch (Exception e) {
			System.out.println("uniqid Value = " + e);
			apiResponseDtoBuilder.withStatus(HttpStatus.UNAUTHORIZED).withMessage("unique id not available with db");
		}
		return apiResponseDtoBuilder.build();
	}

}
