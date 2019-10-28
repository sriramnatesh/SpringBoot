package com.fedsea.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.dto.StoryImageDto;
import com.fedsea.app.dto.UserBdto;
import com.fedsea.app.model.MyStory;
import com.fedsea.app.model.Story;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.MyStoryRepository;
import com.fedsea.app.repository.StoryRepository;
import com.fedsea.app.repository.UserRepository;

@Service("storyService")
public class MyStoryServiceImpl implements IMyStoryService {

	

	@Autowired
	private FileStorageService fileStorageService;


	@Autowired
	private MyStoryRepository storyRepository;
	
	@Autowired
	private StoryRepository story1Repository;

	@Override
	public void addStory(MyStory story, List<MultipartFile> StoryImageFiles,String username) {
		
		try {
			if(StoryImageFiles!=null) {
			   List<String> profileImageUrl = fileStorageService.storeFiles(StoryImageFiles, story.getUserId(),username, "story","users");
	
			   if(!profileImageUrl.isEmpty()) {
				   story.setImageUrl(profileImageUrl);
				   }
			}

		}catch(Exception ex){
			System.out.println("Exception " +ex);
		};	
		storyRepository.save(story);
		
	}


	
	  @Override public List<Object> findAllProfileImagesUrl(List<Long> usersId) {
	  return storyRepository.findAllProfileImagesUrl(usersId); }
	 

	/*
	 * @Override public MyStory findByImageUrl(String image_Url) {
	 * 
	 * return storyRepository.findByImageUrl(image_Url); }
	 */


	/*
	 * @Override public MyStory findByimageUrlContaining(String storyimageName) { //
	 * TODO Auto-generated method stub return
	 * storyRepository.findByimageUrlContaining(storyimageName); }
	 * 
	 */
	

	
}
