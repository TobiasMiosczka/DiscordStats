import { Pipe } from "@angular/core";

@Pipe({
       name: 'SecondsToDurationStringPipe'
})
export class SecondsToDurationStringPipe{
    times = {
        year: 31557600,
        month: 2629746,
        day: 86400,
        hour: 3600,
        minute: 60,
        second: 1
    }

    transform(seconds: number){
        if (seconds == 0) {
            return '0 seconds';
        }
        let time_string: string = '';
        for(var key in this.times){
            if(Math.floor(seconds / this.times[key]) > 0){
                time_string += 
                    Math.floor(seconds / this.times[key]).toString() 
                    + ' ' 
                    + key.toString() 
                    + (Math.floor(seconds / this.times[key]) > 1 ? 's ' : ' ');
                seconds = seconds - this.times[key] * Math.floor(seconds / this.times[key]);
            }
        }
        return time_string;
    }
}