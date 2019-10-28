
package com.fedsea.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Chat;
import com.fedsea.app.repository.ChatRepository;

@Service
public class ChatServiceImpl implements IChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public void addChat(Long userId, Chat chat) {
		chat.setSenderId(userId);
		chatRepository.save(chat);
	}

	@Override
	public void burnChat(Long userId, Chat chat) {
		chat.setBurned(true);
		chat.setRead(true);
		chat.setMessage("");
		chatRepository.save(chat);
	}
}
