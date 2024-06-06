package com.ivn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivn.models.User;
import com.ivn.repository.UserRepository;
import com.ivn.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public List<User> getUser() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception{
		
		User user = userService.findUserById(id);
		
		return user;
	}
	
	
	@PutMapping("/update")
	public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updateUser = userService.updateUser(user, reqUser.getId());
		
		return updateUser;
	}
	
	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {
		
		Optional<User> user = userRepository.findById(userId);
		
		if (user.isEmpty()) {
			throw new Exception("user not exit with id" + userId);
		}
		
		userRepository.delete(user.get());
		
		return "user deleted successfully with id" + userId;
	}
	
	@PutMapping("/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user = userService.followUser(reqUser.getId(), userId2);
		
		return user;
	}
	
	@GetMapping("/search")
	public List<User> searchUser(@RequestParam("query") String query ){
		List<User> users = userService.seatchUser(query);
		
		return users;
	}
	
	@GetMapping("/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		
		return user;
	}
}
