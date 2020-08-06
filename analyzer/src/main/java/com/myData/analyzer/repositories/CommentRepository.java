package com.myData.analyzer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myData.analyzer.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
	List<Comment> findByPostId(Long postId);
}
