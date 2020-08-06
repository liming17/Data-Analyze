package com.myData.analyzer.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myData.analyzer.entities.Comment;
import com.myData.analyzer.entities.Post;
import com.myData.analyzer.entities.User;
import com.myData.analyzer.pojos.CommentPojo;
import com.myData.analyzer.services.CommentService;
import com.myData.analyzer.services.PostService;
import com.myData.analyzer.services.UserService;

@RestController
public class BlogControllers {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
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
	
//	@GetMapping(value = "/private")
//    public String privateArea(){
//        return "Private area";
//    }
	
//	@GetMapping(value = "/post/{username}")
//	public List<Post> postsByUsername(@PathVariable String username){
//		return postService.findByUser(userService.getUser(username));
//	}
	
	@GetMapping(value="/getMyposts")
    public List<Post> postsByUser(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
        return postService.findByUser(userService.getUser(username));
    }

    @DeleteMapping(value = "/post/{id}")
    public String deletePost(@PathVariable Long id){
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return postService.deletePost(id,username);
    }
    
    @DeleteMapping(value = "/comment/{id}")
    public boolean deleteComment(@PathVariable Long id){
        return commentService.deletePost(id);
    }


    @GetMapping(value = "/comments/{postId}")
    public List<Comment> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @PostMapping(value = "/post/postComment")
    public boolean postComment(@RequestBody CommentPojo comment){
        Post post = postService.find(comment.getPostId());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.getUser(userDetails.getUsername());
        if(post == null || creator == null)
            return false;

        commentService.comment(new Comment(comment.getText(),post,creator));
        return true;
    }

}
