package com.emploi.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	
	@GetMapping("/test")
	public String test(Model model) {
	    return "test.html";
	 }
}
