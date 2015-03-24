var eb = require("vertx/event_bus");
var console = require("vertx/console");
var vertx = require("vertx")

eb.registerHandler("message", function(message,reply) {
    console.log("message: " , message);
    var a = {};
    var response = "resp";
    a["content-length"] = response.length;
    a['x-response-body'] = response;
    reply(a);
});
