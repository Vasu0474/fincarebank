package com.fincarebank.springlimitexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincarebank.springlimitexample.limits.LimitService;
import com.fincarebank.springlimitexample.limits.Limits;

@RestController
public class Controller {

	@Autowired
	LimitService limitService;
	
	
	@GetMapping("/demo/limit")
	public Limits demo()
	{
		return new Limits(limitService.getMin(),limitService.getMax());
	}
}
