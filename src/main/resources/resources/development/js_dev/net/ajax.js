goog.provide('net.Ajax');
goog.require('goog.net.XhrIo');
goog.require('goog.events');
goog.require('goog.net.EventType');

net.Ajax = function() {
	this.xhr_ = new goog.net.XhrIo();
	this.opt_okListener_ = null;
	this.opt_failListener_ = null;
	this.opt_alwaysListener_ = null;
};

net.Ajax.get = function(url, callback) {
	goog.net.XhrIo.send(url, function(e) {
		var xhr = e.target;
		var obj = xhr.getResponseJson();
		callback(obj);
	});
};

net.Ajax.prototype.post = function(url, param) {
	var data = goog.uri.utils.buildQueryDataFromMap(param);
	var that = this;
	goog.events.listenOnce(this.xhr_, goog.net.EventType.COMPLETE, function(e) {
		var xhr = /** @type {goog.net.XhrIo} */ (e.target);
        if (xhr.isSuccess()) {
        	var obj = xhr.getResponseJson();
        	if (that.opt_okListener_) {
        		that.opt_okListener_(obj);
        	}
        } else {
        	if (that.opt_failListener_) {
        		that.opt_failListener_();
        	}
        }
        if (that.opt_alwaysListener_) {
        	that.opt_alwaysListener_();
        }
        xhr.dispose(); // Dispose of the XHR if it is not going to be reused.
    });
	this.xhr_.send(url, 'POST', data);
	return this;
};

net.Ajax.prototype.ok = function(okListener) {
	this.opt_okListener_ = okListener;
	return this;
};

net.Ajax.prototype.fail = function(failListener) {
	this.opt_failListener_ = failListener;
	return this;
};

net.Ajax.prototype.always = function(alwaysListener) {
	this.opt_alwaysListener_ = alwaysListener;
	return this;
};

net.Ajax.prototype.depose = function() {
	this.xhr_.dispose();
};