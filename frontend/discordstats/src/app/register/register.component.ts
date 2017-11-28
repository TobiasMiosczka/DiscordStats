import {Component, Input, OnInit} from '@angular/core';
import {UserDto} from '../user-dto';
import {Gender} from '../gender.enum';
import {RegisterService} from '../register.service';
import {logger} from 'codelyzer/util/logger';
import {copyObj} from "@angular/animations/browser/src/util";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  @Input() userDto: UserDto;

  hidden = true;

  today: Date = new Date();

  constructor(private registerService: RegisterService) {
  }

  genders(): Gender[] {
    const genders: Gender[] = [Gender.MALE, Gender.FEMALE];
    return genders;
  }

  register(): void {
    this.registerService.registerUser(this.userDto);
    console.log('test');
  }

  cancel(): void {
    this.userDto = new UserDto();
  }

  ngOnInit() {
    this.userDto = new UserDto();
  }
}
