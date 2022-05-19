package com.tta.test.backtesttta.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	
	private static final long serialVersionUID = -4850216959608886031L;

	@Id private String id;
	private String username;
	private String password;
	

}
