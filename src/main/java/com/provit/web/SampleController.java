package com.provit.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/ping")
	public String test() {
	    try {
	        return "Welcome";
	    } catch (Exception e){
	        throw new RuntimeException(e);
	    }
	} 
}
