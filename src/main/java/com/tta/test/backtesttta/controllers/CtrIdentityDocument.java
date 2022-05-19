package com.tta.test.backtesttta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tta.test.backtesttta.entities.IdentityDocument;
import com.tta.test.backtesttta.services.SrvIdentityDocument;

@RestController()
@RequestMapping("/documents")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class CtrIdentityDocument {
	
	@Autowired
	SrvIdentityDocument srv;

	@GetMapping("/get")
	List<IdentityDocument> findAllDocuments(){
		for (IdentityDocument i : srv.getAllDocuments()) {
			System.out.println(i.toString());			
		}
		return srv.getAllDocuments();
	}
	
	@GetMapping("/get/{number}")
	List<IdentityDocument> findAllDocuments(@PathVariable(name="number") String n){
		try {
			if(!n.isEmpty() || !n.isBlank()) return srv.getByNumber(n);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@PostMapping("/post")
	String createDoc(@RequestBody IdentityDocument idoc) {
		try {
			srv.save(idoc);
			return "Successful create.!!";
		} catch (Exception e) {
			e.printStackTrace();	
			return "Failed to create.!!";
		}
	}
	
	@PutMapping("/put")
	String updateDoc(@RequestBody IdentityDocument idoc) {
		try {
			for (IdentityDocument i : srv.getByNumber(idoc.getNumber())) {
				System.out.println("############# " + idoc.getNumber());
				if (i.getNumber().equals(idoc.getNumber())) {
					System.out.println("############# ----------" + i.getNumber());
					i.setDocumentType(idoc.getDocumentType());
					i.setEmissionDate(idoc.getEmissionDate());
					i.setExpiryDate(idoc.getExpiryDate());
					srv.save(i);
				}
			}
			return "Success to update.!!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to update.!!";
		}		
	}
	
	@DeleteMapping("/delete/{number}")
	public String deletePhysical(@PathVariable(name="number") String n) {
		try {
			for (IdentityDocument i : srv.getByNumber(n)) {
				if (i.getNumber().equals(n)) {
					System.out.println("ENCONTRO EL DOCUMENTO: " + n);
					srv.deleteByNumber(i);
				}
			}			
			return "Success to delete.!!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to delete.!!";
		}
	}
	
	@DeleteMapping("/delete/all")
	public String deletePhysicalAll() {
		try {
			srv.deleteAll();
			return "Success to delete.!!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to delete.!!";
		}
	}
	
}
