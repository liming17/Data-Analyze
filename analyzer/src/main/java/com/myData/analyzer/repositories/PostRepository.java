package com.myData.analyzer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myData.analyzer.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
	List<Post> findByCreatorId(Long id);

}
