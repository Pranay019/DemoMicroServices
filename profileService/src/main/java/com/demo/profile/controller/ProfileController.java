package com.demo.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.profile.ClientComp.NotificationService;
import com.demo.profile.Entitys.UserProfile;
import com.demo.profile.model.UserProfileDTO;
import com.demo.profile.service.IuserProfileService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/profileService")
public class ProfileController {

	@Autowired
	IuserProfileService userProfileService;

	@Autowired
	NotificationService notificationService;

	@PostMapping("/saveprofile")
	@CircuitBreaker(name = "NOTIFICTIONSERVICE", fallbackMethod = "emailServiceFail")
	public ResponseEntity<UserProfileDTO> saveUser(@RequestBody UserProfileDTO userProfileDTO) {
		UserProfileDTO savedUser = userProfileService.saveProfile(userProfileDTO);

		
		if(savedUser.getId()!=null) {
			String to ="bunnypatelz005@gmail.com";
			String sbject ="Sign up Successfull From profile Service";
			String text = String.format(
				    "Hello %s,\n\n" +
				    "Congratulations! Your signup was successful for the Profile Service.\n\n" +
				    "Here are your profile details:\n" +
				    "--------------------------------\n" +
				    "Full Name : %s\n" +
				    "Email     : %s\n" +
				    "Phone     : %s\n" +
				    "Address   : %s, %s, %s - %s\n\n" +
				    "Thank you for registering with us!\n\n" +
				    "Best regards,\n" +
				    "Profile Service Team",
				    savedUser.getFullName(),
				    savedUser.getFullName(),
				    savedUser.getEmail(),
				    savedUser.getPhone(),
				    savedUser.getAddress().getStreet(),
				    savedUser.getAddress().getCity(),
				    savedUser.getAddress().getState(),
				    savedUser.getAddress().getZipCode()
				);

			
			String resp = this.notificationService.sendEmail(to, sbject, text);
			
		}

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
	public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile user) {

		UserProfile userProdile = this.userProfileService.updateUser(user);

		return ResponseEntity.ok(userProdile);
	}

	public ResponseEntity<String> emailServiceFail(Throwable e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Email Service is currently unavailable. Please try again later.");
	}

}
