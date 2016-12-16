///<reference path="../node_modules/angular2/typings/browser.d.ts"/>
import {bootstrap} from 'angular2/platform/browser';
import 'rxjs/Rx';
import {AppComponent} from "./app.component";
import {ROUTER_PROVIDERS} from 'angular2/router';
import {UserService} from "./user.service";

bootstrap(AppComponent, [
  ROUTER_PROVIDERS,
  UserService
]);


