import { Injectable } from '@angular/core';
import {UserDto} from './user-dto';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class RegisterService {

  private URL_REGISTER_USER: string = 'http://localhost/user/registration';

  constructor(private http: HttpClient) { }

  registerUser(userDto: UserDto): void {
    this.http
      .post(this.URL_REGISTER_USER, userDto)
      // See below - subscribe() is still necessary when using post().
      .subscribe();
  }

}
