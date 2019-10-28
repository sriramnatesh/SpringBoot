
package com.fedsea.app.service;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Chat;

@Service
public interface IChatService {

	void addChat(Long userId, Chat chat);

	void burnChat(Long userId, Chat chat);

}
