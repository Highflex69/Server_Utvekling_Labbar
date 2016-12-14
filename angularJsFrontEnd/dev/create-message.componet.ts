/**
 * Created by Teddy on 2016-12-14.
 */
import {ChatService} from './chat.service';
import {Component} from 'angular2/core';


  @Component({
    selector: 'create-message',
    template: `
      <form #sendMsg = "ngForm" (ngSubmit)="onSubmit()">
        <div class="input-group col-xs-8">
          
          <input
            [(ngModel)]="message.content"
            ngControl="msg"
            required
            type="text"
            class = "form-control"
            placeholder="message..."/>
            
            <span class = "input-group-btn">
              <button
                [disabled]="!sendMsg.form.valid"
                class="btn btn-secondary"
                type="submit">send</button>       
            </span>     
          </div>    
       </form>`,
    directives: [],
    providers: [ChatService]

  })

export class CreateMessage{
  private submitted = false;
  private message = {
    from: 'gogo',
    to: 'meme',
    content: ' ',
  }

  constructor(private chatService: ChatService)
  {

  }


  private onSubmit() {
    this.chatService.messages.next(this.message);
    this.message.content = '';
  }

}
