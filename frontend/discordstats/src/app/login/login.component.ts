import { Component, OnInit } from '@angular/core';
import {Oauth2Service} from '../oauth2/oauth2.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private username = '';
  private password = '';

  constructor(private oauth2Service: Oauth2Service) { }

  ngOnInit() {
  }

  login(): void {
    this.oauth2Service.login(this.username, this.password);
  }

}
