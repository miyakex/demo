package com.example.domain.model.simple;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class MarkerModel extends LatLngModel {
	private String infoContent;
	
	public MarkerModel(double lat, double lng, String infoContent) {
		super(lat, lng);
		this.infoContent = infoContent;
	}
}
