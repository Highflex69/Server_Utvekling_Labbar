"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by Teddy on 2016-12-15.
 */
var core_1 = require('angular2/core');
var router_1 = require('angular2/router');
var LoginChat = (function () {
    function LoginChat() {
        this.username = "";
        this.password = "";
    }
    LoginChat.prototype.login = function () {
        return "Chat";
    };
    LoginChat = __decorate([
        core_1.Component({
            selector: 'login-chat',
            template: "\n      \n      <div class=\"wrapper\">\n        usermane: <input type=\"username\"  >\n         password: <input type=\"password\">\n         \n        {{AppComponent.logininfo}}\n        <button value=\"login\"  [routerLink]=\"['Chat']\">login </button>\n       \n       </div>\n      \n    ",
            directives: [router_1.ROUTER_DIRECTIVES],
            providers: [],
            inputs: ['logininfo']
        })
    ], LoginChat);
    return LoginChat;
}());
exports.LoginChat = LoginChat;
//# sourceMappingURL=login-chat.component.js.map