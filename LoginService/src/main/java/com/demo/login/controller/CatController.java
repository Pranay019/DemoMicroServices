package com.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.login.Service.CatService;
import com.demo.login.model.CatModel;

@RestController
@RequestMapping("/demo/protected")
public class CatController {

	@Autowired
	private CatService catService;

	@GetMapping("/callcat")
	public ResponseEntity<CatModel> callCat() {

		CatModel cm = new CatModel();

		cm = this.catService.callApi();

		return ResponseEntity.ok(cm);

	}

}
