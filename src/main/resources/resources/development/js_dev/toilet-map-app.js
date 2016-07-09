goog.provide('toilet-map-app');/*@provide_main@*/

goog.require('map.MapManager');
goog.require('goog.events');
goog.require('goog.events.EventType');
goog.require('net.Ajax');
goog.require('util.UrlUtil');

var opt_here = {
		chase : false
};
var mapMgr = new map.MapManager(opt_here);

var marker1 = new google.maps.Marker({position: new google.maps.LatLng( 35.714150, 139.800254499999998)});
mapMgr.pushMarker(marker1);

function initialize() {
	// get the page's canvas container
	var	mapCanvas = document.getElementById('map_canvas');
	// define the Google Maps options
	var map_options = {
			zoom: 17,
			// let's initially center on downtown Austin
			center: new google.maps.LatLng( 30.264664, -97.747378 ),
			mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	mapMgr.initialize(mapCanvas, map_options);
	mapCanvas.style.width = '100%';
	mapCanvas.style.height = '93%';//TODO まだ無理やりな方法
	
	//bindHandler
	var seachBtn = document.getElementById('seach_btn');
	goog.events.listen(seachBtn, goog.events.EventType.CLICK, function(e) {
		mapMgr.clearMarkers();
		var param = {};
		new net.Ajax().post(util.UrlUtil.httpRoot() + '/toilet-map/load', param).ok(function(res) {
			goog.array.forEach(res, function(position) {
				var marker = new google.maps.Marker({position: new google.maps.LatLng(position.lat, position.lng)});
				mapMgr.pushMarker(marker);
			});
		});
	});
};
