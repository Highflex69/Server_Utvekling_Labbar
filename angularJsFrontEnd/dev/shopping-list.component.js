"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("angular2/core");
var ShoppingListComponent = (function () {
    function ShoppingListComponent() {
        this.shoppingListItems = [
            { name: "Milk" },
            { name: "Suggar" },
            { name: "bread" },
        ];
        this.selectedItem = { name: "" };
        this.wSocket = new WebSocket("ws://localhost:8080/chat");
    }
    ShoppingListComponent.prototype.onItemClicked = function (shoppingListItem) {
        this.selectedItem = shoppingListItem;
        this.wSocket.onopen = function () {
            this.wSocket.send("OPEN carlos test123 teddy");
        };
        console.log("OnItemClicked");
    };
    ShoppingListComponent.prototype.onAddItem = function (shoppingListItem) {
        this.shoppingListItems.push({ name: shoppingListItem.value });
    };
    ShoppingListComponent.prototype.onDeleteItem = function () {
        this.shoppingListItems.splice(this.shoppingListItems.indexOf(this.selectedItem), 1);
    };
    ShoppingListComponent = __decorate([
        core_1.Component({
            selector: "shopping-list",
            template: "\n    <ul>\n      <li \n      *ngFor=\"#shoppingListItem of shoppingListItems\"\n      (click)=\"onItemClicked(shoppingListItem)\"\n      >{{shoppingListItem.name}}</li>\n    </ul>\n    <input type=\"text\" [(ngModel)] = \"selectedItem.name\">\n    <button (click)=\"onDeleteItem()\">Delete item</button><br>\n    <input type=\"text\" #shoppingListItem>\n    <button (click)=\"onAddItem(shoppingListItem)\">Add item</button>\n  "
        })
    ], ShoppingListComponent);
    return ShoppingListComponent;
}());
exports.ShoppingListComponent = ShoppingListComponent;
//# sourceMappingURL=shopping-list.component.js.map