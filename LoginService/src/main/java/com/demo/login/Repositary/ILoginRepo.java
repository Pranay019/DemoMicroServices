package com.demo.login.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.login.Entity.Login;

public interface ILoginRepo extends JpaRepository<Login, Integer> {

	public Optional<Login> findByEmail(String email);

	public Optional<Login> findByUsername(String username);

	public Optional<Login> findByEmailAndPassword(String email, String password);
}
