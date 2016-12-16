import {Injectable} from 'angular2/core';

@Injectable()
export class UserService {
  private username:string;
  private password:string;

  public getUsername():string {
    return this.username;
  }
  public getPassword():string {
    return this.username;
  }
  public setUsername(username:string) {
    this.username = username;
  }
  public setPassword(password:string) {
    this.password = password;
  }
}
