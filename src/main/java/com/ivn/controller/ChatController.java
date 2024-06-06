package com.ivn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivn.models.Chat;
import com.ivn.models.User;
import com.ivn.request.CreateChatRequest;
import com.ivn.service.ChatService;
import com.ivn.service.UserService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public Chat createChat (
			@RequestHeader("Authorization") String jwt,
			@RequestBody CreateChatRequest req) throws Exception {
		
		User requser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(requser, user2);
		
		return chat;
		
	}

	@GetMapping("/")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		List<Chat> chats = chatService.findUserChat(user.getId());
		
		return chats;
		
	}
	
}
