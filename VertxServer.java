import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.EventBusBridgeHook;
import org.vertx.java.core.sockjs.SockJSSocket;
import org.vertx.java.platform.Verticle;

import java.util.HashSet;
import java.util.Set;

import java.util.UUID;

public class VertxServer extends Verticle {

  public void start(){
    EventBus eb = vertx.eventBus();
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(new Handler<HttpServerRequest>() {
      public void handle(HttpServerRequest request) {
        System.out.println("A request has arrived on the server!");
	String randUUID = UUID.randomUUID().toString();
	System.out.println("randUUID: " + randUUID);	
	eb.registerHandler(randUUID, new Handler<Message<JsonObject>>() {
          public void handle(Message<JsonObject> message) {
            JsonObject resp = message.body();
	    System.out.println("I received a reply " + resp);
            request.response().end(resp.toString());
          }
        });
	JsonObject o = new JsonObject();
	o.putString("replyUUID",randUUID);
        eb.send("message", o);
      }
    });
  server.listen(9000, "localhost");
  }
}
