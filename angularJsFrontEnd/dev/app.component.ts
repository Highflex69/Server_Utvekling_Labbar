import {Component, OnInit} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from "angular2/router";
import {LoginChat} from './login-chat.component';
import {VertXChat} from "./vertx-chat.component";
import {UserService} from "./user.service";

@Component({
    selector: 'my-app',
    template: `

      <router-outlet></router-outlet>
      
    `,
  directives: [ROUTER_DIRECTIVES],
  providers: [UserService],
})

@RouteConfig([
  {path: '/chat', name:'Chat', component: VertXChat},
  {path: '', name: 'Root', component: LoginChat, useAsDefault: true}
])


export class AppComponent {
  public username:string = "test";
  public password:string = "tes2";



}
