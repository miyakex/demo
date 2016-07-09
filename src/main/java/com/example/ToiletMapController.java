package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToiletMapController {

	@RequestMapping("/toilet-map")
	public String index() {
		return "toilet-map";
	}
}
