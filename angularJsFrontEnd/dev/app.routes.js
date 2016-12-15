"use strict";
/**
 * Created by Teddy on 2016-12-15.
 */
var router_1 = require("angular2/router");
var vertx_chat_component_1 = require('./vertx-chat.component');
var login_chat_component_1 = require('./login-chat.component');
var APP_ROUTES = [
    { path: 'chat', component: vertx_chat_component_1.VertXChat },
    { path: '', component: login_chat_component_1.LoginChat }
];
exports.APP_ROUTES_PROVIDER = [
    router_1.Router(APP_ROUTES),
];
//# sourceMappingURL=app.routes.js.map