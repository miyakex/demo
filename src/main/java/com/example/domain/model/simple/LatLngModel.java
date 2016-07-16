package com.example.domain.model.simple;

import com.google.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LatLngModel {
	private double lat;
	private double lng;
	
	public LatLng toLatLng() {
		return new LatLng(lat, lng);
	}
}
