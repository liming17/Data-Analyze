package com.myData.analyzer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myData.analyzer.repositories.PostRepository;
import com.myData.analyzer.entities.Post;
import com.myData.analyzer.entities.User;

@Service
public class PostService {
    
	@Autowired
    private PostRepository postRepo;
	
	public List<Post> getAllPosts(){
		return postRepo.findAll();
	}
	
	public void insert(Post post) {
		postRepo.save(post);
	}
	
	public List<Post> findByUser(User user){
		return postRepo.findByCreatorId(user.getId());
	}
}
