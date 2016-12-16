"use strict";
/**
 * Created by Teddy on 2016-12-14.
 */
var angular2_websocket_1 = require('angular2-websocket/angular2-websocket');
//import WebSocket = require('ws');
var Connection = (function () {
    function Connection() {
        this.wSocket = new angular2_websocket_1.$WebSocket("ws://130.229.171.11:8083/server");
        //var host = "ws://130.229.171.11:8080/chat";
        //var wSocket = new $WebSocket(host);
    }
    Connection.prototype.send = function () {
        this.wSocket.send("OPEN carlos test123 teddy");
    };
    return Connection;
}());
exports.Connection = Connection;
//# sourceMappingURL=app.connection.js.map