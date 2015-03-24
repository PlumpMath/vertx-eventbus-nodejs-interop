1. Install vertx 2.1.5
2. Install node 0.10.29
3. npm install . in directory to install node modules
4. Make sure clustering is setup and working.  I had issues with multicast on my mac. My sanity check for this was:
vertx run VertxServer.java -cluster -cluster-host 127.0.0.1
vertx run vertx-responder.js -cluster -cluster-host 127.0.0.2
check netstat to make sure connection exists
curl http://localhost:9000 -D -
Should get a 200 w/ empty response back and see messages on both server and responder.
5. Start up test environment:
vertx run VertxServer.java -cluster -cluster-host 127.0.0.1
vertx run VertxEventBusServer.java  -cluster -cluster-host 127.0.0.2
node node-responder.js
6. curl http://localhost:9000
7. You will see VertxServer.java received the request
8. You will see that node-responder.js received an empty object {}.
9. You will see that VertxEventBusServer.java caught a handleSendOrPub event.
10. You will see VertxServer.java never gets the response.
11. curl will be hung waiting for a response.
