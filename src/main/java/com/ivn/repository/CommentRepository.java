package com.ivn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivn.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
