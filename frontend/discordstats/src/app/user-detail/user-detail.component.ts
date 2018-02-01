import { Component, OnInit } from '@angular/core';
import { DiscordStatsService } from '../discord-stats.service';
import { ActivatedRoute } from '@angular/router';
import { DiscordUser } from '../dto/DiscordUser';
import { DiscordVoiceChannelUsage } from '../dto/DiscordVoiceChannelUsage';
import { DiscordStats } from '../dto/DiscordStats';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {

  discordUser: DiscordUser;
  discordUserStats: DiscordStats;
  discordVoiceChannelUsages: Array<DiscordVoiceChannelUsage> = new Array<DiscordVoiceChannelUsage>();
  longestDiscordVoiceChannelUsage: Array<DiscordVoiceChannelUsage>;

  now: Date = new Date();
  from: Date = new Date(this.now.getFullYear(), this.now.getMonth(), this.now.getDate() - 1, this.now.getHours(), this.now.getMinutes(), this.now.getSeconds());
  to: Date = new Date(this.now.getFullYear(), this.now.getMonth(), this.now.getDate() + 1, this.now.getHours(), this.now.getMinutes(), this.now.getSeconds());

  constructor(private route: ActivatedRoute, private discordStatsService: DiscordStatsService) { }

  ngOnInit() {
    let userId: string = this.route.snapshot.paramMap.get('id');
    this.discordStatsService.getUser(userId).subscribe(
      data => {this.discordUser = data;},
      error => {console.log("Error");}
    );
    this.discordStatsService.getUserStats(userId).subscribe(
      data => {this.discordUserStats = data;},
      error => {console.log("Error");}
    )
    this.discordStatsService.getUserLongestVoiceChannelUsage(userId).subscribe(
      data => {this.longestDiscordVoiceChannelUsage = data;},
      error => {console.log("error");}
    );
    this.update();
  }

  update(): void {
    let userId: string = this.route.snapshot.paramMap.get('id');
    this.discordStatsService.getVoiceChannelUsageByUser(userId, this.from, this.to).subscribe(
      data => {this.discordVoiceChannelUsages = data;},
      error => {console.log("Error");}
    );
  }
}
