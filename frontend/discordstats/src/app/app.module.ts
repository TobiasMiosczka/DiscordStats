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
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RegisterService } from './register.service';
import { GenderPipe } from './pipes/gender.pipe';
import { GenderToSalutionPipe } from './pipes/gender-to-salution.pipe';
import {MAT_DATE_LOCALE} from '@angular/material';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    AboutComponent,
    LoginComponent,
    RegisterComponent,
    GenderPipe,
    GenderToSalutionPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [RegisterService,
              {provide: MAT_DATE_LOCALE, useValue: 'de-DE'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
