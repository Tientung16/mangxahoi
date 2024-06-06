package com.ivn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivn.models.Comment;
import com.ivn.models.User;
import com.ivn.service.CommentService;
import com.ivn.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/post/{postId}")
	public Comment createComment(
			@RequestBody Comment comment, 
			@RequestHeader("Authorization") String jwt,
			@PathVariable("postId") Integer postId) throws Exception{
		
		User user = userService.findUserByJwt(jwt);
		
		Comment createComment = commentService.createComment(
				comment, 
				postId, 
				user.getId());	
		
		return createComment;
	}
	
	@PutMapping("/like/{commentId}")
	public Comment likeComment(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception{
		
		User user = userService.findUserByJwt(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
	}
}
