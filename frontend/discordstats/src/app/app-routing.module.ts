import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { GuildsComponent } from './guilds/guilds.component';
import { GuildDetailComponent } from './guild-detail/guild-detail.component';
import { VoiceChannelDetailComponent } from './voice-channel-detail/voice-channel-detail.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'guild/:id', component: GuildDetailComponent },
  { path: 'guild', component: GuildsComponent },
  { path: 'voice-channel/:id', component: VoiceChannelDetailComponent },
  { path: 'user/:id', component: UserDetailComponent },
  { path: 'about', component: AboutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
