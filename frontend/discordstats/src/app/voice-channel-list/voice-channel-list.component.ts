import { Component, OnInit, Input } from '@angular/core';
import { DiscordVoiceChannel } from '../dto/DiscordVoiceChannel';

@Component({
  selector: 'app-voice-channel-list',
  templateUrl: './voice-channel-list.component.html',
  styleUrls: ['./voice-channel-list.component.scss']
})
export class VoiceChannelListComponent implements OnInit {

  @Input()
  voiceChannels: Array<DiscordVoiceChannel> = new Array<DiscordVoiceChannel>();

  constructor() { }

  ngOnInit() {
  }

}
