/**
 * Created by Teddy on 2016-12-15.
 */
import {Component} from 'angular2/core';
import {ChatService} from './chat.service';
import {Chat} from './chat.component';
import {CreateMessage} from './create-message.componet';
import {WebSocketService} from './websocket.service';
import {UserService} from "./user.service";
import {Inject} from 'angular2/core';


@Component({
  selector: 'vertxchat',
  template: `
      <div class="wrapper">
      <p>{{username}}</p>
        <navbar></navbar>
        <chat></chat>
        <create-message></create-message> 
       </div>
    `,
  directives: [Chat, CreateMessage],
  providers: [ChatService, WebSocketService],

})

export class VertXChat{
  username:string = "asdf";
  password:string= "asdf";


  constructor(private _userService: UserService) {}

  ngOnInit(){
    console.log("Constructor: user = " +this._userService.getUsername());
    this.username = this._userService.getUsername();
    this.password = this._userService.getPassword();
  }

}
