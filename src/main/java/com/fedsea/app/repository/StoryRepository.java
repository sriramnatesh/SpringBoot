package com.fedsea.app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.fedsea.app.model.MyStory;
import com.fedsea.app.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {

/*	

	  @Query(value="SELECT s.user_Id AS UserID ,s.image_Url,s.video_Url AS video_Url ,s.comment,s.Tags AS  Tags FROM story s WHERE s.user_Id in(?1) AND story_date >= now() - INTERVAL 1 DAY;",nativeQuery=true)
	 List<Object> findAllProfileImagesUrl(List<Long> usersId);  
	
	  @Query(
			  value="SELECT id,user_Id,comment,image_Url,video_Url,story_Type,feed_Type,Tags,story_date FROM story WHERE image_Url LIKE %:keyword%" ,nativeQuery= true) 
	  Object[] findMyStoryWithPartOfName(@Param("keyword")String ImageUrl);

	  @Query(value="SELECT s.image_url FROM story s WHERE s.user_id = ?1",nativeQuery= true)
	  List<String> findImageUrlByUserId(Long Id);

	  @Query(value = "SELECT ue.user_id FROM story ue WHERE ue.image_Url LIKE %:imageUrl%", nativeQuery = true)
	  Long findUserIdByImageUrl(@Param("imageUrl") String imageUrl);

	 @Query(value="SELECT s.id FROM story s WHERE s.user_id = ?1",nativeQuery= true) 
	 Long findIdByUserId(Long userId);

	*/
	
	/*
	 * @Query("UPDATE story SET image_Url = :imageurl WHERE user_Id = :userId"
	 * ,nativeQuery= true) void setImageUrl(@Param("imageurl") String
	 * Url,@Param("userId") Long id);
	 */
	 
	 
}
