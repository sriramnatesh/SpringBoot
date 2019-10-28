package com.fedsea.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.model.MyStory;
import com.fedsea.app.model.Story;
import com.fedsea.app.repository.MyStoryRepository;
import com.fedsea.app.repository.StoryRepository;
import com.fedsea.app.service.IFriendService;
import com.fedsea.app.service.IMyStoryService;
import com.fedsea.app.service.IUserService;

@RestController
@RequestMapping("/api/story")
public class MyStoryController {

	private static final String IMAGES_ADDED_SUCCESSFULLY = "Images uploaded successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String NO_IMAGE_EXISTS = "No Image Exists";


	@Value("${file.upload-dir}")
	private String imagePath;
	
	@Autowired
	private IUserService userService;
	
	
	@Autowired 
	private IMyStoryService myStoryService;
	
	@Autowired
	private IFriendService friendService;
	
	
	@Autowired
	private MyStoryRepository storyrepo;

	
	@Autowired
	private StoryRepository storirepo;
	
	

	/**
	 *  API for Add Stories ,multiple Images
	 * @throws IOException 
	 * 
	 * **/
	
	
	@RequestMapping(value = "/user/images", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addStoryImages(@RequestParam("story") String userStory,@RequestParam(required = false)List<MultipartFile>  StoryImageFiles) throws IOException {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {	
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(userStory);
			MyStory story = mapper.treeToValue(node, MyStory.class);
			apiResponseDtoBuilder.withMessage(IMAGES_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK);
	
			if (story.getUserId() != null) {	
				String userName=userService.findUsername(story.getUserId());
				if(userName!=null) {					
					myStoryService.addStory(story, StoryImageFiles,userName);
					apiResponseDtoBuilder.withMessage(IMAGES_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK); 
					}
				else apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus. ALREADY_REPORTED);
			}

		} catch (JpaSystemException e) {
			apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST)
					.withData(" Error Appeared, Please try again");
			System.out.println("Exception " +e);
		}

		return apiResponseDtoBuilder.build();
	}

	/**
	 *  API for Get Image files Id (Story Images)//TODO dto conversion
	 * 
	 * **/
	/* get all profile */

	@RequestMapping(value = "/image/{userId}/imageurl", method = RequestMethod.GET)
	public ApiResponseDto getfriendsStoryImagesUrl(@PathVariable("userId") Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			List<Long> connectedUsers = friendService.findAllFriends(userId);
			if (!connectedUsers.isEmpty()) {
				
				List<Object> friendsstoriesimgurl= myStoryService.findAllProfileImagesUrl(connectedUsers);
						
						//userService.getAllProfileImagesUrl(connectedUsers);
				
				if (!friendsstoriesimgurl.isEmpty()) {
					apiResponseDtoBuilder.withMessage("Success").withStatus(HttpStatus.OK)
							.withData(friendsstoriesimgurl);
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
		return apiResponseDtoBuilder.build();
	}
	
	/**
	 *  API for Get Story Image by Story file Id //todo
	 * 
	 * **/

	@RequestMapping(value = "/image/{userId}/{storyimageName}/stories", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable("storyimageName") String storyimageName,@PathVariable("userId") Long userId) {
		byte[] image = null;
		String username=userService.GetUserNameByUserId(userId);
		if(username!=null) {
			try {
				image = Files.readAllBytes(Paths.get(imagePath + "/users/" +username+"/stories/"+ storyimageName));
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

	/**
	 *  API for Delete Story Image by Story file Id and User ID
	 * 
	 * **/

	@RequestMapping(value = "/image/{userId}/{storyimageName}/delete", method = RequestMethod.DELETE)
	public ApiResponseDto deleteStoryImage(@PathVariable("userId") Long userId,@PathVariable("storyimageName") String storyimageName) {
		 ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		 if(userId!=null) {
			 String username=userService.GetUserNameByUserId(userId);
			 if(username!=null) {
				 Long id=storyrepo.findIdByImageUrl(storyimageName);
				 if(id!=null) {
				// Optional<MyStory> mystory=storyrepo.findById(id);
				// MyStory stor=storyrepo.findByIdAndImageUrl(storyimageName, id);
				 List<String> imageurls=storyrepo.findImageUrlByUserId(id);
				// imageurls=mystory.get().getImageUrl();
				 List<String> upimageurls = new ArrayList<String>();
				
				 for(String str: imageurls) {
					 if(str.trim().contains(storyimageName)) {
						// imageurls.remove(index);
						 File file = new File(imagePath + "/users/" + username + "/stories/" + storyimageName);
						 if (file.delete()) {
							 System.out.println(storyimageName + " File deleted");
						 } else
							 System.out.println("File " + storyimageName + " doesn't exist");
						 apiResponseDtoBuilder.withMessage("Image Exists,Deleted").withStatus(HttpStatus.OK);
					 }else
						 apiResponseDtoBuilder.withMessage(NO_IMAGE_EXISTS).withStatus(HttpStatus.NO_CONTENT);
				 }
					MyStory newstory =storyrepo.getOne(id);				
					String inputString = null;
					for ( int i = 0;  i < imageurls.size(); i++){
						inputString=imageurls.get(i);					
					}
					String[] arraySplit_3 = inputString.split(",");
					for (int i=0; i < arraySplit_3.length; i++){
					  //  System.out.println(arraySplit_3[i]);
					    if(!(arraySplit_3[i].equals(storyimageName))) {
					    	System.out.println(arraySplit_3[i]);
					    	upimageurls.add(arraySplit_3[i]);
						  }				 	   
					}
				//	mystory.get().setImageUrl(upimageurls);
				//	storyrepo.save(mystory.get());					
					//newstory.setId(id);
					newstory.setImageUrl(upimageurls);
					storyrepo.save(newstory);
			     }else apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);	
			 }else apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
		 }else apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
		return apiResponseDtoBuilder.build();
	} 

}

