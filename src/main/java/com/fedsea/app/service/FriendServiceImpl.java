package com.fedsea.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Friend;
import com.fedsea.app.repository.FriendRepository;

@Service
public class FriendServiceImpl implements IFriendService {

	@Autowired
	private FriendRepository friendRepository;

	@Override
	public void sendRequest(Long userId, Friend friend) {
		friend.setSenderId(userId);
		friendRepository.save(friend);
	}

	@Override
	public void connect(Friend friend) {
		friend.setStatus(1);
		friend.setAcceptedDate(new Date());
		friendRepository.save(friend);
	}

	@Override
	public void unConnect(Friend friend) {
		friend.setStatus(2);
		friend.setRejectedDate(new Date());
		friendRepository.save(friend);
	}

	@Override
	public	List<Long> findByRequestIdAndSenderIdAndStatus(Long userId,Integer status) {
		// TODO Auto-generated method stub
		return friendRepository.findByRequestIdAndStatus(userId, status);
	}

	@Override
	public List<Long> getConnectNotification(Long userId, Integer status) {
		// TODO Auto-generated method stub
		return friendRepository.getALLNOTIFICATIONBySenderIdAndStatus(userId, status);
	}

	@Override
	public List<Long> findAllFriends(Long userId) {
		// TODO Auto-generated method stub
		return friendRepository.findAllFriends(userId);
	}
	
	

}
