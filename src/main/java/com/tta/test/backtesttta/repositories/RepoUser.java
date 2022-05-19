package com.tta.test.backtesttta.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tta.test.backtesttta.entities.User;

@Repository
public interface RepoUser extends MongoRepository<User, String>{

	User findByUsername(String username);
	
}
