import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { MaterialModule } from './material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './/app-routing.module';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { GenderPipe } from './pipes/gender.pipe';
import { GenderToSalutionPipe } from './pipes/gender-to-salution.pipe';
import { MAT_DATE_LOCALE } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { GuildsComponent } from './guilds/guilds.component';
import { DiscordStatsService } from './discord-stats.service';
import { GuildDetailComponent } from './guild-detail/guild-detail.component';
import { VoiceChannelDetailComponent } from './voice-channel-detail/voice-channel-detail.component';
import { TimeTableComponent } from './timetable/timetable.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    AboutComponent,
    GenderPipe,
    GenderToSalutionPipe,
    GuildsComponent,
    GuildDetailComponent,
    VoiceChannelDetailComponent,
    TimeTableComponent,
    UserDetailComponent
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
  bootstrap: [AppComponent]
})
export class AppModule {}
