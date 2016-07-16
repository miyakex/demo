goog.provide('util.MarkerUtil');

/**
 * @param {google.maps.LatLng|Object{lat=number, lng=number}} latlng
 * @param {string|node} opt_info
 */
util.MarkerUtil.newInstance = function(latlng, opt_info) {
	var position = (latlng instanceof google.maps.LatLng)?latlng : new google.maps.LatLng(latlng.lat, latlng.lng);
	var marker = new google.maps.Marker({position: position});
	if (opt_info) {
		google.maps.event.addListener(marker, 'click', function() {
			var infoWindow = new google.maps.InfoWindow({content: opt_info});
			infoWindow.open(marker.getMap(), marker);
		});
	}
	return marker;
};

/**
 * @param {MarkerModel} model
 */
util.MarkerUtil.toInstance = function(model) {
	var position = new google.maps.LatLng(model.lat, model.lng);
	var marker = new google.maps.Marker({position: position});
	if (model.infoContent) {
		google.maps.event.addListener(marker, 'click', function() {
			var infoWindow = new google.maps.InfoWindow({content: model.infoContent});
			infoWindow.open(marker.getMap(), marker);
		});
	}
	return marker;
};