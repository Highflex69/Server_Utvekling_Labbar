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
var CreateMessage = (function () {
    function CreateMessage(chatService) {
        this.chatService = chatService;
        this.submitted = false;
        this.message = {
            from: 'gogo',
            to: 'meme',
            content: ''
        };
    }
    CreateMessage.prototype.onSubmit = function () {
        this.chatService.messages.next(this.message);
        this.message.content = '';
    };
    CreateMessage = __decorate([
        core_1.Component({
            selector: 'create-message',
            template: "\n      <form #sendMsg = \"ngForm\" (ngSubmit)=\"onSubmit()\">\n        <div class=\"input-group col-xs-8\">\n          \n          <input\n            [(ngModel)]=\"message.content\"\n            ngControl=\"msg\"\n            required\n            type=\"text\"\n            class = \"form-control\"\n            placeholder=\"message...\"/>\n            \n            <span class = \"input-group-btn\">\n              <button\n                [disabled]=\"!sendMsg.form.valid\"\n                class=\"btn btn-secondary\"\n                type=\"submit\">send</button>       \n            </span>     \n          </div>    \n       </form>",
            directives: [],
            providers: [chat_service_1.ChatService]
        })
    ], CreateMessage);
    return CreateMessage;
}());
exports.CreateMessage = CreateMessage;
//# sourceMappingURL=create-message.componet.js.map