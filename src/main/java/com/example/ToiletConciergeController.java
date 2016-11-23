package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToiletConciergeController {
	
	@RequestMapping("/toilet-concierge")
	public String index() {
		return "toilet-concierge";
	}
	

}
