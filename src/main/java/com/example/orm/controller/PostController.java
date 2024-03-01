package com.example.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orm.model.Owner;
import com.example.orm.model.Post;
import com.example.orm.repo.OwnerRepo;
import com.example.orm.repo.PostRepo;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private OwnerRepo ownerRepo;
	@Autowired
	private PostRepo postRepo;
	
	@PostMapping("/add/{ownerId}")
	public ResponseEntity<?> addPost(@PathVariable int ownerId, @RequestBody Post post){
		Owner owner = ownerRepo.findById(ownerId).get();
		post.setOwner(owner);
		Post savedPost = postRepo.saveAndFlush(post);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(savedPost);		
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllPosts(){
		List<Post> post = postRepo.findAll();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(post);
		
	}
	@GetMapping("/get/{postId}")
	public ResponseEntity<?> getPost(@PathVariable int postId){
		Post post = postRepo.findById(postId).get();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(post);
		
	}
	@GetMapping("/get/all/{ownerId}")
	public ResponseEntity<?> getAllPost(@PathVariable int ownerId){
		List<Post> posts = postRepo.findAllOwnersPost(ownerId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(posts);
		
	}
	@GetMapping("/list/{page}/{size}")
	public ResponseEntity<?> getPostsPage(@PathVariable int page, @PathVariable int size){
		PageRequest.of(2, 3);
		
//		List<Post> posts = postRepo.findAll(PageRequest.of(page, size)).toList();
		List<Post> posts = postRepo.findAll(
				PageRequest.of(
						page, size, 
						Sort.by("postId").descending()
						)).toList();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(posts);
		
	}
	
}
