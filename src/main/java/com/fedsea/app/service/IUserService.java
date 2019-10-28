package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.dto.UserBdto;
import com.fedsea.app.model.User;

@Service
public interface IUserService {

	void addUser(User user, MultipartFile profileImageFile, MultipartFile coverImageFile);
	
	void uploadImage(Long userId,MultipartFile ImageFile,int type);

	void updateUser(User user, MultipartFile profileImageFile, MultipartFile coverImageFile);

	List<Object> findAllWithCustomObject(List<Long> userId);
	
	List<Object>  getAllUsernameLike(String username);
	
	String findUsername(Long Id);
	
	List<String> getAllProfileImagesUrl(List<Long> usersId);  
	
	String GetUserNameByUserId(Long userId);
	
    String findUserProfileImageUrlByUserId(Long userId);
    
    String findUserCoverImageUrlByUserId(Long userId);
}
