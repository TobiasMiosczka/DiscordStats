import { Pipe, PipeTransform } from '@angular/core';
import {Gender} from '../gender.enum';

@Pipe({
  name: 'gender'
})
export class GenderPipe implements PipeTransform {

  transform(value: Gender, args?: any): string {
    const keys = Object.keys(Gender);
    const strings = Object.keys(Gender).slice(keys.length / 2);
    return strings[value];
  }

}
