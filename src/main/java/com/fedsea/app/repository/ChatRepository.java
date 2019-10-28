package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fedsea.app.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	Chat findByChatId(Long chatId);

	List<Chat> findBySenderIdAndReceiverId(Long userId, Long receiverId);

}
