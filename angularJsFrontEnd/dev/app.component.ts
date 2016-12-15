import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from "angular2/router";
import {LoginChat} from './login-chat.component';
import {VertXChat} from "./vertx-chat.component";


@Component({
    selector: 'My-app',
    template: `

      <router-outlet [logininfo]="this.logininfo"></router-outlet>
      
    `,
  directives: [ROUTER_DIRECTIVES],
  providers: [],
})

@RouteConfig([
  {path: '/chat', name:'Chat', component: VertXChat},
  {path: '', name: 'Root', component: LoginChat, useAsDefault: true}
])


export class AppComponent {
  username:string = "test";
  password:string = "tes2";
  logininfo:Array<string>;

  constructor()
  {
    this.logininfo = [ this.username,this.password ]
  }
}
