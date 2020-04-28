package com.wd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestC {
	@RequestMapping("/hello")
	public String test() {
		return "hello docker!";
	}
}
