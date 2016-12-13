import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocket;
import io.vertx.core.net.SocketAddress;


/**
 * Created by Teddy on 2016-12-13.
 */
public class ChatClient
{
    String username;
    String socketAddress;
    String password;
    String socketPort;

    public ChatClient(String username, String socketAddress, String password, String socketPort) {
        this.username = username;
        this.socketAddress = socketAddress;
        this.password = password;
        this.socketPort = socketPort;
    }

    public String getSocketAddress() {
        return socketAddress;
    }

    public String getSocketPort() {
        return socketPort;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword()
    {
        return password;
    }
}