package com.example.orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orm.model.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer>{

}
