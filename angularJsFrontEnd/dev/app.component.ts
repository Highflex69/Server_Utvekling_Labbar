import {Component} from 'angular2/core';
import {ChatService} from './chat.service';
import {Chat} from './chat.component';
import {CreateMessage} from './create-message.componet';
import {WebSocketService} from './websocket.service';


@Component({
    selector: 'My-app',
    template: `
      <div class="wrapper">
        <navbar>
        </navbar>
        
        <chat></chat>
        <create-message></create-message> 
       </div>
    `,
  directives: [Chat, CreateMessage],
  providers: [ChatService, WebSocketService],
})

export class AppComponent {
/*
  constructor(private _connection: Connection)
  {
      _connection.send();
  }*/
}
