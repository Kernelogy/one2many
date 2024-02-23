package com.example.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orm.model.Owner;
import com.example.orm.repo.OwnerRepo;
import com.example.orm.repo.PostRepo;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerRepo ownerRepo;
	@Autowired
	private PostRepo postRepo;
	
	@PostMapping("/add")
	public ResponseEntity<?> addOwner(@RequestBody Owner owner){
		Owner savedEntity = ownerRepo.saveAndFlush(owner);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(owner);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOwner(@PathVariable int id){
		Owner owner = ownerRepo.findById(id).get();

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(owner);
	}

}
