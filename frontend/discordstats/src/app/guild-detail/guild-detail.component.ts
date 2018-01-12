import { Component, OnInit } from '@angular/core';
import { DiscordGuild } from '../dto/DiscordGuild';
import { DiscordStatsService } from '../discord-stats.service';
import { ActivatedRoute } from '@angular/router';
import { DiscordVoiceChannel } from '../dto/DiscordVoiceChannel';

@Component({
  selector: 'app-guild-detail',
  templateUrl: './guild-detail.component.html',
  styleUrls: ['./guild-detail.component.scss']
})
export class GuildDetailComponent implements OnInit {
  
  guild: DiscordGuild;
  voiceChannels: Array<DiscordVoiceChannel> = new Array<DiscordVoiceChannel>();

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
  }
}
