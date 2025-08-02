package com.demo.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.login.model.CatModel;

@Service
public class CatService {

	@Autowired
	private RestTemplate restTemplate;

	public CatModel callApi() {

		String url = "https://catfact.ninja/fact";

		ResponseEntity<CatModel> forEntity = restTemplate.getForEntity(url, CatModel.class);
		

		return forEntity.getBody();
	}

}
