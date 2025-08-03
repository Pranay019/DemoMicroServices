package com.demo.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.profile.Entitys.UserProfile;
import com.demo.profile.model.UserProfileDTO;
import com.demo.profile.service.IuserProfileService;

@RestController
@RequestMapping("/profileService")
public class ProfileController {

	@Autowired
	IuserProfileService userProfileService;

	@PostMapping("/saveprofile")
	public ResponseEntity<UserProfileDTO> saveUser(@RequestBody UserProfileDTO userProfileDTO) {
		UserProfileDTO savedUser = userProfileService.saveProfile(userProfileDTO);
		return ResponseEntity.ok(savedUser);
	}

	@GetMapping("/check")
	public ResponseEntity<String> check() {

		return ResponseEntity.ok().body("Working Fine");
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserProfile> fetchById(@PathVariable Integer id) {

		UserProfile userProdile = this.userProfileService.fetchUser(id);

		return ResponseEntity.ok(userProdile);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserProfile>> getAllUSer() {

		List<UserProfile> allusers = this.userProfileService.getallusers();

		return ResponseEntity.ok(allusers);
	}
	
	
	@PatchMapping("/updateuser")
	public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile user){
		
		UserProfile userProdile = this.userProfileService.updateUser(user);
		
		
		
		
		return ResponseEntity.ok(userProdile);
	}
	
	
	
	
	
	
	

}
