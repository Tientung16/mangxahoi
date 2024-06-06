package com.ivn.service;

import java.util.List;

import com.ivn.models.Chat;
import com.ivn.models.Message;
import com.ivn.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req ) throws Exception;
	
	public List<Message> findChatsMessages (Integer chatId) throws Exception;
	
}
