/**
 * Created by Teddy on 2016-12-15.
 */
import {Component} from 'angular2/core';
import {VertXChat} from './vertx-chat.component';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {Observable} from "rxjs/Observable";
import {AppComponent} from "./app.component";

@Component({
  selector: 'login-chat',
  template: `
      
      <div class="wrapper">
        usermane: <input type="username"  >
         password: <input type="password">
         
        {{AppComponent.logininfo}}
        <button value="login"  [routerLink]="['Chat']">login </button>
       
       </div>
      
    `,
  directives: [ROUTER_DIRECTIVES],
  providers: [],
  inputs: ['logininfo']
})

export class LoginChat{
  username = "";
  password = "";

  login():string{
    return "Chat";
  }
}
