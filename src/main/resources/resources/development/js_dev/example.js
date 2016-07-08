goog.provide('foo.Example');

goog.require('goog.array');
goog.require('goog.crypt.base64');
goog.require('goog.dom');
goog.require('goog.dom.classes');
goog.require('goog.events');
goog.require('goog.net.EventType');
goog.require('goog.net.XhrIo');
goog.require('net.Ajax');

/**
 * An example class of the Googkit project template.
 * @constructor
 */
foo.Example = function() {
};


/**
 * Shows a message 'it works'.
 */
foo.Example.prototype.showItWorks = function() {
  // Add 'works' css class if Closure Library works correctly
  goog.dom.classes.set(document.body, 'works');
};


/**
 * Shows a simple demo.
 */
  foo.Example.prototype.demonstrate = function() {
  var text = goog.crypt.base64.encodeString('Hello, Googkit');

  var welcomeElem = goog.dom.getElementByClass('welcome');
  var newElem = goog.dom.createDom('pre', null, text);
  goog.dom.insertSiblingAfter(newElem, welcomeElem);
};


/**
 * Sample method.
 */
foo.Example.prototype.doSomething = function() {
	alert(location.host);
	net.Ajax.get('http://'+ location.host +'/getusers', function(res) {
		alert(res.name + ', ' + res.age);
		new net.Ajax().post('http://'+ location.host +'/setusers', res).ok(function(obj) {
			alert(obj.name + ', ' + res.age);
		}).fail(function() {
			alert('fail');
		}).always(function() {
			alert('always');
		});
	});
};
