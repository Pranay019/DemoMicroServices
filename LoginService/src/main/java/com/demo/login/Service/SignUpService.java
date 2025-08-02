package com.demo.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.login.DAO.LoginDao;
import com.demo.login.Entity.Login;
import com.demo.login.IService.ISignUpService;

@Service
public class SignUpService implements ISignUpService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public Login save(Login userCred) {

		userCred = this.loginDao.save(userCred);

		return userCred;
	}

}
