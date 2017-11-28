import {Gender} from './gender.enum';

export class UserDto {
  constructor() {}
  username: string;
  password: string;
  email: string;
  gender: Gender;
  firstname: string;
  lastname: string;
  birthdate: Date;
}