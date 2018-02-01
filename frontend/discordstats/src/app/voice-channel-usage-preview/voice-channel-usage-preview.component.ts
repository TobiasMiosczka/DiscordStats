import { Component, OnInit, Input } from '@angular/core';
import { DiscordVoiceChannelUsage } from '../dto/DiscordVoiceChannelUsage';

@Component({
  selector: 'app-voice-channel-usage-preview',
  templateUrl: './voice-channel-usage-preview.component.html',
  styleUrls: ['./voice-channel-usage-preview.component.scss']
})
export class VoiceChannelUsagePreviewComponent implements OnInit {

  @Input()
  data: DiscordVoiceChannelUsage;

  constructor() { }

  ngOnInit() {
  }

}
