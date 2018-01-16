import { Component, OnInit } from '@angular/core';
import { DiscordGuild } from '../dto/DiscordGuild';
import { DiscordStatsService } from '../discord-stats.service';
import { ActivatedRoute } from '@angular/router';
import { DiscordVoiceChannel } from '../dto/DiscordVoiceChannel';
import { DiscordUser } from '../dto/DiscordUser';
import { DiscordGuildStats } from '../dto/DiscordGuildStats';

@Component({
  selector: 'app-guild-detail',
  templateUrl: './guild-detail.component.html',
  styleUrls: ['./guild-detail.component.scss']
})
export class GuildDetailComponent implements OnInit {
  
  guild: DiscordGuild;
  voiceChannels: Array<DiscordVoiceChannel> = new Array<DiscordVoiceChannel>();
  discordGuildStats: DiscordGuildStats;
  members: Array<DiscordUser> = new Array<DiscordUser>();

  constructor(private route: ActivatedRoute, private discordStatsService: DiscordStatsService) { }

  ngOnInit() {
    const guildId: string = this.route.snapshot.paramMap.get('id');
    this.discordStatsService.getGuild(guildId).subscribe(
      data => {this.guild = data;},
      error => {console.log("error");}//TODO
    );
    this.discordStatsService.getVoiceChannels(guildId).subscribe(
      data => {this.voiceChannels = data;
      this.voiceChannels.sort((c1, c2) => c1.position - c2.position);
    },
      error => {console.log("error");}//TODO
    );
    this.discordStatsService.getMembers(guildId).subscribe(
      data => {this.members = data;},
      error => {console.log("error");}
    );
    this.discordStatsService.getGuildStats(guildId).subscribe(
      data => {this.discordGuildStats = data;},
      error => {console.log("error");}
    );
  }
}
