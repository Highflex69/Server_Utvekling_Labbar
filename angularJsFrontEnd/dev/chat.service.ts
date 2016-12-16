/**
 * Created by Teddy on 2016-12-14.
 */
import {Observable, Subject} from 'rxjs/Rx';
import {Injectable} from 'angular2/core';
import {WebSocketService} from './websocket.service';

const CHAT_URL = "ws://130.229.171.11:8083/server";

export interface Message{
    from: string;
    to: string;
    content: string;
}

@Injectable()
export class ChatService{
  public messages: Subject<Message>;

  constructor(wsService: WebSocketService)
  {
    this.messages = <Subject<Message>>wsService
      .connect(CHAT_URL)
      .map((response: MessageEvent): Message =>{

        console.log("response: "+response.data);
        let data = JSON.parse(response.data);

        return{
          from: data.from,
          to: data.to,
          content: data.content,
        }
      });
  }


}

