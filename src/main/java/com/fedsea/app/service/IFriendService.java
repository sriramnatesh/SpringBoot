package com.fedsea.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Friend;

@Service
public interface IFriendService {

	void sendRequest(Long userId, Friend friend);

	void connect(Friend dbFriend);

	void unConnect(Friend dbFriend);

	List<Long> findByRequestIdAndSenderIdAndStatus(Long userId,Integer status);
	
	List<Long> getConnectNotification(Long userId,Integer status); 
	
	List<Long> findAllFriends(Long userId);
	
	
	
}
