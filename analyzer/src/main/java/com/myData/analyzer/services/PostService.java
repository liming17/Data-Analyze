package com.myData.analyzer.services;

import java.util.List;
import java.util.Optional;

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
		postRepo.saveAndFlush(post);
	}
	
	public List<Post> findByUser(User user){
		return postRepo.findByCreatorId(user.getId());
	}
	
    public String deletePost(Long id, String username){
	    Optional<Post> post = postRepo.findById(id);
    	Post thePost = post.orElse(null);
	    if(thePost == null) {
	    	return "Post not found!";
	    }else if(!thePost.getCreator().getUsername().equals(username)) {
	    	return "Username error!";
	    }else {
		    postRepo.deleteById(id);
		    return "Post deleted!";
	    }
	}

	public Post getPost(Long id) {
		Optional<Post> post = postRepo.findById(id);
	    return post.orElse(null);
	}

	public List<Post> searchPosts(String searchText){
		 return (List<Post>) postRepo.searchPosts(searchText);
	}
}
