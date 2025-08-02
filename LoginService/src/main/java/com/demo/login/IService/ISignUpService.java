package com.demo.login.IService;

import org.springframework.stereotype.Service;

import com.demo.login.Entity.Login;

@Service
public interface ISignUpService {

	Login save(Login userCred);

}
