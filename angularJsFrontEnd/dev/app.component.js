"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('angular2/core');
var router_1 = require("angular2/router");
var login_chat_component_1 = require('./login-chat.component');
var vertx_chat_component_1 = require("./vertx-chat.component");
var AppComponent = (function () {
    function AppComponent() {
        this.username = "test";
        this.password = "tes2";
        this.logininfo = [this.username, this.password];
    }
    AppComponent = __decorate([
        core_1.Component({
            selector: 'My-app',
            template: "\n\n      <router-outlet [logininfo]=\"this.logininfo\"></router-outlet>\n      \n    ",
            directives: [router_1.ROUTER_DIRECTIVES],
            providers: []
        }),
        router_1.RouteConfig([
            { path: '/chat', name: 'Chat', component: vertx_chat_component_1.VertXChat },
            { path: '', name: 'Root', component: login_chat_component_1.LoginChat, useAsDefault: true }
        ])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map