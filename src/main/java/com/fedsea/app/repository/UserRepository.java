package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fedsea.app.dto.UserBdto;
import com.fedsea.app.model.MyStory;
import com.fedsea.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByUsername(String username);
	
	User findByMobileNumber(String parameter);
	
	//User findById(Long userId);
	
	/*
	 * @Query(value="SELECT u.id AS" + UserBdto.id +"," +
	 * "u.full_name AS UserBdto.fullName," + "u.username AS UserBdto.username," +
	 * "u.profile_image_url AS UserBdto.profileimageurl\n" +
	 * " FROM user u WHERE u.id IN (?1)")
	 * 
	 */
	  @Query(value ="SELECT username FROM user WHERE id=?1"  , nativeQuery = true)
	  String findByUserId(Long userId);
	
	
	  @Query(value ="SELECT u.id AS Id ,u.full_name AS fullName,u.username AS username,u.dob AS DOB ,u.profile_image_url AS profileimageurl FROM user u WHERE u.id IN (?1)"
			  , nativeQuery = true) List<Object> findAllWithCustomObject(List<Long> userId);
	  
	 
    
    @Query(value = "\n" +
	  "SELECT u.full_name AS fullName,u.username AS username,u.profile_image_url AS profileimageurl FROM user u WHERE u.username IN (?1)"
	  , nativeQuery = true )List<Object>  getAllUsernameAndProfile_image_url(List<String> username);
	  
	  
    @Query(value = "\n" +
	  "SELECT u.full_name AS fullName,u.username AS username,u.profile_image_url AS profileimageurl FROM user u WHERE u.username LIKE %?1%"
	  , nativeQuery = true )List<Object>  getAllUsernameLike(String username);


	  @Query(value="SELECT u.profile_image_url AS profileimageurl FROM user u WHERE id in(?1)",nativeQuery=true)
	 List<String> findAllProfileImagesUrl(List<Long> usersId);  
	  
	  @Query(value="SELECT u.username AS username FROM user u WHERE id in(?1)",nativeQuery=true)
	 String findUserNameByUserId(Long userId);
	  
	  @Query(value="SELECT u.profile_image_url AS profileimageurl FROM user u WHERE id in(?1)",nativeQuery=true)
	 String findUserProfileImageUrlByUserId(Long userId);
	  
	  @Query(value="SELECT u.cover_image_url AS coverimageurl FROM user u WHERE id in(?1)",nativeQuery=true)
	 String findUserCoverImageUrlByUserId(Long userId);
	
}
