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

import com.fedsea.app.dto.UserBdto;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.UserRepository;

@Service("userService")
public class UserServiceImp implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileStorageService fileStorageService;

	@Override
	public void addUser(User user, MultipartFile profileImageFile, MultipartFile coverImageFile) {

		try {
			if(!profileImageFile.isEmpty()) {
			String profileImageUrl = fileStorageService.storeFile(profileImageFile, user.getUsername(), "profile","users");
			user.setProfileImageUrl(profileImageUrl);
		}
			if (!coverImageFile.isEmpty()) {
			String coverImageUrl = fileStorageService.storeFile(coverImageFile, user.getUsername(), "cover", "users");
			user.setCoverImageUrl(coverImageUrl);
		}
		}catch(Exception ex){
			System.out.print("Could not set Url "+ex);
		};
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user, MultipartFile profileImageFile, MultipartFile coverImageFile) {
	
	
		try {

			if (profileImageFile != null) {
				String profileImageUrl = fileStorageService.storeFile(profileImageFile, user.getUsername(), "profile",
						"users");
				user.setProfileImageUrl(profileImageUrl);
			}
			if (coverImageFile != null) {
				String coverImageUrl = fileStorageService.storeFile(coverImageFile, user.getUsername(), "cover",
						"users");
				user.setCoverImageUrl(coverImageUrl);
			}
		} catch (Exception ex) {
		}
		
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public List<Object> findAllWithCustomObject(List<Long> userId) {
		
		return userRepository.findAllWithCustomObject(userId);
	}

	@Override
	public List<Object> getAllUsernameLike(String username) {
		
		return userRepository.getAllUsernameLike(username);
	}

	@Override
	public void uploadImage(Long userId,MultipartFile ImageFile,int type) {// 1 =cover ,2 profile ,3=single image story
		User user=userRepository.getOne(userId);

		try {
			if (!ImageFile.isEmpty()) {
				String ImageUrl;
				switch(type){
				case 1:
					ImageUrl = fileStorageService.storeFile(ImageFile, user.getUsername(), "cover", "users");
					user.setCoverImageUrl(ImageUrl);
					break;
				case 2:
					ImageUrl = fileStorageService.storeFile(ImageFile, user.getUsername(), "profile", "users");
					user.setProfileImageUrl(ImageUrl);
					break;
				}
		}
		}catch(Exception ex){
			System.out.print("Could not set Url "+ex);
		};
		userRepository.save(user);

	}

	@Override
	public String findUsername(Long Id) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(Id);
	}

	@Override
	public List<String> getAllProfileImagesUrl(List<Long> usersId) {
		// TODO Auto-generated method stub
		return userRepository.findAllProfileImagesUrl(usersId);
	}

	@Override
	public String GetUserNameByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findUserNameByUserId(userId);
	}

	@Override
	public String findUserProfileImageUrlByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findUserProfileImageUrlByUserId(userId);
	}

	@Override
	public String findUserCoverImageUrlByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findUserCoverImageUrlByUserId(userId);
	}

	
	
	
}
