package com.tta.test.backtesttta.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tta.test.backtesttta.entities.IdentityDocument;

@Repository
public interface RepoIdentityDocument extends MongoRepository<IdentityDocument, Integer>{

	List<IdentityDocument> findByNumber(String number);
}
