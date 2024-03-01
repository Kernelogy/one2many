package com.example.orm.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.orm.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{
	@Query(value = "SELECT * FROM posts WHERE owner_id = :ownerId", nativeQuery = true)
	List<Post> findAllOwnersPost(int ownerId);
	
}
