package com.fedsea.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.dto.UserNotification;
import com.fedsea.app.model.MyStory;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IFriendService;
import com.fedsea.app.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final String USER_ADDED_SUCCESSFULLY = "User added successfully.";

	private static final String USER_ALREADY_EXISTS = "User Already Exists";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String INCORRECT_PASSWORD = "Incorrect Password";

	private static final String PASSWORD_CHANGED = "Password Changed";
	

	@Value("${file.upload-dir}")
	private String imagePath;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IUserService userService;

	@Autowired
	private IFriendService friendService;

	// =========================Api Method For Add User
	// Information==============================

	@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addUserInformation(@RequestParam(required = false) MultipartFile profileImageFile,
			@RequestParam(required = false) MultipartFile coverImageFile, @RequestParam("user") String userData) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(userData);
			User user = mapper.treeToValue(node, User.class);
			User userCheck = userRepository.findByUsername(user.getUsername());
			if (userCheck == null) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				userService.addUser(user, profileImageFile, coverImageFile);
			}
			if (userCheck != null) {
				apiResponseDtoBuilder.withMessage(USER_ALREADY_EXISTS).withStatus(HttpStatus.ALREADY_REPORTED);
			} else if (user.getId() != null) {
				apiResponseDtoBuilder.withMessage(USER_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK).withData(user);
			} else {
				apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST)
					.withData(" Error Appeared, Check Images Selected");
		}

		return apiResponseDtoBuilder.build();
	}

	// =========================Api Method For Update User
	// Information==============================
	@RequestMapping(value = "/v1/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto updateUserInformation(@PathVariable Long userId,
			@RequestParam(required = false) MultipartFile profileImageFile,
			@RequestParam(required = false) MultipartFile coverImageFile, @RequestParam("user") String userData) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(userData);
			User user = mapper.treeToValue(node, User.class);
			if (userId != 0) {
				Optional<User> dbUser = userRepository.findById(userId);
				if (dbUser.isPresent()) {
					// dbUser.get().setId(user.getId());
					dbUser.get().setType(user.getType());
					dbUser.get().setMobileNumber(user.getMobileNumber());
					dbUser.get().setEmail(user.getEmail());
					dbUser.get().setFullName(user.getFullName());
					dbUser.get().setUsername(user.getUsername());
					dbUser.get().setDob(user.getDob());
					dbUser.get().setTags(user.getTags());
					dbUser.get().setLocation(user.getLocation());
					dbUser.get().setBio(user.getBio());
					userService.updateUser(dbUser.get(), profileImageFile, coverImageFile);
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(dbUser.get());
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

	// =========================Api Method For Get User By Name
	// Information==============================
	@RequestMapping(value = "/v1/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getUserInformation(@PathVariable String username) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		User user = userRepository.findByUsername(username);
		if (user != null) {
			apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(user);
		} else {
			apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}

	// =========================Api Method For Is Existing
	// User==============================
	@RequestMapping(value = "/v1/user/exists/{parameter}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto isExistingUser(@PathVariable String parameter) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();

		User user = new User();
		if (parameter.contains("@")) {
			user = userRepository.findByEmail(parameter);
		} else {
			user = userRepository.findByMobileNumber(parameter);
		}
		if (user != null) {
			apiResponseDtoBuilder.withMessage(USER_ALREADY_EXISTS).withStatus(HttpStatus.ALREADY_REPORTED);
		} else {
			apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}

	@RequestMapping(value = "/v1/user/{userId}/notify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto notification(@PathVariable String userID, @RequestBody UserNotification userNotification) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		System.out.println(userID);
		apiResponseDtoBuilder.withMessage(USER_ALREADY_EXISTS).withStatus(HttpStatus.ALREADY_REPORTED);
		return apiResponseDtoBuilder.build();
	}

	// =========================Api Method For Get  Profile Image by UserId

	@RequestMapping(value = "/image/{userId}/profile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getprofileImage(@PathVariable("userId") Long userId) {
		byte[] image = null;
		if(userId!=null) {
			//String Uname=userService.GetUserNameByUserId(userId);	
			String imageUrl=userService.findUserProfileImageUrlByUserId(userId);	
			if(!imageUrl.isEmpty()) {
				try {  //sajjankumar6/coverprofile/sajjankumar6_profile.png
					image = Files.readAllBytes(Paths.get(imagePath +imageUrl));
				} catch (IOException e) {
					e.printStackTrace();
				}
				/*
				 * try { //sajjankumar6/coverprofile/sajjankumar6_profile.png image =
				 * Files.readAllBytes(Paths.get(imagePath
				 * +"/users"+"/"+Uname+"/"+"coverprofile"+ Uname+"_profile.png")); } catch
				 * (IOException e) { e.printStackTrace(); }
				 */
			}else {				
				try { // users/generalPhoto/men
					image = Files.readAllBytes(Paths
							.get(imagePath + "/"+"users" + "/" + "general"+ "/" + "men" + "/" + "men.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}	 
			}	
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
	
	// =========================Api Method For Get  Cover Image by UserId
		
	@RequestMapping(value = "/image/{userId}/cover", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCoverImage(@PathVariable("userId") Long userId) {
		byte[] image = null;
		if(userId!=null) {
			//String Uname=userService.GetUserNameByUserId(userId);	
			String imageUrl=userService.findUserCoverImageUrlByUserId(userId);	
			if(!imageUrl.isEmpty()) {
				try {  //sajjankumar6/coverprofile/sajjankumar6_profile.png
					image = Files.readAllBytes(Paths.get(imagePath +imageUrl));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {				
				try { // users/generalPhoto/men
					image = Files.readAllBytes(Paths
							.get(imagePath + "/"+"users" + "/" + "general"+ "/" + "no-info-icon.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}	 
			}	
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
	
	@RequestMapping(value = "/v1/change/password/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto changePassword(@RequestParam(required = true) String oldPassword,
			@RequestParam(required = true) String newPassword, @PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			Optional<User> dbuser = userRepository.findById(userId);
			if (dbuser.isPresent()) {
				if (bCryptPasswordEncoder.matches(oldPassword, dbuser.get().getPassword())) {
					dbuser.get().setPassword(bCryptPasswordEncoder.encode(newPassword));
					userRepository.save(dbuser.get());
					apiResponseDtoBuilder.withMessage(PASSWORD_CHANGED).withStatus(HttpStatus.OK);
				} else {
					apiResponseDtoBuilder.withMessage(INCORRECT_PASSWORD).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
		}

		return apiResponseDtoBuilder.build();
	}

	/*
	 * Add cover Images
	 * 
	 * 
	 */
	@RequestMapping(value = "/v1/user/image/cover", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addecoverImageFile(@RequestParam(required = true) Long userId,
			@RequestParam(required = true) MultipartFile coverImageFile) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();

		if (userId != 0) {
			
			String imageUrl=userService.findUserCoverImageUrlByUserId(userId);
			
			if(!imageUrl.isEmpty()) {
				File file = new File(imagePath+imageUrl);
				if(file.delete()){
				    System.out.println(imageUrl +" File deleted");
				}else System.out.println("File "+imageUrl+" doesn't exist");
			}
			
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				try {
				
					userService.uploadImage(dbUser.get().getId(), coverImageFile,1);// 1 =cover ,2 profile ,3=single image story
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(dbUser.get());
				} catch (Exception e) {
					apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST)
							.withData(" Error Appeared, Please try again");
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		}else apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);


		return apiResponseDtoBuilder.build();
	}

	/*
	 * Add profile Images
	 * 
	 */
	@RequestMapping(value = "/v1/user/image/profile", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addprofileImageFile(@RequestParam(required = true) Long userId,
			@RequestParam(required = true) MultipartFile profileImageFile) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();

		if (userId != 0) {
			String imageUrl=userService.findUserProfileImageUrlByUserId(userId);
			if(!imageUrl.isEmpty()) {
				File file = new File(imageUrl);
				if(file.delete()){
				    System.out.println(imageUrl +" File deleted");
				}else System.out.println("File "+imageUrl+" doesn't exist");
			}
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				try {				
					userService.uploadImage(dbUser.get().getId(), profileImageFile,2);// 1 =cover ,2= profile ,3=single image story
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(dbUser.get());
				} catch (Exception e) {
					apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST)
							.withData(" Error Appeared, Please try again");
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		}else apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
		return apiResponseDtoBuilder.build();
	}

	/* get all profile */
	@RequestMapping(value = "/image/{userId}/imageurl", method = RequestMethod.GET)
	public ApiResponseDto getfriendsProfileImageUrl(@PathVariable("userId") Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			List<Long> connectedUsers = friendService.findAllFriends(userId);
			if (!connectedUsers.isEmpty()) {
				List<String> friendsprofileimgurl = userService.getAllProfileImagesUrl(connectedUsers);
				if (!friendsprofileimgurl.isEmpty()) {
					apiResponseDtoBuilder.withMessage("Success").withStatus(HttpStatus.OK)
							.withData(friendsprofileimgurl);
				} else {
					apiResponseDtoBuilder.withMessage("Friend Profile Images not Available")
							.withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(" NO Connected Friends").withStatus(HttpStatus.NO_CONTENT);
			}

		} else {
			apiResponseDtoBuilder.withMessage("Invalid UserId").withStatus(HttpStatus.NO_CONTENT);

		}

		/*
		 * byte[] image = null; try { image = Files.readAllBytes(Paths.get(imagePath +
		 * "/users/" + imageName)); } catch (IOException e) { e.printStackTrace(); }
		 */

		return apiResponseDtoBuilder.build();
	}

}