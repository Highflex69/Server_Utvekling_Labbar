"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by Teddy on 2016-12-14.
 */
var chat_service_1 = require('./chat.service');
var core_1 = require('angular2/core');
var Chat = (function () {
    function Chat(chatService) {
        var _this = this;
        this.chatService = chatService;
        this.messages = new Array();
        chatService.messages.subscribe(function (msg) {
            _this.messages.push(msg);
        });
    }
    Chat = __decorate([
        core_1.Component({
            selector: 'chat',
            template: '<div class = "messages"> <p *ngFor="#msg of messages; #last = last">{{ msg.content }}</p> </div>',
            directives: [],
            providers: [chat_service_1.ChatService]
        })
    ], Chat);
    return Chat;
}());
exports.Chat = Chat;
//# sourceMappingURL=chat.component.js.map