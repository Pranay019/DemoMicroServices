package com.demo.login.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class ProtectedController {
	
	
	
	
	
	@GetMapping("/protected")
	public ResponseEntity<List<String>> getdetails(){
		
		List<String> names = Arrays.asList("apple","samsung","micromax","MI");
		
		
		
		return ResponseEntity.ok(names);
		
	}

}
