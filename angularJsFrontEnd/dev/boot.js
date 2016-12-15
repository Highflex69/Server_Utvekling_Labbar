"use strict";
///<reference path="../node_modules/angular2/typings/browser.d.ts"/>
var browser_1 = require('angular2/platform/browser');
require('rxjs/Rx');
var app_component_1 = require("./app.component");
var router_1 = require('angular2/router');
browser_1.bootstrap(app_component_1.AppComponent, [
    router_1.ROUTER_PROVIDERS
]);
//# sourceMappingURL=boot.js.map