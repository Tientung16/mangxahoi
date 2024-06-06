package com.ivn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivn.models.Reels;
import com.ivn.models.User;
import com.ivn.service.ReelsService;
import com.ivn.service.UserService;

@RestController
@RequestMapping("/api/reels/")
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public Reels createReels(
			@RequestBody Reels reel,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reels createReels = reelsService.createReel(reel, reqUser);
		
		return createReels;
	}
	
	@GetMapping("/")
	public List<Reels> findAllReels() throws Exception{
		
		List<Reels> reels = reelsService.findAllReels();
		
		return reels;
	}
	
	@GetMapping("/user/{userId}")
	public List<Reels> findUsersReels(
			@PathVariable Integer userId) throws Exception{
		
		List<Reels> reels = reelsService.findUserReel(userId);
		
		return reels;
	}
	
}
