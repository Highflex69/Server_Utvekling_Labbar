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
var chat_service_1 = require('./chat.service');
var chat_component_1 = require('./chat.component');
var create_message_componet_1 = require('./create-message.componet');
var websocket_service_1 = require('./websocket.service');
var VertXChat = (function () {
    function VertXChat(_userService) {
        this._userService = _userService;
        this.username = "asdf";
        this.password = "asdf";
    }
    VertXChat.prototype.ngOnInit = function () {
        console.log("Constructor: user = " + this._userService.getUsername());
        this.username = this._userService.getUsername();
        this.password = this._userService.getPassword();
    };
    VertXChat = __decorate([
        core_1.Component({
            selector: 'vertxchat',
            template: "\n      <div class=\"wrapper\">\n      <p>{{username}}</p>\n        <navbar></navbar>\n        <chat></chat>\n        <create-message></create-message> \n       </div>\n    ",
            directives: [chat_component_1.Chat, create_message_componet_1.CreateMessage],
            providers: [chat_service_1.ChatService, websocket_service_1.WebSocketService]
        })
    ], VertXChat);
    return VertXChat;
}());
exports.VertXChat = VertXChat;
//# sourceMappingURL=vertx-chat.component.js.map