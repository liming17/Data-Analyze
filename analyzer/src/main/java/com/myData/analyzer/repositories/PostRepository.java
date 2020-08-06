package com.myData.analyzer.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myData.analyzer.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
	List<Post> findByCreatorId(Long id);
	
	 @Query(value="SELECT * FROM POST P WHERE P.TITLE LIKE CONCAT('%',:searchText,'%')"+
	 "OR P.BODY LIKE CONCAT('%',:searchText,'%')",nativeQuery = true)
	 Collection<Post> searchPosts(@Param("searchText") String searchText);

}
