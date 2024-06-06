package com.ivn.service;

import java.util.List;

import com.ivn.models.Reels;
import com.ivn.models.User;

public interface ReelsService {
	public Reels createReel (Reels reel, User user) throws Exception;
	
	public List<Reels> findAllReels() throws Exception;
	
	public List<Reels> findUserReel (Integer userId)throws Exception;
	
}
