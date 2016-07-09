package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.maps.model.LatLng;

@Controller
public class ToiletMapController {

	@RequestMapping("/toilet-map")
	public String index() {
		return "toilet-map";
	}
	
	@RequestMapping("/toilet-map/load")
    @ResponseBody
    public List<LatLng> load(Map<String, Object> param) {
		List<LatLng> res = Arrays.asList(
			new LatLng(35.714160, 139.80025449999994),
			new LatLng(35.714160, 139.80015449989984),
			new LatLng(35.714160, 139.80005449979974)
			);
		return res;
	}
}
