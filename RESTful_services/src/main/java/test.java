import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import sun.applet.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Created by Teddy on 2016-11-22.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class test {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        System.out.println("hello world systeout");
        return "Hello World";
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:8080/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:8080/helloworld");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        //System.out.println("Server stopped");*/
    }
}
