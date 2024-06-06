package com.ivn.service;

import java.util.List;

import com.ivn.models.Chat;
import com.ivn.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2) throws Exception;
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUserChat(Integer userId) throws Exception;
	
}
