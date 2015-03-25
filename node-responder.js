var vertx = require('vertx-eventbus-client');

var eb = new vertx.EventBus('http://localhost:9001/eventbus');

eb.onopen = function() {
  console.log('eb.onopen start.');
  eb.registerHandler("message",function(message,reply){
    console.log("some-news",message);
    eb.send(message.replyUUID,{"status": "resp"});
  });
}
