goog.provide('foo.Example');

goog.require('goog.array');
goog.require('goog.crypt.base64');
goog.require('goog.dom');
goog.require('goog.dom.classes');


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
  this.showItWorks();
  this.demonstrate();
};
