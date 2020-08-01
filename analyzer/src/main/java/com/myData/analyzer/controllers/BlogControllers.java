package com.myData.analyzer.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myData.analyzer.entities.Post;
import com.myData.analyzer.entities.User;
import com.myData.analyzer.services.PostService;
import com.myData.analyzer.services.UserService;

@RestController
public class BlogControllers {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping(value="/posts")
	public List<Post> posts(){
		return postService.getAllPosts();
	}
	
	
	@PostMapping(value="/post")
	public String publishPost(@RequestBody Post post) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(post.getDateCreated() == null) {
			post.setDateCreated(new Date());
		}
		post.setCreator(userService.getUser(userDetails.getUsername()));
		postService.insert(post);
		return "Posted!";
	}
	
	@GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }
	
	@GetMapping(value = "/post/{username}")
	public List<Post> postsByUsername(@PathVariable String username){
		return postService.findByUser(userService.getUser(username));
	}

}
