import { Component, OnInit } from '@angular/core';
import { DiscordStatsService } from '../discord-stats.service';
import { DiscordVoiceChannelUsage } from '../dto/DiscordVoiceChannelUsage';
import { ActivatedRoute } from '@angular/router';
import { DiscordVoiceChannel } from '../dto/DiscordVoiceChannel';
import { Observable } from 'rxjs/Observable';
import { DiscordStats } from '../dto/DiscordStats';

@Component({
  selector: 'app-voice-channel-detail',
  templateUrl: './voice-channel-detail.component.html',
  styleUrls: ['./voice-channel-detail.component.scss']
})
export class VoiceChannelDetailComponent implements OnInit {

  discordVoiceChannel: DiscordVoiceChannel;
  discordVoiceChannelUsages: Array<DiscordVoiceChannelUsage> = new Array<DiscordVoiceChannelUsage>();
  discordVoiceChannelStats: DiscordStats;
  longestDiscordVoiceChannelUsage: Array<DiscordVoiceChannelUsage>;

  now: Date = new Date();
  from: Date = new Date(this.now.getFullYear(), this.now.getMonth(), this.now.getDate() - 1, this.now.getHours(), this.now.getMinutes(), this.now.getSeconds());
  to: Date = new Date(this.now.getFullYear(), this.now.getMonth(), this.now.getDate() + 1, this.now.getHours(), this.now.getMinutes(), this.now.getSeconds());

  constructor(private route: ActivatedRoute, private discordStatsService: DiscordStatsService) { }

  ngOnInit() {
    let channelId: string = this.route.snapshot.paramMap.get('id');
    this.discordStatsService.getVoiceChannel(channelId).subscribe(
      data => {this.discordVoiceChannel = data; },
      error => {console.log("error");}
    );
    this.discordStatsService.getVoiceChannelStats(channelId).subscribe(
      data => {this.discordVoiceChannelStats = data; },
      error => {console.log("error");}
    );
    this.discordStatsService.getVoiceChannelLongestVoiceChannelUsage(channelId).subscribe(
      data => {this.longestDiscordVoiceChannelUsage = data;},
      error => {console.log("error");}
    );
    this.update();
  }

  update(): void {
    let channelId: string = this.route.snapshot.paramMap.get('id');
    this.discordStatsService.getVoiceChannelUsageByVoiceChannel(channelId, this.from, this.to).subscribe(
      data => {this.discordVoiceChannelUsages = data; },
      error => {console.log("error");}
    );
  }
}
