package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.model.simple.LatLngModel;
import com.example.domain.model.simple.MarkerModel;
import com.example.enums.tags.ToiletAttr;
import com.example.service.toilet.ToiletService;

@Controller
public class ToiletMapController {

	@Autowired
	private ToiletService service;
	
	@RequestMapping("/toilet-map")
	public String index() {
		return "toilet-map";
	}
	
	@RequestMapping("/toilet-map/load")
    @ResponseBody
    public List<MarkerModel> load(LatLngModel myLatLng) {
		List<MarkerModel> res = Arrays.asList(
			new MarkerModel(35.715160D, 139.80025449999994D, service.createContent(ToiletAttr.SHOWER)),
			new MarkerModel(35.716160D, 139.80015449889984D, service.createContent(ToiletAttr.SHOWER, ToiletAttr.SOUND)),
			new MarkerModel(35.717160D, 139.80005449779974D, service.createContent(ToiletAttr.SHOWER, ToiletAttr.STILLAGE, ToiletAttr.BARRIER_FREE))
			);
		return res;
	}
}
