package com.shopme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class MainController {
 
	@GetMapping("api")
	public String api() {
		return "hi";
	}
}
