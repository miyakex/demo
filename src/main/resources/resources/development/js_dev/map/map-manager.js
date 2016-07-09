goog.provide('map.MapManager');

goog.require('goog.array');
goog.require('util.UrlUtil');

/**
 * @param opt_here = {chase:{boolean}}
 */
map.MapManager = function(opt_here) {
	this.loaded_ = false;
	this.loadedListener_ = [];
	this.opt_here_ = opt_here;
	this.mapObj_ = null;
	this.markers_ = [];
};

/******************************************************** 
 * PUBLIC 
 ********************************************************/
map.MapManager.prototype.initialize = function(mapCanvas, map_options) {
	this.mapObj_ = new google.maps.Map(mapCanvas, map_options);
	if (this.opt_here_) {
		this.displayHere_();
	}
	this.loaded_ = true;
	goog.array.forEach(this.loadedListener_, function(listen) {
		listen.call();
	}, this);
};

map.MapManager.prototype.pushMarker = function(marker) {
	if (!this.loaded_) {
		var that = this;
		this.loadedListener_.push(function() {
			that.pushMarker(marker);
		});
		return;
	}
	marker.setMap(this.mapObj_);
	this.markers_.push(marker);
};

map.MapManager.prototype.clearMarkers = function() {
	goog.array.forEach(this.markers_, function(marker) {
		marker.setMap(null);
	}, this);
	this.markers_ = [];
};

map.MapManager.prototype.centerHere = function() {
	var that = this;
	if (!this.loaded_) {
		this.loadedListener_.push(function() {
			that.centerHere();
		});
		return;
	}
	navigator.geolocation.getCurrentPosition(
			//success
			function(position) {
				var myLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
				that.mapObj_.setCenter(myLatLng);
			},
			//error
			map.MapManager.handleError_, 
			//option
			map.MapManager.geolocationDefOpt_);
};

/******************************************************** 
 * PRIVETE 
 ********************************************************/
map.MapManager.prototype.displayHere_ = function() {
	var first = true;
	var myMarker = 0;
	var that = this;
	var displayLocation = function(position) {
			// create a new LatLng object for every position update
			var myLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
			// build entire marker first time thru
			if (!myMarker) {
				// define our custom marker image
				var image = new google.maps.MarkerImage(
					// [MIT-LICENSE]https://github.com/stedman/GeoLocateMe
					util.UrlUtil.httpRoot() + '/images/bluedot.png',
					null, // size
					null, // origin
					new google.maps.Point(8, 8), // anchor (move to center of marker)
					new google.maps.Size(17, 17) // scaled size (required for Retina display icon)
				);
				// then create the new marker
				myMarker = new google.maps.Marker({
					flat: true,
					icon: image,
					map: that.mapObj_,
					optimized: false,
					position: myLatLng,
					title: 'I might be here',
					visible: true
				});
			
			// just change marker position on subsequent passes
			} else {
				myMarker.setPosition(myLatLng);
			}
			// center map view on every pass
			if (that.opt_here_.chase || first) {
				that.mapObj_.setCenter(myLatLng);
				first = false;
			}
		};
	var handleError = function(error) {
			var errorMessage = [ 
				'We are not quite sure what happened.',
				'Sorry. Permission to find your location has been denied.',
				'Sorry. Your position could not be determined.',
				'Sorry. Timed out.'
			];
			alert(errorMessage[error.code]);
		};
		// cache the userAgent
	var useragent = navigator.userAgent;
	// allow iPhone or Android to track movement
	if (useragent.indexOf('iPhone') !== -1 || useragent.indexOf('Android') !== -1) {
		navigator.geolocation.watchPosition(
			displayLocation, 
			map.MapManager.handleError_, 
			map.MapManager.geolocationDefOpt_
		);	
	}

};

map.MapManager.handleError_ = function(error) {
	var errorMessage = [ 
	    'We are not quite sure what happened.',
	    'Sorry. Permission to find your location has been denied.',
	    'Sorry. Your position could not be determined.',
	    'Sorry. Timed out.'
	];
	alert(errorMessage[error.code]);
};

map.MapManager.geolocationDefOpt_ = {
		enableHighAccuracy: true,
		maximumAge: 30000,
		timeout: 27000
};