package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.model.simple.LatLngModel;
import com.example.domain.model.simple.MarkerModel;

@Controller
public class ToiletMapController {

	@RequestMapping("/toilet-map")
	public String index() {
		return "toilet-map";
	}
	
	@RequestMapping("/toilet-map/load")
    @ResponseBody
    public List<MarkerModel> load(LatLngModel myLatLng) {
		List<MarkerModel> res = Arrays.asList(
			new MarkerModel(35.715160D, 139.80025449999994D, "unko"),
			new MarkerModel(35.716160D, 139.80015449889984D, "unko2"),
			new MarkerModel(35.717160D, 139.80005449779974D, "moreta")
			);
		return res;
	}
}
