goog.provide('util.UrlUtil');

util.UrlUtil.httpRoot = function() {
	return 'http://' + location.host;
};

util.UrlUtil.httpsRoot = function() {
	return 'https://' + location.host;
};