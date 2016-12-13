

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.shareddata.LocalMap;

import java.util.*;

/**
 * Created by Teddy on 2016-12-05.
 */



public class main extends AbstractVerticle {
    private ArrayList<ChatClient> clientIPAddresList;
    private ArrayDeque<String> chatMessages;
    private EventBus eb;
    private Gson gson;
    private ArrayList<String[]> sessionList;

    public void start(){
        clientIPAddresList = new ArrayList<ChatClient>();
        chatMessages = new ArrayDeque<String>();
        sessionList = new ArrayList<String[]>();

        eb = vertx.eventBus();
        gson = new Gson();

        eb.consumer("registerclient", message -> {
            System.out.println("message: "+message.body().toString() + " and sessionlistSize = " + sessionList.size());
            String[] userInfo = message.body().toString().split(" ");
            String[] session = {userInfo[0], userInfo[2]};
            String userSocketId = userInfo[3];
            String users = session[0] + " " +session[1];
           boolean exist = false;

            for(int i=0;i<sessionList.size();i++)
            {
                if((sessionList.get(i)[0].equals(session[0]) && sessionList.get(i)[1].equals(session[1])) ||
                        (sessionList.get(i)[1].equals(session[0]) && sessionList.get(i)[0].equals(session[1])))
                {
                    exist = true;
                    break;
                }
            }

            if(!exist)
            {
                sessionList.add(session);
                System.out.println("register done between: " + session[0] +" "+ session[1]);
                //event: username1.username2

                System.out.println("creating bus consumer: " + session[0]+"."+session[1]);
                eb.consumer(getSessionFromUsers(users), chatMessage->{

                    LocalMap<String,String> localMap = vertx.sharedData().getLocalMap(getSessionFromUsers(users));
                    ArrayList<String> chatroom = (ArrayList<String>) localMap.values();
                    System.out.println("inSend id1: " + chatroom.get(0).toString());
                    eb.send(chatroom.get(0).toString(), chatMessage.body().toString());
                    eb.send(chatroom.get(1).toString(), chatMessage.body().toString());

                });
                LocalMap<String,String> localMap = vertx.sharedData().getLocalMap(getSessionFromUsers(users));
                localMap.put("id1",userSocketId);
                //test
                System.out.println("id1: " + userSocketId.toString());
                //test end
            }

            LocalMap<String,String> localMap = vertx.sharedData().getLocalMap(getSessionFromUsers(users));
            localMap.put("id2",userSocketId);

        });

        eb.consumer("sendmessage", message -> {

            String[] msgArr = message.body().toString().split(" ");
            String users = msgArr[0]+" "+msgArr[1];
            StringBuilder msgbuilder = new StringBuilder();
            msgbuilder.append(msgArr[0]+":");

            for(int i=2;i<msgArr.length;i++)
            {
                msgbuilder.append(" "+msgArr[i]);
            }

           eb.send(getSessionFromUsers(users), msgbuilder.toString());
        });
        createChatPage();
    }

    private String getSessionFromUsers(String msg)
    {
        String[] strArr = msg.split(" ");
        String session = "";
        int res = strArr[0].compareTo(strArr[1]);
        if(res < 0)
        {
            session = strArr[0]+"."+strArr[1];
        }
        else
        {
            session = strArr[1]+"."+strArr[0];
        }

        return session;

    }

    private void createChatPage()
    {

        vertx.createHttpServer().websocketHandler(new Handler<ServerWebSocket>() {
            @Override
            public void handle(ServerWebSocket serverWebSocket) {
                if(serverWebSocket.path().equals("/chat"))
                {
                    serverWebSocket.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer data) {
                            System.out.println("got data(in /chat): " + data.toString());
                            vertx.executeBlocking(objectFuture -> {
                                String[] dataStr = data.toString().split(" ");
                                String msg = "";
                                for(int i=1;i<dataStr.length;i++)
                                {
                                    msg = msg.concat(dataStr[i]+" ");
                                }
                                switch (dataStr[0])
                                {
                                    case "OPEN":
                                        msg = msg.concat(serverWebSocket.textHandlerID());
                                        eb.send("registerclient", msg);
                                        break;
                                    case "SEND":

                                        eb.send("sendmessage", msg);
                                        break;
                                    default:
                                        System.out.println("In default");
                                }

                                objectFuture.complete("done");
                            }, true ,res ->{
                                System.out.println("The result is: " + res.result());
                            });

                        }
                    });
                }
                else
                {
                    serverWebSocket.reject();
                }
            }
        }).listen(8080);
    }

}
