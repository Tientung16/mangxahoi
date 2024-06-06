package com.ivn.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivn.models.Reels;
import com.ivn.models.User;
import com.ivn.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService{

	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReel(Reels reel, User user) throws Exception {
		
		Reels createReel = new Reels();
		
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		createReel.setCreatedAt(LocalDateTime.now());
		
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() throws Exception {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReel(Integer userId) throws Exception {
		
		userService.findUserById(userId);
		
		return reelsRepository.findByUserId(userId);
		
	}

}
