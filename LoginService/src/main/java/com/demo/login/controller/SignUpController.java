package com.demo.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.login.ClientComp.SaveProfile;
import com.demo.login.Entity.Login;
import com.demo.login.IService.ISignUpService;
import com.demo.login.model.AddressDTO;
import com.demo.login.model.LoginDetails;
import com.demo.login.model.UserProfileDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/demo")
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private ISignUpService SignUpService;

    @Autowired
    SaveProfile saveProfile;

    @PostMapping("/signup")
    @CircuitBreaker(name = "PROFILESERVICE", fallbackMethod = "saveProfleFallback")
    @Transactional
    public ResponseEntity<String> invokeSignUp(@RequestBody LoginDetails userDetails,
                                               @RequestHeader(required = false) String Auth) {

        logger.info("Received signup request");

        if (userDetails == null) {
            return ResponseEntity.badRequest().body("user name or password should not be null");
        }

        Login userCred = new Login();
        userCred.setUsername(userDetails.getUsername());
        userCred.setPassword(userDetails.getPassword());
        userCred.setEmail(userDetails.getEmail());

        userCred = this.SignUpService.save(userCred);

        if (userCred.getId() != null) {
            UserProfileDTO user = new UserProfileDTO();
            user.setEmail(userCred.getEmail());
            user.setFullName(userCred.getUsername());
            user.setPhone("7845874539");

            AddressDTO address = new AddressDTO();
            address.setStreet("kotha lingampallay");
            address.setCity("nakapur");
            address.setDistrict("karimnagaer");
            address.setState("telangana");
            address.setZipCode("505188");

            user.setAddress(address);

            ResponseEntity<UserProfileDTO> saveProfile2 = this.saveProfile.saveProfile(user);

            userDetails.setId(userCred.getId());
            return ResponseEntity.ok("SignUp Successfull. Please login. " +
                    saveProfile2.getBody().toString() + " | Status: " + saveProfile2.getStatusCode());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
    }

  
    public ResponseEntity<String> saveProfleFallback(LoginDetails userDetails, String Auth, Throwable e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Profile Service is currently unavailable. Please try again later.");
    }
}
