import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { MaterialModule } from './material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './/app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { MAT_DATE_LOCALE } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { GuildsComponent } from './guilds/guilds.component';
import { DiscordStatsService } from './discord-stats.service';
import { GuildDetailComponent } from './guild-detail/guild-detail.component';
import { VoiceChannelDetailComponent } from './voice-channel-detail/voice-channel-detail.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { GuildListComponent } from './guild-list/guild-list.component';
import { UserListComponent } from './user-list/user-list.component';
import { VoiceChannelListComponent } from './voice-channel-list/voice-channel-list.component';
import { SecondsToDurationStringPipe } from './pipes/SecondsToDurationStringPipe';
import { ChannelTimetableComponent } from './timetable/channel-timetable.component';
import { UserTimetableComponent } from './timetable/user-timetable.component';
import { DiscordVoiceChannelUsageDialogComponent } from './discord-voice-channel-usage-dialog/discord-voice-channel-usage-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    SecondsToDurationStringPipe,
    GuildsComponent,
    GuildDetailComponent,
    VoiceChannelDetailComponent,
    ChannelTimetableComponent,
    UserTimetableComponent,
    UserDetailComponent,
    GuildListComponent,
    UserListComponent,
    VoiceChannelListComponent,
    DiscordVoiceChannelUsageDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    DiscordStatsService,
    {provide: MAT_DATE_LOCALE, useValue: 'de-DE'}
    ],
  bootstrap: [AppComponent],
  entryComponents: [DiscordVoiceChannelUsageDialogComponent]
})
export class AppModule {}
