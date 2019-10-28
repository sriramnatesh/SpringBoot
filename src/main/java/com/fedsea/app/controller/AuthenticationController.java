package com.fedsea.app.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fedsea.app.config.JwtTokenUtil;
import com.fedsea.app.constants.AuthorizationConstants;
import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.dto.LoginUser;
import com.fedsea.app.model.LoggedTime;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.LoggedTimeRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.ILoggedTimeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private static final String TOKEN = "token";

	private static final String USER = "user";

	private static final String LOGINEDUSER = "user_details";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ILoggedTimeService loggedTimeService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResponseDto register(@RequestBody LoginUser loginUser) throws AuthenticationException {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			
			  authenticationManager.authenticate( new
			  UsernamePasswordAuthenticationToken(loginUser.getUsername(),
			  loginUser.getPassword()));
			 
			final UserDetails user = userDetailsService.loadUserByUsername(loginUser.getUsername());
			final String token = jwtTokenUtil.generateToken(user);
			User userDetails = userRepository.findByUsername(loginUser.getUsername());
			
			Map<String, Object> response = setTokenDetails(user, token, userDetails);
			apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage(AuthorizationConstants.LOGIN_SUCESSFULL).withData(response);
			
			LoggedTime loggedTime=new LoggedTime();
			loggedTime.setUsername(loginUser.getUsername());
			loggedTime.setUniqid(loginUser.getUniqid());
			if(loginUser.getUniqid()==null) {
				apiResponseDtoBuilder.withStatus(HttpStatus.UNAUTHORIZED).withData(null);
			}else {
			    //Convert the time into UTC and build Timestamp object.//TODO add service implementation
			    Timestamp ts = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
				loggedTime.setLoggedtime(ts);
				loggedTimeService.save(loggedTime);
			}

		} catch (Exception e) {
			apiResponseDtoBuilder.withStatus(HttpStatus.UNAUTHORIZED).withMessage("Invalid Credential");
		}

		return apiResponseDtoBuilder.build();
	}

	private Map<String, Object> setTokenDetails(final UserDetails user, final String token, final User userDetails) {
		Map<String, Object> response = new HashMap<>();
		response.put(USER, user);
		response.put(TOKEN, token);
		response.put(LOGINEDUSER, userDetails);
		return response;
	}
}
