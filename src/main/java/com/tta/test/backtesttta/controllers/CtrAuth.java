package com.tta.test.backtesttta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tta.test.backtesttta.entities.User;
import com.tta.test.backtesttta.models.AuthReponse;
import com.tta.test.backtesttta.models.AuthRequest;
import com.tta.test.backtesttta.repositories.RepoUser;
import com.tta.test.backtesttta.services.SrvUser;
import com.tta.test.backtesttta.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class CtrAuth {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private RepoUser repo;
	@Autowired
	private SrvUser userService;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/register")
	private ResponseEntity<?> register(@RequestBody AuthRequest authrq){
		String username = authrq.getUsername();
		String password = authrq.getPassword();	
		System.out.println("USER BODY: " + authrq.toString());
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		System.out.println("USER CLASS: " + u.toString());
		try {
			repo.save(u);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new AuthReponse("Error to register user: " + username));
		}
		return ResponseEntity.ok(new AuthReponse("Succesful to register user: " + username ));		
	}
	
	@PostMapping("/access")
	private ResponseEntity<?> access(@RequestBody AuthRequest authrq){
		String username = authrq.getUsername();
		String password = authrq.getPassword();		
		System.out.println("USER BODY ACCESS: " + authrq.toString());
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new AuthReponse("Error to access user: " + username));
		}
		UserDetails loadedUser = userService.loadUserByUsername(username);
		String generatedToken = jwtUtils.generateToken(loadedUser);
		return ResponseEntity.ok(new AuthReponse(generatedToken));		
	}
	
	@GetMapping("/get")
	private String getprueba() {
		return "WELCOME, SUCCESFUL GET API REST.!! " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
