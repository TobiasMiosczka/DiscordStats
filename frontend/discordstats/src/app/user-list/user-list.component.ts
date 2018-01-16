import { Component, Input } from '@angular/core';
import { DiscordUser } from '../dto/DiscordUser';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent{

  @Input()
  users: Array<DiscordUser> = new Array<DiscordUser>();

  constructor() { }
}
