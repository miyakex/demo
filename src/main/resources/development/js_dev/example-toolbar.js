goog.provide('foo.ExampleToolbar');

goog.require('goog.dom');
goog.require('goog.style');
goog.require('goog.events');
goog.require('goog.ui.Component.EventType');
goog.require('goog.ui.Toolbar');
goog.require('goog.ui.ToolbarButton');
goog.require('goog.ui.ToolbarToggleButton');

/** @constructor */
foo.ExampleToolbar = function() {
};

foo.ExampleToolbar.prototype.init_ = function() {
  goog.style.showElement(goog.dom.getElement('home'), false);
  goog.style.showElement(goog.dom.getElement('toiletNavi'), false);
};

foo.ExampleToolbar.prototype.decorate = function() {
  this.init_();
  goog.style.showElement(goog.dom.getElement('home'), true);
  var toolbar = new goog.ui.Toolbar();

  var exampleToolbarEl = goog.dom.getElementByClass('example-toolbar');
  toolbar.decorate(exampleToolbarEl);
  goog.events.listen(toolbar, goog.ui.Component.EventType.ACTION, this.onAction_);
};

foo.ExampleToolbar.prototype.onAction_ = function(e) {
  goog.style.showElement(goog.dom.getElement('home'), false);
  goog.style.showElement(goog.dom.getElement('toiletNavi'), false);
  var id = e.target.getId();
  switch(id) {
    case 'btn-home':
      goog.style.showElement(goog.dom.getElement('home'), true);
      break;
    case 'btn-toiletNavi':
      goog.style.showElement(goog.dom.getElement('toiletNavi'), true);
      break;
  }
};
