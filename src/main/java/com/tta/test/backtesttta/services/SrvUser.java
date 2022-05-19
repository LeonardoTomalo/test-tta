package com.tta.test.backtesttta.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tta.test.backtesttta.entities.User;
import com.tta.test.backtesttta.repositories.RepoUser;

@Service
public class SrvUser implements UserDetailsService{

	@Autowired
	private RepoUser repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user != null) 
			return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
		else return null;
	}

}
