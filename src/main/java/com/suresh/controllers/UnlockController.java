package com.suresh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.bindings.UnlockAccountRequest;
import com.suresh.service.UnlockAccountService;

@RestController
public class UnlockController {

	@Autowired
	private UnlockAccountService service;

	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockAccountRequest request) {
		return service.unlockAccount(request);
	}

}
