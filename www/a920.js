// Empty constructor
function A920() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
A920.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'A920', 'show', [options]);
}

// Installation constructor that binds ToastyPlugin to window
A920.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.a920 = new A920();
  return window.plugins.a920;
};
cordova.addConstructor(A920.install);
