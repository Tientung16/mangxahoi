package com.ivn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivn.models.Post;
import com.ivn.models.User;
import com.ivn.response.ApiResponse;
import com.ivn.service.PostService;
import com.ivn.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Post> createPost(
			@RequestHeader("Authorization") String jwt,
			@RequestBody Post post)throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(
			@PathVariable Integer postId, 
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message, true);
		
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId)throws Exception{
		
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception{
		
		List<Post> posts = postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> findAllPost() throws Exception{
		
		List<Post> posts = postService.findAllPost();
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@PutMapping("/save/{postId}")
	public ResponseEntity<Post> savedPostHandler(
			@PathVariable Integer postId, 
			@RequestHeader("Authorization") String jwt)throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post post = postService.savedPost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	} 
	
	@PutMapping("/like/{postId}")
	public ResponseEntity<Post> likePostHandler(
			@PathVariable Integer postId, 
			@RequestHeader("Authorization") String jwt)throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post post = postService.likePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	} 
	
}