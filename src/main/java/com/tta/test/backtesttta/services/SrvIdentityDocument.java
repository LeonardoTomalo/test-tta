package com.tta.test.backtesttta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tta.test.backtesttta.entities.IdentityDocument;
import com.tta.test.backtesttta.repositories.RepoIdentityDocument;

@Service
public class SrvIdentityDocument {

	@Autowired
	RepoIdentityDocument repo;
	
	public List<IdentityDocument> getAllDocuments(){
		return repo.findAll();
	}
	
	public List<IdentityDocument> getByNumber(String n) {
		return repo.findByNumber(n);
	}
	
	public void save(IdentityDocument idoc) {
		repo.save(idoc);
	}

	public void saveAll(List<IdentityDocument> listDoc) {
		repo.saveAll(listDoc);
	}

	public void deleteByNumber(IdentityDocument idoc) {
		repo.delete(idoc);
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
		
}
