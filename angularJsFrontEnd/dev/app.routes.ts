/**
 * Created by Teddy on 2016-12-15.
 */
import {Router} from "angular2/router";
import {VertXChat} from './vertx-chat.component';
import {LoginChat} from './login-chat.component';

const APP_ROUTES = [
{path: 'chat', component: VertXChat},
{ path: '', component: LoginChat}
];

export const APP_ROUTES_PROVIDER = [
  Router(APP_ROUTES),
];
