import {Component} from "angular2/core";
import {$WebSocket} from 'angular2-websocket/angular2-websocket';
@Component({
  selector: "shopping-list",
  template:`
    <ul>
      <li 
      *ngFor="#shoppingListItem of shoppingListItems"
      (click)="onItemClicked(shoppingListItem)"
      >{{shoppingListItem.name}}</li>
    </ul>
    <input type="text" [(ngModel)] = "selectedItem.name">
    <button (click)="onDeleteItem()">Delete item</button><br>
    <input type="text" #shoppingListItem>
    <button (click)="onAddItem(shoppingListItem)">Add item</button>
  `
})
export class ShoppingListComponent{


  public shoppingListItems = [
    {name: "Milk"},
    {name: "Suggar"},
    {name: "bread"},
  ];
  public selectedItem = {name: ""};
  onItemClicked(shoppingListItem) {
    this.selectedItem = shoppingListItem;
    var wSocket = new $WebSocket("ws://localhost:8080/chat");
    
    wSocket.send("OPEN carlos test123 teddy");
    console.log("OnItemClicked");
  }

  onAddItem(shoppingListItem) {
    this.shoppingListItems.push({name: shoppingListItem.value});


    //var host = "ws://130.229.171.11:8080/chat";

    //var wSocket = new $WebSocket(host);
  }

  onDeleteItem() {
    this.shoppingListItems.splice(this.shoppingListItems.indexOf(this.selectedItem), 1);
  }
}
