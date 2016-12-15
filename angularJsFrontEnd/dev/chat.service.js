"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('angular2/core');
var CHAT_URL = "ws://130.229.171.11:8081/chat";
var ChatService = (function () {
    function ChatService(wsService) {
        this.messages = wsService
            .connect(CHAT_URL)
            .map(function (response) {
            console.log("response: " + response.data);
            var data = JSON.parse(response.data);
            return {
                from: data.from,
                to: data.to,
                content: data.content
            };
        });
    }
    ChatService = __decorate([
        core_1.Injectable()
    ], ChatService);
    return ChatService;
}());
exports.ChatService = ChatService;
//# sourceMappingURL=chat.service.js.map