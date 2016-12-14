import {Component} from 'angular2/core';
import {ShoppingListComponent} from "./shopping-list.component";
import {Connection} from "./app.connection";

@Component({
    selector: 'my-app',
    template: `<shopping-list></shopping-list>
    `,
  directives: [ShoppingListComponent]
})
export class AppComponent {
/*
  constructor(private _connection: Connection)
  {
      _connection.send();
  }*/
}
