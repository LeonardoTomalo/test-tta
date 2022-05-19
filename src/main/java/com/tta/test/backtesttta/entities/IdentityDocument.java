package com.tta.test.backtesttta.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "identityDocument")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDocument implements Serializable{

	private static final long serialVersionUID = -265658328635655937L;
	
	@Id private String id;
	private String number;
	private String expiryDate;
	private String emissionDate;
	private DocumentType documentType;
}
