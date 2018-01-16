import { Component, OnInit, Input } from '@angular/core';
import { DiscordGuild } from '../dto/DiscordGuild';

@Component({
  selector: 'app-guild-list',
  templateUrl: './guild-list.component.html',
  styleUrls: ['./guild-list.component.scss']
})
export class GuildListComponent implements OnInit {

  @Input()
  guilds: Array<DiscordGuild> = new Array<DiscordGuild>();

  constructor() { }

  ngOnInit() {
  }

}
