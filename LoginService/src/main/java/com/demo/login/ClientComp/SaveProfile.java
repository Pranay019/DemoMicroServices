package com.demo.login.ClientComp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.login.model.UserProfileDTO;

@FeignClient(name = "PROFILESERVICE")
public interface SaveProfile {

	@PostMapping("profileService/saveprofile")
	ResponseEntity<UserProfileDTO> saveProfile(@RequestBody UserProfileDTO profileDTO);

}
