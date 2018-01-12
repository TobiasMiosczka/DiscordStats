import { Component, OnInit } from '@angular/core';
import { DiscordStatsService } from '../discord-stats.service';
import { Console } from '@angular/core/src/console';
import { DiscordGuild } from '../dto/DiscordGuild';

@Component({
  selector: 'app-guilds',
  templateUrl: './guilds.component.html',
  styleUrls: ['./guilds.component.scss']
})
export class GuildsComponent implements OnInit {

  guilds: Array<DiscordGuild> = new Array<DiscordGuild>();

  constructor(private discordStatsService: DiscordStatsService) { }

  ngOnInit() {
    this.discordStatsService.getGuilds().subscribe(
      data => {this.guilds = data;},
      error => {console.log("error");}//TODO
    );
  }
}