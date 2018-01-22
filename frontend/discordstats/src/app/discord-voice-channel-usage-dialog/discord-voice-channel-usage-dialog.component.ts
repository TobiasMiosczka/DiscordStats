import { Component, OnInit, Input, Inject } from '@angular/core';
import { DiscordVoiceChannelUsage } from '../dto/DiscordVoiceChannelUsage';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-discord-voice-channel-usage-dialog',
  templateUrl: './discord-voice-channel-usage-dialog.component.html'
})
export class DiscordVoiceChannelUsageDialogComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<DiscordVoiceChannelUsageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DiscordVoiceChannelUsage
  ) {}

  ngOnInit() {

  }

}
