/**
 * Created by Teddy on 2016-12-15.
 */
import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {UserService} from "./user.service";

@Component({
  selector: 'login-chat',
  template: `
      
      <div class="wrapper">
        usermane: <input type="text" [(ngModel)]="username">
        password: <input type="text" [(ngModel)]="password">
        <button value="login" (click)="setLogin(username, password)" [routerLink]="['Chat']">login </button>
       
       </div>
      
    `,
  directives: [ROUTER_DIRECTIVES]
})


export class LoginChat{
  username = "";
  password = "";

  constructor(private _userService: UserService) {}

  setLogin(username:string, password:string) {
    console.log("setLogin, u:" +username +" p:" +password);
    this._userService.setPassword(password);
    this._userService.setUsername(username);
  }

}
