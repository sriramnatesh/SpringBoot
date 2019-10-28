package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	List<Friend> findBySenderId(Long userId);

	Friend findBySenderIdAndRequestId(Long userId, Long requestId);

	List<Friend> findByRequestId(Long userId);
	
	  @Query(value =
		"SELECT  sender_id AS friend FROM friends WHERE  request_id =?1 AND  status=?2\n" + 
		"UNION DISTINCT\n" + 
		"SELECT  request_id AS friend FROM  friends WHERE  sender_id=?1 AND  status=?2 \n" + 
		"\n" + 
		""
	  , nativeQuery = true)
	List<Long> findByRequestIdAndStatus(Long userId,Integer status);
	

	  @Query(value =
		"SELECT DISTINCT sender_id AS friend FROM friends WHERE  request_id =?1 AND  status=?2\n" + 
		"\n" + 
		""
	  , nativeQuery = true)
	List<Long> getALLNOTIFICATIONBySenderIdAndStatus(Long userId,Integer status);  
	  
	  @Query(value="SELECT  sender_id AS friend FROM friends WHERE  request_id = ?1 AND  status=1 "
	  			   + "UNION DISTINCT "
	  		       + "SELECT  request_id AS friend FROM  friends WHERE  sender_id=?1 AND  status=1",nativeQuery=true)
	List<Long> findAllFriends(Long userId);
	  
}
