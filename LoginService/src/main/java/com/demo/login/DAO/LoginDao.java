package com.demo.login.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.login.Entity.Login;
import com.demo.login.Repositary.ILoginRepo;

@Repository
public class LoginDao {

	@Autowired
	private ILoginRepo loginRepo;

	public Login save(Login userCred) {

		Optional<Login> emailExists = this.loginRepo.findByEmail(userCred.getEmail());
		Optional<Login> userExists = this.loginRepo.findByUsername(userCred.getUsername());

		// If both email and username are unique, save
		if (emailExists.isEmpty() && userExists.isEmpty()) {
			return this.loginRepo.save(userCred);
		}

		// If email is taken but username is free
		else if (emailExists.isPresent() && userExists.isEmpty()) {
			throw new RuntimeException("Email already exists.");
		}

		// If username is taken but email is free
		else if (emailExists.isEmpty() && userExists.isPresent()) {
			throw new RuntimeException("Username already exists.");
		}

		// If both are taken
		else {
			throw new RuntimeException("Email and Username already exist.");
		}
	}

	public Login findByEmailAndPassword(String email, String password) {

		Optional<Login> user = this.loginRepo.findByEmailAndPassword(email, password);

		if (user.isPresent()) {

			return user.get();

		}

		return null;
	}
}
