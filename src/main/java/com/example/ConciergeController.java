package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConciergeController {

	
	@RequestMapping("/concierge")
	public String index() {
		return "concierge";
	}

}
