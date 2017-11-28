import { Pipe, PipeTransform } from '@angular/core';
import {Gender} from '../gender.enum';

@Pipe({
  name: 'genderToSalution'
})
export class GenderToSalutionPipe implements PipeTransform {

  transform(value: Gender, args?: any): string {
    switch (value) {
      case Gender.MALE:
        return 'Mr.';
      case Gender.FEMALE:
        return 'Mrs.';
      default:
        return '';
    }
  }

}
