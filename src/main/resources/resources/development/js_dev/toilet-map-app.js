goog.provide('toilet-map-app');/*@provide_main@*/


/* 
DEMO FILE FOR: You Are Here (with Safari on iPhone)
Created by Steve Stedman on 2009-06-18: http://plebeosaur.us/you-are-here-with-safari-on-iphone/
Updated on 2010-02-24: http://plebeosaur.us/iphone-safari-geolocation-map-update/
Updated on 2011-10-30: http://plebeosaur.us/here-we-are-again/
*/
window.plebeosaur = window.plebeosaur || {
map: function() {
	var	// get the page's canvas container
		mapCanvas = document.getElementById( 'map_canvas' ),
		// define the Google Maps options
		map_options = {
			zoom: 17,
			// let's initially center on downtown Austin
			center: new google.maps.LatLng( 30.264664, -97.747378 ),
			mapTypeId: google.maps.MapTypeId.ROADMAP
		},
		// then create the map
		map = new google.maps.Map( mapCanvas, map_options ),
		
		myMarker = 0,
		displayLocation = function( position ) {
			// create a new LatLng object for every position update
			var myLatLng = new google.maps.LatLng( position.coords.latitude, position.coords.longitude );
			// build entire marker first time thru
			if ( !myMarker ) {
				// define our custom marker image
				var image = new google.maps.MarkerImage(
					'images/bluedot.png',
					null, // size
					null, // origin
					new google.maps.Point( 8, 8 ), // anchor (move to center of marker)
					new google.maps.Size( 17, 17 ) // scaled size (required for Retina display icon)
				);
				// then create the new marker
				myMarker = new google.maps.Marker({
					flat: true,
					icon: image,
					map: map,
					optimized: false,
					position: myLatLng,
					title: 'I might be here',
					visible: true
				});
			
			// just change marker position on subsequent passes
			} else {
				myMarker.setPosition( myLatLng );
			}
			// center map view on every pass
			map.setCenter( myLatLng );
		},
		handleError = function( error ) {
			var errorMessage = [ 
				'We are not quite sure what happened.',
				'Sorry. Permission to find your location has been denied.',
				'Sorry. Your position could not be determined.',
				'Sorry. Timed out.'
			];
			alert( errorMessage[ error.code ] );
		},
		// cache the userAgent
		useragent = navigator.userAgent;
	// set the map canvas's height/width (Google Maps needs inline height/width)
	mapCanvas.style.width = mapCanvas.style.height = '100%';
	// allow iPhone or Android to track movement
	if ( useragent.indexOf('iPhone') !== -1 || useragent.indexOf('Android') !== -1 ) {
		navigator.geolocation.watchPosition( 
			displayLocation, 
			handleError, 
			{ 
				enableHighAccuracy: true, 
				maximumAge: 30000, 
				timeout: 27000 
			}
		);			
	// or let other geolocation capable browsers to get their static position
	} else if ( navigator.geolocation ) {
		navigator.geolocation.getCurrentPosition( displayLocation, handleError );
	}
}
};
