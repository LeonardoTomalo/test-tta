package com.tta.test.backtesttta.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "documentType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentType implements Serializable{

	private static final long serialVersionUID = 4384654380390906907L;
	
	@Id private String id;
	private String code;
	private String name;

}
