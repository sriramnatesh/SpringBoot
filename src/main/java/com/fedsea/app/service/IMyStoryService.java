package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.dto.StoryImageDto;
import com.fedsea.app.dto.UserBdto;
import com.fedsea.app.model.MyStory;
import com.fedsea.app.model.Story;
import com.fedsea.app.model.User;

@Service
public interface IMyStoryService {

//	void addUser(User user, MultipartFile profileImageFile, MultipartFile coverImageFile);
	
	void addStory(MyStory story,List<MultipartFile> storyImageFiles, String string);
	
	 List<Object> findAllProfileImagesUrl(List<Long> usersId);  

	/*
	 * String uploadCoverImage(Long userId,MultipartFile coverImageFile);
	 * 
	 * void updateUser(User user, MultipartFile profileImageFile, MultipartFile
	 * coverImageFile);
	 * 
	 * List<Object> findAllWithCustomObject(List<Long> userId);
	 * 
	 * List<Object> getAllUsernameLike(String username);
	 */
	 
//	 MyStory findByImageUrl(String image_Url);

//	MyStory findByimageUrlContaining(String storyimageName);
}
