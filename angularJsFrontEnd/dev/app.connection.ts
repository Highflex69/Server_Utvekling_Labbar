/**
 * Created by Teddy on 2016-12-14.
 */
import {$WebSocket} from 'angular2-websocket/angular2-websocket';
//import WebSocket = require('ws');
export class Connection{
  wSocket:$WebSocket;
  public constructor() {
     this.wSocket = new $WebSocket("ws://130.229.171.11:8083/server");

    //var host = "ws://130.229.171.11:8080/chat";

    //var wSocket = new $WebSocket(host);
  }

  public send()
  {
    this.wSocket.send("OPEN carlos test123 teddy");
  }

}
