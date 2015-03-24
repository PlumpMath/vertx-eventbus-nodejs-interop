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

public class VertxServer extends Verticle {

  public void start(){
    EventBus eb = vertx.eventBus();
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(new Handler<HttpServerRequest>() {
      public void handle(HttpServerRequest request) {
        System.out.println("A request has arrived on the server!");
        eb.send("message", new JsonObject(), new Handler<Message<JsonObject>>() {
          public void handle(Message<JsonObject> message) {
            System.out.println("I received a reply " + message.body());
            request.response().end();
          }
        });
      }
    });
  server.listen(9000, "localhost");
  }
}
