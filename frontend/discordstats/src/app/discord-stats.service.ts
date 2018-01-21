import { Injectable } from '@angular/core';
import { DiscordGuild } from './dto/DiscordGuild';
import { DiscordVoiceChannel } from './dto/DiscordVoiceChannel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { DiscordVoiceChannelUsage } from './dto/DiscordVoiceChannelUsage';
import { DiscordUser } from './dto/DiscordUser';
import { DiscordStats } from './dto/DiscordStats';

@Injectable()
export class DiscordStatsService {

  URL = 'http://discordstats.wookiedaris.com:9091/rest/api';
  URL_GUILD = '/guild';
  URL_VOICECHANNEL = '/voicechannel';
  URL_USER = '/user';

  constructor(private http: HttpClient) { }

  getGuilds():Observable<Array<DiscordGuild>> {
    return this.http.get<Array<DiscordGuild>>(this.URL + this.URL_GUILD);
  }

  getGuild(id: string):Observable<DiscordGuild> {
    return this.http.get<DiscordGuild>(this.URL + this.URL_GUILD + '/' + id);
  }

  getVoiceChannels(guildId: string): Observable<Array<DiscordVoiceChannel>> {
    return this.http.get<Array<DiscordVoiceChannel>>(this.URL + this.URL_VOICECHANNEL + '?guild=' + guildId);
  }

  getVoiceChannel(voiceChannelId: string): Observable<DiscordVoiceChannel> {
    return this.http.get<DiscordVoiceChannel>(this.URL + this.URL_VOICECHANNEL + '/' + voiceChannelId);
  }

  getVoiceChannelUsageByVoiceChannel(voiceChannelId: string, from: Date, to: Date): Observable<Array<DiscordVoiceChannelUsage>> {
    return this.http.get<Array<DiscordVoiceChannelUsage>>(this.URL + this.URL_VOICECHANNEL + '/' + voiceChannelId + '/voicechannelusage?from=' + this.serializeDate(from) + '&to=' + this.serializeDate(to));
  }

  getVoiceChannelUsageByUser(userId: string, from: Date, to: Date): Observable<Array<DiscordVoiceChannelUsage>> {
    return this.http.get<Array<DiscordVoiceChannelUsage>>(this.URL + this.URL_USER + '/' + userId + '/voicechannelusage?from=' + this.serializeDate(from) + '&to=' + this.serializeDate(to));
  }

  getUser(userId: string): Observable<DiscordUser> {
    return this.http.get<DiscordUser>(this.URL + this.URL_USER + '/' + userId);
  }

  getMembers(guildId: string): Observable<Array<DiscordUser>> {
    return this.http.get<Array<DiscordUser>>(this.URL + this.URL_GUILD + '/' + guildId + '/user');
  }

  getGuildStats(guildId: string): Observable<DiscordStats> {
    return this.http.get<DiscordStats>(this.URL + this.URL_GUILD + '/' + guildId + '/stats');
  }

  getVoiceChannelStats(voiceChannelId: string): Observable<DiscordStats> {
    return this.http.get<DiscordStats>(this.URL + this.URL_VOICECHANNEL + '/' + voiceChannelId + '/stats');
  }

  getUserStats(userId: string): Observable<DiscordStats> {
    return this.http.get<DiscordStats>(this.URL + this.URL_USER + '/' + userId + '/stats');
  }

  serializeDate(date: Date): string {
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() ;
  }
}
