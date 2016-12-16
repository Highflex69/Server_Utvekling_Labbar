"use strict";
///<reference path="../node_modules/angular2/typings/browser.d.ts"/>
var browser_1 = require('angular2/platform/browser');
require('rxjs/Rx');
var app_component_1 = require("./app.component");
var router_1 = require('angular2/router');
var user_service_1 = require("./user.service");
browser_1.bootstrap(app_component_1.AppComponent, [
    router_1.ROUTER_PROVIDERS,
    user_service_1.UserService
]);
//# sourceMappingURL=boot.js.map